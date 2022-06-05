package com.sysam.sysam_base.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * CustomRedisProperties.java
 * Shiro自定义Redis配置
 *
 * @author hby
 * @since 2021-01-20
 */
//@Component
//@ConfigurationProperties(prefix = "custom-redis-config")
public class CustomRedisProperties {

    /**
     * Redis server host.
     */
    private String host = "localhost";


    /**
     * Redis server port.
     */
    private int port = 6379;

    /**
     * Login username of the redis server.
     */
    private String username;



    /**
     * Login password of the redis server.
     */
    private String password;



    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }


    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}
