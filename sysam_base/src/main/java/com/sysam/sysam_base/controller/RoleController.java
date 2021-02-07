package com.sysam.sysam_base.controller;


import com.sysam.sysam_base.entity.Role;
import com.sysam.sysam_base.service.RoleService;
import com.sysam.sysam_base.wrapper.RoleQueryWrapper;
import com.sysam.sysam_common.utils.PageUtil;
import com.sysam.sysam_common.utils.PageUtilResult;
import com.sysam.sysam_common.utils.ResultUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 角色管理Controller
 * 前端控制器
 * </p>
 *
 * @author jibl
 * @since 2021-01-20
 */
@RestController
@RequestMapping("/sys/role")
public class RoleController {


    @Autowired
    private RoleService roleService;


    /**
     * 分页获取角色列表
     *
     * @param roleQueryWrapper
     * @param pageUtil
     * @return
     */
    @ApiOperation("分页获取角色列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<?> roleList(RoleQueryWrapper roleQueryWrapper, PageUtil pageUtil) {
        //初始化查询条件
        //result 返回数据
        PageUtilResult<Role> result = this.roleService.roleList(roleQueryWrapper, pageUtil);
        //返回结果
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    /**
     * 通过角色ID查看角色信息
     *
     * @param id
     * @return
     */
    @ApiOperation("通过角色ID查看角色信息")
    @ApiImplicitParam(name = "id", value = "角色id", defaultValue = "1", required = true)
    @RequestMapping(value = "/role/{id}", method = RequestMethod.GET)
    public ResultUtil<Role> roleInfo(Integer id) {
        //通过角色ID查询角色信息
        Role role = roleService.getById(id);
        return new ResultUtil<>(200, "success", role);
    }


    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    @ApiOperation("新增角色")
    @ApiImplicitParam(name = "role", value = "角色实体类", defaultValue = "", required = true)
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ResultUtil roleAdd(Role role) {
        //新增角色
        boolean flag = roleService.roleAdd(role);
        if (flag) {
            return ResultUtil.ok("操作成功！");
        } else {
            return new ResultUtil<>(500, "fail", null);
        }
    }


    /**
     * 修改角色信息
     *
     * @param role
     * @return
     */
    @ApiOperation("修改角色信息")
    @ApiImplicitParam(name = "role", value = "角色实体类", defaultValue = "", required = true)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultUtil roleUpdate(@RequestBody Role role) {
        //通过角色ID修改角色信息
        boolean flag = roleService.roleUpdate(role);
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
    @ApiOperation("删除角色信息")
    @ApiImplicitParam(name = "id", value = "角色ID", defaultValue = "", required = true)
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ResultUtil roleDelete(@RequestParam(value = "id", required = true) Integer id) {
        //通过角色ID删除角色信息
        boolean flag = roleService.roleDelete(id);
        if (flag) {
            return ResultUtil.ok("操作成功！");
        } else {
            return new ResultUtil<>(500, "fail", null);
        }
    }

}

