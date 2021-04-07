package com.sysam.sysam_base.controller;


import com.sysam.sysam_base.entity.Menu;
import com.sysam.sysam_base.service.MenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 系统菜单表 前端控制器
 * </p>
 *
 * @author jibl
 * @since 2021-01-20
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    /**
     * 获取菜单列表
     * @return
     */
    @ApiOperation("获取菜单列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<Menu>> menuList() {
        //初始化查询条件
        //result 返回数据
        List<Menu> result = this.menuService.list();
        //返回结果
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

