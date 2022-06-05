package com.sysam.sysam_base.config;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author huanyi
 * @version 1.0.0
 * @className JwtToken
 * @description JwtToken 自定义 AuthenticationToken 认证需要的 token
 * @date 2022-05-26
 */
public class JwtToken implements AuthenticationToken {

    private static final long seriaVersionUID = 1L;

    /**
     * 认证使用的 token
     */
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }


    /**
     * 返回主体
     *
     * @return
     */
    @Override
    public Object getPrincipal() {
        return token;
    }


    /**
     * 返回凭据
     *
     * @return
     */
    @Override
    public Object getCredentials() {
        return token;
    }
}
