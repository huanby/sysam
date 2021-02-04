package com.sysam.sysam_generator.service.impl;

import com.sysam.sysam_generator.entity.GeneratorConfig;
import com.sysam.sysam_generator.dao.GeneratorConfigMapper;
import com.sysam.sysam_generator.service.GeneratorConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 代码生成配置表 服务实现类
 * </p>
 *
 * @author jibl
 * @since 2021-02-04
 */
@Service
public class GeneratorConfigServiceImpl extends ServiceImpl<GeneratorConfigMapper, GeneratorConfig> implements GeneratorConfigService {
    @Override
    public GeneratorConfig findGeneratorConfig() {
        List<GeneratorConfig> generatorConfigs = this.baseMapper.selectList(null);
        return CollectionUtils.isNotEmpty(generatorConfigs) ? generatorConfigs.get(0) : null;
    }


}
