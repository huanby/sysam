package com.sysam.sysam_generator.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sysam.sysam_generator.entity.GeneratorConfig;
import com.sysam.sysam_generator.service.GeneratorConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 代码生成配置表 前端控制器
 * </p>
 *
 * @author jibl
 * @since 2021-02-04
 */
@Api(tags = "代码生成参数配置")
@RestController
@RequestMapping("/generatorConfig")
public class GeneratorConfigController {

    @Autowired
    GeneratorConfigService generatorConfigService;

    @ApiOperation(value = "代码生成参数配置",httpMethod = "get")
    @GetMapping("/list")
    public GeneratorConfig list() {
        QueryWrapper<GeneratorConfig> queryWrapper = new QueryWrapper<>();
        return generatorConfigService.getOne(queryWrapper);
    }

    @ApiOperation("保存代码生成参数配置")
    @GetMapping("/save")
    public boolean save(GeneratorConfig generatorConfig) {
        return generatorConfigService.saveOrUpdate(generatorConfig);
    }
}

