package com.sysam.sysam_base.config;

import com.sysam.sysam_base.entity.Role;
import com.sysam.sysam_base.entity.RoleMenu;
import com.sysam.sysam_base.entity.User;
import com.sysam.sysam_base.entity.UserRole;
import com.sysam.sysam_base.service.ILoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpSession;


/**
 * ShiroConfig.java
 * Shiro配置类
 * Shiro 从 Realm 获取安全数据（如用户、角色、权限）
 *
 * @author hby
 * @since 2021-01-20
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private ILoginService iLoginService;

    @Autowired
    private HttpSession session;


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

        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (UserRole userRole : user.getUserRoles()) {
            Role role = userRole.getRole();

            //添加角色标识信息
//            simpleAuthorizationInfo.addRole(role.getRoleName());
            simpleAuthorizationInfo.addRole(role.getRolesign());
            //List转Set
//            Set<String> perms = role.getRoleMenus().stream().map(roleMenu -> roleMenu.getMenu().getUrl()).collect(Collectors.toSet());
//            simpleAuthorizationInfo.addRoles(perms);

            //添加权限
            /*for (Permissions permissions : role.getPermissions()) {
                simpleAuthorizationInfo.addStringPermission(permissions.getPermissionsName());
            }*/
            for (RoleMenu permissions : role.getRoleMenus()) {
                simpleAuthorizationInfo.addStringPermission(permissions.getMenu().getPerms());
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 认证 -
     *
     * @MethodName doGetAuthenticationInfo
     * @Description 认证配置类
     * @Param [authenticationToken]
     * @Return AuthenticationInfo
     * @Author WangShiLin
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (ObjectUtils.isEmpty(authenticationToken.getPrincipal())) {
            return null;
        }
        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        User user = iLoginService.getUserByName(name);
        session.setAttribute("user",user);
        System.out.println(session);
        if (user == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //认证通过
            //这里验证 authenticationToken 和 simpleAuthenticationInfo 的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, user.getPassword().toString(), getName());
            // 设置超时时间
            SecurityUtils.getSubject().getSession().setTimeout(30 * 60 * 1000);
            return simpleAuthenticationInfo;
        }
    }
}
