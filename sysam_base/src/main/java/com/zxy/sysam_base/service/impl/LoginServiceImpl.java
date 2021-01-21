package com.zxy.sysam_base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zxy.sysam_base.entity.Menu;
import com.zxy.sysam_base.entity.User;
import com.zxy.sysam_base.service.ILoginService;
import com.zxy.sysam_base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * 模拟数据库查询
     *
     * @param userName 用户名
     * @return User
     */
    /*private User getMapByName(String userName) {
        Menu menu1 = new Menu();
        Menu menu2 = new Menu();
        Set<Permissions> permissionsSet = new HashSet<>();
        permissionsSet.add(permissions1);
        permissionsSet.add(permissions2);
        Role role = new Role("1", "admin", permissionsSet);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        User user = new User("1", "wsl", "123456", roleSet);
        Map<String, User> map = new HashMap<>();
        map.put(user.getUserName(), user);

        Set<Permissions> permissionsSet1 = new HashSet<>();
        permissionsSet1.add(permissions1);
        Role role1 = new Role("2", "user", permissionsSet1);
        Set<Role> roleSet1 = new HashSet<>();
        roleSet1.add(role1);
        User user1 = new User("2", "zhangsan", "123456", roleSet1);
        map.put(user1.getUserName(), user1);
        return map.get(userName);
    }*/
}
