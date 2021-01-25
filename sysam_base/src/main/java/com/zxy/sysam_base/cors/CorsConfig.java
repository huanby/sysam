package com.zxy.sysam_base.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author Bob Zhang
 * @ClassName: CorsConfig
 * @Description: 跨域系统配置
 * @since 2021-01-22
 */
@Configuration
public class CorsConfig {
    /**
     * 允许任何域名使用
     * 允许任何头
     * 允许任何方法（post、get等）
     */
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // // addAllowedOrigin 不能设置为* 因为与 allowCredential 冲突,需要设置为具体前端开发地址
        corsConfiguration.addAllowedOrigin("http://localhost:9999");//前端的开发地址 允许跨域访问的域名
        corsConfiguration.addAllowedOrigin("http://127.0.0.1:9999");//前端的开发地址 允许跨域访问的域名
//        corsConfiguration.addAllowedOrigin("*");//前端的开发地址 允许跨域访问的域名
        corsConfiguration.addAllowedHeader("*");    // 请求头
        corsConfiguration.addAllowedMethod("*");    //请求方法
//        corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
//        corsConfiguration.addAllowedMethod(HttpMethod.POST);
//        corsConfiguration.addAllowedMethod(HttpMethod.GET);
//        corsConfiguration.addAllowedMethod(HttpMethod.PUT);
//        corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
//        corsConfiguration.addAllowedMethod(HttpMethod.OPTIONS);
        // 预检请求的有效期，单位为秒。
        // corsConfiguration.setMaxAge(3600L);
        // allowCredential 需设置为true
        corsConfiguration.setAllowCredentials(true);

        return corsConfiguration;
    }

    /**
     * CorsFilter过滤器
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
}