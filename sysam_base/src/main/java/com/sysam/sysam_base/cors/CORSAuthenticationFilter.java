package com.sysam.sysam_base.cors;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
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
 * @Package: com.sysam.sysam_base.config
 * @ClassName: CORSAuthenticationFilter
 * @Author: jibl
 * @Description: 自定义 session 跨域处理过滤器
 * @Date: 2021/2/3 9:11
 * @Version: 1.0
 */
//@Component
@Slf4j
public class CORSAuthenticationFilter extends FormAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(CORSAuthenticationFilter.class);

    public CORSAuthenticationFilter() {
        super();
    }

    /**
     * 如果请求中有 session，可以继续访问
     * <p>
     * 复杂的跨域请求，前端会先发送一个 OPTIONS 请求确认是否能够访问。所以，如果是 OPTIONS 请求，我们需要跳过认证。
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
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

    /**
     * session 跨域处理：
     * 没有 session 从 isAccessAllowed 跳转到 onAccessDenied
     * 拒绝访问，返回 302
     * <p>
     * token 认证处理：
     * 使用 token 后是一定没有 seesion 信息的，一定会跳转到 onAccessDenied
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        log.info("当 isAccessAllowed() 返回 false 的时候，才会执行方法 onAccessDenied() ");
        // session 跨域处理
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

        //token 认证处理：
        /*HttpServletRequest req = (HttpServletRequest) request;
        if ("OPTIONS".equals(req.getMethod())) {
            return true;
        }
        return super.onAccessDenied(request, response);*/
    }
}