package com.sysam.sysam_base.config;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author huanyi
 * @version 1.1.1
 * @className NacosConfig
 * @description 配置类管理 Nacos
 * @date 2022-05-04
 * @update [序号][2022-05-04] [更改人姓名][变更描述]
 */
@Configuration
@NacosPropertySource(dataId = "sysam_dev", autoRefreshed = true)
public class NacosConfig {

    @Value("${server.port}")
    private int serverPort;

    @Value("${server.ip}")
    private String ip;

    @Value("${spring.application.name}")
    private String applicationName;

    @NacosInjected
    private NamingService namingService;

    /**
     * 启动自动注册服务
     *
     * @throws NacosException
     */
    @PostConstruct
    public void registerInstance() throws NacosException {
        namingService.registerInstance(applicationName, ip, serverPort);
    }
}
