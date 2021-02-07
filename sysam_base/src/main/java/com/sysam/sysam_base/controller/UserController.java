package com.sysam.sysam_base.controller;


import com.sysam.sysam_base.entity.User;
import com.sysam.sysam_base.service.UserService;
import com.sysam.sysam_base.wrapper.UserQueryWrapper;
import com.sysam.sysam_common.utils.PageUtil;
import com.sysam.sysam_common.utils.PageUtilResult;
import com.sysam.sysam_common.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户管理Controller
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


    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @ApiOperation("新增用户")
    @ApiImplicitParam(name = "user", value = "用户实体类", defaultValue = "", required = true)
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ResultUtil userAdd(User user) {
        //新增用户
        boolean flag = userService.save(user);
        if (flag) {
            return ResultUtil.ok("操作成功！");
        } else {
            return new ResultUtil<>(500, "fail", null);
        }
    }


    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @ApiOperation("修改用户信息")
    @ApiImplicitParam(name = "user", value = "用户实体类", defaultValue = "", required = true)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultUtil userUpdate(@RequestParam User user) {
        //通过用户ID修改用户信息
        boolean flag = userService.userUpdate(user);
        if (flag) {
            return ResultUtil.ok("操作成功！");
        } else {
            return new ResultUtil<>(500, "fail", null);
        }
    }


    /**
     * 删除用户信息
     *
     * @param id
     * @return
     */
    @ApiOperation("删除用户信息")
    @ApiImplicitParam(name = "user", value = "用户实体类", defaultValue = "", required = true)
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResultUtil userDelete(@RequestParam(value = "id", required = true) Integer id) {
        //通过用户ID删除用户信息
        boolean flag = userService.userDelete(id);
        if (flag) {
            return ResultUtil.ok("操作成功！");
        } else {
            return new ResultUtil<>(500, "fail", null);
        }
    }


}
