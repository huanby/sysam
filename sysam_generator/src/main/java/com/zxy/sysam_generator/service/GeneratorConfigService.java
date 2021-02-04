package com.zxy.sysam_generator.service;

import com.zxy.sysam_generator.entity.GeneratorConfig;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 代码生成配置表 服务类
 * </p>
 *
 * @author jibl
 * @since 2021-02-04
 */
public interface GeneratorConfigService extends IService<GeneratorConfig> {

    GeneratorConfig findGeneratorConfig();
}
