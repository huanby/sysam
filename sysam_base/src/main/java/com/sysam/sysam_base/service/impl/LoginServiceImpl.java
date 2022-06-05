package com.sysam.sysam_base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sysam.sysam_base.entity.*;
import com.sysam.sysam_base.service.ILoginService;
import com.sysam.sysam_base.service.UserService;
import com.sysam.sysam_base.utils.LoginUserUtil;
import com.sysam.sysam_common.utils.BuildTree;
import com.sysam.sysam_common.utils.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

/**
 * LoginServiceImpl.java
 * 用户登录服务实现类
 *
 * @author hby
 * @since 2021-01-20
 */
@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession httpSession;

    /**
     * 通过用户姓名获取用户信息
     *
     * @param name
     * @return
     */
    @Override
    public User getUserByName(String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", name);
        User user = userService.getOne(queryWrapper);
        return user;
    }


    /**
     * 访问首页
     *
     * @return
     */
    @Override
    public Map<String, Object> index() {
        Map<String, Object> map = new HashMap<>();
        // 通过 session 获取用户
//        User user = (User) httpSession.getAttribute("user");
        // 使用 JWT 后，通过 shiro 获取用户
        User user = LoginUserUtil.getLoginUser();
        List<Tree<Menu>> trees = new ArrayList<Tree<Menu>>();
        //返回菜单列表的Tree结构
        //可以修改成使用jdk8新特性转换
        for (UserRole userRole : user.getUserRoles()) {
            for (RoleMenu roleMenu : userRole.getRole().getRoleMenus()) {
                Menu menu = roleMenu.getMenu();
                Tree<Menu> tree = new Tree<Menu>();
                //菜单id
                tree.setId(menu.getId().toString());
                //
                tree.setParentId(menu.getPid().toString());
                //菜单名称
                tree.setText(menu.getMenuname());
                //菜单属性
                Map<String, Object> attributes = new HashMap<>(16);
                //菜单URL
                attributes.put("url", menu.getUrl());
                //菜单路由信息
                attributes.put("route", menu.getRoute());
                //菜单icon
                attributes.put("icon", menu.getIcon());
                tree.setAttributes(attributes);
                trees.add(tree);
            }
        }
        //根据id去重复值
        trees = trees.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Tree<Menu>::getId))), ArrayList::new));
        // 默认顶级菜单为０，根据数据库实际情况调整
        List<Tree<Menu>> menus = BuildTree.buildList(trees, "0");
        map.put("user", user);
        map.put("menus", menus);
        return map;
    }
}
