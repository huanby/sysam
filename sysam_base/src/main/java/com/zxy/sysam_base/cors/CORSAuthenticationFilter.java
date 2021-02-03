package com.zxy.sysam_base.cors;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: sysam
 * @Package: com.zxy.sysam_base.config
 * @ClassName: CORSAuthenticationFilter
 * @Author: jibl
 * @Description:
 * @Date: 2021/2/3 9:11
 * @Version: 1.0
 */
@Component
public class CORSAuthenticationFilter extends FormAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(CORSAuthenticationFilter.class);

    public CORSAuthenticationFilter() {
        super();
    }

    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //Always return true if the request's method is OPTIONS
        if (request instanceof HttpServletRequest) {
            if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
                return true;
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);

    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse res = (HttpServletResponse) response;
//        res.setHeader("Access-Control-Allow-Origin", "http://localhost:9999");
        res.setStatus(HttpServletResponse.SC_FOUND);
        res.setCharacterEncoding("UTF-8");
        PrintWriter writer = res.getWriter();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 302);
        map.put("msg", "未登录");
        writer.write(JSON.toJSONString(map));
        writer.close();
        return false;
    }
}