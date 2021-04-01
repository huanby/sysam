package com.sysam.sysam_base.controller;


import com.sysam.sysam_base.entity.*;
import com.sysam.sysam_base.service.ILoginService;
import com.sysam.sysam_base.service.MenuService;
import com.sysam.sysam_common.utils.BuildTree;
import com.sysam.sysam_common.utils.ResultUtil;
import com.sysam.sysam_common.utils.Tree;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LoginController.java
 * 用户登录控制器
 *
 * @author hby
 * @since 2021-01-20
 */
@Api(value = "", tags = {"用户登录管理"})
@Controller
@CrossOrigin
public class LoginController {


    @Autowired
    private MenuService menuService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private ILoginService iLoginService;


    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @ApiOperation(value = "用户登录", notes = "用户登录")
    @ApiResponses({@ApiResponse(code = 400, message = "Invalid Order")})
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true, dataType = "String")
    })
    @GetMapping("/login")
    @ResponseBody
    public ResultUtil<Object> login(String username, String password) {
        if (ObjectUtils.isEmpty(username) || ObjectUtils.isEmpty(password)) {
            return new ResultUtil<>(400, "请输入用户名和密码！", null);
        }
        //用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                username,
                password
        );
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
        } catch (UnknownAccountException e) {
//            log.error("用户名不存在！", e);
//            return "用户名不存在！";
            return new ResultUtil(400, "用户名不存在！", null);
        } catch (AuthenticationException e) {
//            log.error("账号或密码错误！", e);
//            return "账号或密码错误！";
            return new ResultUtil(400, "账号或密码错误！", null);

        } catch (AuthorizationException e) {
//            log.error("没有权限！", e);
//            return "没有权限";
            return new ResultUtil(403, "没有权限！", null);

        }
        String sessionId = httpSession.getId();
        System.out.println(sessionId);
//        return "login success";
        return new ResultUtil(200, "login success", null);

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        return "logout";
    }

    @RequiresRoles("ROLE_ADMIN")
    @RequiresPermissions("sys:user:userList")
    @PostMapping("/admin")
    @ApiResponses({
            //code重复的情况下，第一个声明的生效。
            @ApiResponse(code = 200, message = "删除成功"),
            @ApiResponse(code = 202, message = "删除失败，用户不存在")
    })
    @ResponseBody
    public String admin(@ApiParam(name = "name", value = "用户名称", required = true) String name) {
        System.out.println("aaa");
        return "admin success!";
    }

    /**
     * 首页
     *
     * @return
     */
//    @ApiImplicitParam(name = "id", value = "ID", required = false, paramType = "query", dataType = "integer")
    @ApiOperation("访问首页")
//    @RequiresRoles("ROLE_ADMIN")
//    @RequiresPermissions("sys:user:userList")
    @PostMapping("/index")
    @ResponseBody
    public Map<String, Object> index() {
        Map<String, Object> map = new HashMap<>();
        //获取访问首页展示的用户及菜单信息
        map = iLoginService.index();
        return map;
    }

    /**
     * seesion 过期后跳转登录页面302
     */
    @GetMapping("/reLogin")
    public void reLogin(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_FOUND);
    }

}
