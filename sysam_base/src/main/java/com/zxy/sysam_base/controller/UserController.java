package com.zxy.sysam_base.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zxy.sysam_base.entity.User;
import com.zxy.sysam_base.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jibl
 * @since 2021-01-19
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation("根据id查询用户的接口")
    @ApiImplicitParam(name = "id", value = "用户id", defaultValue = "1", required = true)
    @RequestMapping(value = "/selelctInfo", method = RequestMethod.POST)
    public User selelctInfo(Integer id) {
        User user = userService.getById(id);
        return user;
    }

    @ApiOperation("根据姓名查询用户的接口")
    @RequestMapping(value = "/selelctInfos",method = RequestMethod.POST)
    public Map<String, Object> selelctInfos(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        Map<String, Object> map = userService.getMap(wrapper.eq("username", username));
        return map;
    }

    @ApiOperation("更新用户")
    @RequestMapping(value = "/updateInfo",method = RequestMethod.POST)
    public boolean updateInfo() {
        //修改值
        User user = new User();
        user.setExpired(1);
        //修改条件
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("username", "test");
        boolean result = userService.update(user, userUpdateWrapper);
        return result;
    }
    @ApiOperation("新增用户")
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public boolean saveInfo(@RequestBody User user) {
        boolean result = userService.save(user);
        return result;
    }

    @ApiOperation("新增or更新用户")
    @RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
    public boolean saveOrUpdate() {
        //修改值
        User user = new User();
        user.setExpired(2);
        //修改条件
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("username", "test");
        boolean result = userService.saveOrUpdate(user, userUpdateWrapper);
        return result;
    }


}
