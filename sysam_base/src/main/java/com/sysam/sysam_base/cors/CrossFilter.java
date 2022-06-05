package com.sysam.sysam_base.cors;

/**
 * @ProjectName: sysam
 * @Package: com.sysam.sysam_base.cors
 * @ClassName: CrossFilter
 * @Author: jibl
 * @Description: 提供前后台跨域支持
 * @Date: 2021/2/8 10:15
 * @Version: 1.0
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Slf4j
//@Component
public class CrossFilter implements Filter {

    @Value("${allowOrigins}")
    private String allowOrigins;

    private ArrayList<String> allowOrigin = new ArrayList<>();

    public CrossFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("CrossFilter init...................");
        log.info("前台端口配置：" + allowOrigins);
        String[] allowOriginArr = allowOrigins.split(",");
        for (int i = 0; i < allowOriginArr.length; i++) {
            allowOrigin.add(allowOriginArr[i]);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String origin = ((HttpServletRequest) servletRequest).getHeader("Origin");
        //设置允许的跨域请求源   allowOrigin 允许列表    origin 请求源 url
        if (allowOrigin.contains(origin)) {
            httpServletResponse.setHeader("Access-Control-Allow-Origin", origin);
        } else {
            //Access-Control-Allow-Credentials 为 ture 时，ccess-Control-Allow-Origin 不能为 *
            httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        }
        // 设置允许的跨域请求头
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With, userId, token, x-requested-with, XMLHttpRequest, Accept,Authorization");
        // 设置允许的跨域请求方法
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT,OPTIONS, DELETE");
        //  设置允许跨域请求的最长时间，这里设置了30天，就为了尽量延长允许时间，
        //  时间过短会导致经常在请求前先发送一个Option请求，用于获取服务端允许哪些跨域访问类型，导致资源浪费。
        httpServletResponse.setHeader("Access-Control-Max-Age", "2592000");
        // 设置允许携带证书信息，包括 Session 和 Cookie 等等

        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
        //  设置请求类型为json请求
        httpServletResponse.setContentType("application/json;charset=utf-8");

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

