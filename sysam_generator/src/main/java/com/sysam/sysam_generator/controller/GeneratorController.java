package com.sysam.sysam_generator.controller;
import com.sysam.sysam_generator.entity.Column;
import com.sysam.sysam_generator.entity.GeneratorConfig;
import com.sysam.sysam_generator.entity.GeneratorConstant;
import com.sysam.sysam_generator.helper.GeneratorHelper;
import com.sysam.sysam_generator.service.GeneratorConfigService;
import com.sysam.sysam_generator.service.GeneratorService;
import com.sysam.sysam_generator.utils.BaseResult;
import com.sysam.sysam_generator.utils.ComUtils;
import com.sysam.sysam_generator.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: sysam
 * @Package: com.sysam.sysam_generator.controller
 * @ClassName: GeneratorController
 * @Author: jibl
 * @Description:
 * @Date: 2021/2/4 15:08
 * @Version: 1.0
 */

@RestController
@RequestMapping("/generator")
@RequiredArgsConstructor
public class GeneratorController {

    private final GeneratorHelper generatorHelper;

    private static final String SUFFIX = "_code.zip";

    @Autowired
    GeneratorService generatorService;

    @Autowired
    GeneratorConfigService generatorConfigService;

    @GetMapping("/tables/info")
    public BaseResult tablesInfo(String tableName, HttpServletRequest request) {
        return generatorService.selectTableInfos(tableName,request);
    }


    @GetMapping
//    @ControllerEndpoint(exceptionMessage = "代码生成失败")
    public void generate(@NotBlank(message = "{required}") String name, String remark, HttpServletResponse response) throws Exception {
        GeneratorConfig generatorConfig = generatorConfigService.findGeneratorConfig();
        if (generatorConfig == null) {
           System.out.println("代码生成配置为空");
        }

        String className = name;
        if (GeneratorConfig.TRIM_YES.equals(generatorConfig.getIsTrim())) {
            className = RegExUtils.replaceFirst(name, generatorConfig.getTrimValue(), StringUtils.EMPTY);
        }

        generatorConfig.setTableName(name);
        generatorConfig.setClassName(ComUtils.underscoreToCamel(className));
        generatorConfig.setTableComment(remark);
        // 生成代码到临时目录
        List<Column> columns = generatorService.getColumns(GeneratorConstant.DATABASE_TYPE, name);
        generatorHelper.generateEntityFile(columns, generatorConfig);
        generatorHelper.generateMapperFile(columns, generatorConfig);
        generatorHelper.generateMapperXmlFile(columns, generatorConfig);
        generatorHelper.generateServiceFile(columns, generatorConfig);
        generatorHelper.generateServiceImplFile(columns, generatorConfig);
        generatorHelper.generateControllerFile(columns, generatorConfig);
        // 打包
        String zipFile = System.currentTimeMillis() + SUFFIX;
        FileUtil.compress(GeneratorConstant.TEMP_PATH + "src", zipFile);
        // 下载
        FileUtil.download(zipFile, name + SUFFIX, true, response);
        // 删除临时目录
        FileSystemUtils.deleteRecursively(new File(GeneratorConstant.TEMP_PATH));
    }
}
