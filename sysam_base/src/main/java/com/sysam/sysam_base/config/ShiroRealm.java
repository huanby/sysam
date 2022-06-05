package com.sysam.sysam_base.config;

import com.sysam.sysam_base.entity.Role;
import com.sysam.sysam_base.entity.RoleMenu;
import com.sysam.sysam_base.entity.User;
import com.sysam.sysam_base.entity.UserRole;
import com.sysam.sysam_base.service.ILoginService;
import com.sysam.sysam_base.utils.ConvertUtil;
import com.sysam.sysam_base.utils.JwtUtil;
import com.sysam.sysam_base.utils.SpringContextBaseUtil;
import com.sysam.sysam_common.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.servlet.http.HttpSession;


/**
 * ShiroConfig.java
 * Shiro 配置类
 * Shiro 从 Realm 获取安全数据（如用户、角色、权限）
 *
 * @author hby
 * @since 2021-01-20
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private ILoginService iLoginService;

    @Autowired
    private HttpSession session;

    @Autowired
    @Lazy
    private RedisUtil redisUtil;


    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }


    /**
     * 授权
     *
     * @MethodName doGetAuthorizationInfo
     * @Description 权限配置类
     * @Param [principalCollection]
     * @Return AuthorizationInfo
     * @Author WangShiLin
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String name = (String) principalCollection.getPrimaryPrincipal();
        //查询用户名称
        User user = iLoginService.getUserByName(name);

        //授权信息  添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (UserRole userRole : user.getUserRoles()) {
            //获取角色
            Role role = userRole.getRole();
            //添加角色标识信息
            simpleAuthorizationInfo.addRole(role.getRolesign());
            //添加权限
            for (RoleMenu permissions : role.getRoleMenus()) {
                simpleAuthorizationInfo.addStringPermission(permissions.getMenu().getPerms());
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     *
     * @MethodName doGetAuthenticationInfo
     * @Description 认证配置类
     * <p>
     * 对拦截请求的 token 进行身份验证，由 <code>getSubject(request, response).login(token);</code> 调用
     * 只有 header 中含有 token 信息才会调用这个方法
     * login 登录时候不走这个验证，单独对登录的延迟进行处理
     * @Param [authenticationToken]
     * @Return AuthenticationInfo
     * @Author WangShiLin
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        log.info("身份认证");
        String token = (String) auth.getCredentials();
        if (token == null) {
            log.info("—身份认证失败—IP地址：" + ConvertUtil.getIpAddrByRequest(SpringContextBaseUtil.getHttpServletRequest()));
            throw new AuthenticationException("token 为空！");
        }
        // 校验 token 的有效性 User loginUser = this.checkUserTokenIsEffect(token);
        // 解密获得 username，用于和数据库进行对比
        String username = JwtUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token非法无效!");
        }
        // 从数据库查询用户信息
        log.debug("—校验token是否有效：" + token);
        User user = iLoginService.getUserByName(username);
        if (user == null) {
            throw new UnknownAccountException("用户不存在!");
        }
        // 判断用户状态
        if (user.getEnable() != 1) {
            throw new AuthenticationException("账号已被锁定,请联系管理员!");
        }

        // 校验 token 是否超时失效 & 或者账号密码是否错误
        if (!jwtTokenRefresh(token, username, user.getPassword())) {
            throw new AuthenticationException("Token失效，请重新登录!");
        }
        return new SimpleAuthenticationInfo(user, token, getName());
    }


    /**
     * 校验token的有效性
     *
     * @param token
     */
    public User checkUserTokenIsEffect(String token) throws AuthenticationException {
        // 解密获得 username，用于和数据库进行对比
        String username = JwtUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token非法无效!");
        }

        // 查询用户信息
        log.debug("———校验token是否有效————checkUserTokenIsEffect——————— " + token);
        User user = iLoginService.getUserByName(username);
        if (user == null) {
            throw new UnknownAccountException("用户不存在!");
        }
        // 判断用户状态
        if (user.getEnable() != 1) {
            throw new AuthenticationException("账号已被锁定,请联系管理员!");
        }

        // 校验 token 是否超时失效 & 或者账号密码是否错误
        if (!jwtTokenRefresh(token, username, user.getPassword())) {
            throw new AuthenticationException("Token失效，请重新登录!");
        }
        //返回用户实例
        return user;
    }


    /**
     * JWTToken刷新生命周期 （实现： 用户在线操作不掉线功能）
     * 1、登录成功后将用户的 JWT 生成的Token作为 k、v 存储到 cache 缓存里面(这时候k、v值一样)，缓存有效期设置为Jwt有效时间的2倍
     * 2、当该用户再次请求时，通过JWTFilter层层校验之后会进入到doGetAuthenticationInfo进行身份验证
     * 3、当该用户这次请求jwt生成的token值已经超时，但该token对应cache中的k还是存在，则表示该用户一直在操作只是JWT的token失效了，程序会给token对应的k映射的v值重新生成JWTToken并覆盖v值，该缓存生命周期重新计算
     * 4、当该用户这次请求jwt在生成的token值已经超时，并在cache中不存在对应的k，则表示该用户账户空闲超时，返回用户信息已失效，请重新登录。
     * 注意： 前端请求Header中设置Authorization保持不变，校验有效性以缓存中的token为准。
     * 用户过期时间 = Jwt有效时间 * 2。
     *
     * @param userName
     * @param passWord
     * @return
     */
    public boolean jwtTokenRefresh(String token, String userName, String passWord) {
        //缓存 token key
        String cacheToken = String.valueOf(redisUtil.get(JwtConstants.PREFIX_USER_TOKEN + token));
        if (StringUtils.isNotEmpty(cacheToken)) {
            // 校验token有效性
            if (!JwtUtil.verify(cacheToken, userName, passWord)) {
                String newAuthorization = JwtUtil.sign(userName, passWord);
                // 设置超时时间
                redisUtil.set(JwtConstants.PREFIX_USER_TOKEN + token, newAuthorization);
                redisUtil.expire(JwtConstants.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME * 2 / 1000);
                log.info("——用户在线操作，更新 token 保证不掉线——jwtTokenRefresh——" + token);
            }
            //update-begin--Author:scott  Date:20191005  for：解决每次请求，都重写redis中 token缓存问题
//			else {
//				// 设置超时时间
//				redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, cacheToken);
//				redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME / 1000);
//			}
            //update-end--Author:scott  Date:20191005   for：解决每次请求，都重写redis中 token缓存问题
            return true;
        }
        return false;
    }

    /**
     * 清除当前用户的权限认证缓存
     *
     * @param principals 权限信息
     */
    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }


}
