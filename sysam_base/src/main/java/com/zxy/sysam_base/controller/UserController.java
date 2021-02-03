package com.zxy.sysam_base.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zxy.sysam_base.entity.User;
import com.zxy.sysam_base.service.UserService;
import com.zxy.sysam_base.wrapper.UserQueryWrapper;
import com.zxy.sysam_common.utils.PageUtil;
import com.zxy.sysam_common.utils.PageUtilResult;
import com.zxy.sysam_common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/sys/user")
public class UserController {

    @Autowired
    UserService userService;


    /**
     * 分页获取用户列表
     *
     * @param userQueryWrapper
     * @param pageUtil
     * @return
     */
    @ApiOperation("分页获取用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<?> userList(UserQueryWrapper userQueryWrapper, PageUtil pageUtil) {
        //初始化查询条件
        //result 返回数据
        PageUtilResult<User> result = this.userService.userList(userQueryWrapper, pageUtil);
        //返回结果
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    /**
     * 通过用户ID查看用户信息
     *
     * @param id
     * @return
     */
    @ApiOperation("通过用户ID查看用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", defaultValue = "1", required = true)
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResultUtil<User> userInfo(Integer id) {
        //通过用户ID查询用户信息
        User user = userService.getById(id);
        return new ResultUtil<>(200, "success", user);
    }


    @ApiOperation("更新用户")
    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
    public boolean updateInfo() {
        //修改值
        User user = new User();
        user.setEnable(1);
        //修改条件
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("username", "test");
        boolean result = userService.update(user, userUpdateWrapper);
        return result;
    }

    @ApiOperation("新增用户")
    @RequestMapping(value = "/saveInfo", method = RequestMethod.POST)
    public boolean saveInfo(@RequestBody User user) {
        boolean result = userService.save(user);
        return result;
    }

    @ApiOperation("新增or更新用户")
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public boolean saveOrUpdate() {
        //修改值
        User user = new User();
        user.setEnable(2);
        //修改条件
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("username", "test");
        boolean result = userService.saveOrUpdate(user, userUpdateWrapper);
        return result;
    }


    @ApiOperation("根据id查询用户的接口")
    @ApiImplicitParam(name = "id", value = "用户id", defaultValue = "1", required = true)
    @RequestMapping(value = "/selelctInfo", method = RequestMethod.POST)
    public User selelctInfo(Integer id) {
        User user = userService.getById(id);
        System.out.println(user.getUserRoles());
        System.out.println(user);
        return user;
    }

    @ApiOperation("根据姓名查询用户的接口")
    @RequestMapping(value = "/selelctInfos", method = RequestMethod.POST)
    public Map<String, Object> selelctInfos(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        Map<String, Object> map = userService.getMap(wrapper.eq("username", username));
        return map;
    }


}
