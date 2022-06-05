package com.sysam.sysam_base.config;

import com.sysam.sysam_base.cors.CORSAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ShiroConfig.java
 * Shiro配置类
 *
 * @author hby
 * @since 2021-01-20
 */
@Configuration
//@RequiredArgsConstructor
public class ShiroConfig {

    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);


    //自定义Redis配置
//    @Autowired
//    private CustomRedisProperties customRedisProperties;

    @Resource
    private RedisProperties redisProperties;


    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    /**
     * 将自己的验证方式加入容器
     * 前后端支持跨域 session
     *
     * @return CustomRealm 自定义 Realm 授权认证类
     */
    @Bean
    public CustomRealm myShiroRealm() {
        logger.info("将自己的验证方式加入容器");
        //自定义Realm
        //realm从数据库查询权限数据，返回 ModularRealmAuthorizer
        CustomRealm customRealm = new CustomRealm();
        return customRealm;
    }

    @Bean
    public ShiroRealm shiroRealm() {
        logger.info("将自己的验证方式 ShiroRealm 加入容器");
        //自定义Realm
        //realm从数据库查询权限数据，返回 ModularRealmAuthorizer
        ShiroRealm shiroRealm = new ShiroRealm();
        return shiroRealm;
    }

    /**
     * 权限管理，配置主要是Realm的管理认证
     * SecurityManager执行授权，通过ModularRealmAuthorizer执行授权
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        //DefaultWebSecurityManager
        //ModularRealmAuthorizer执行realm（自定义的Realm）从数据库查询权限数据
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRealm(myShiroRealm());
        // 通过 token 验证
        securityManager.setRealm(shiroRealm());
        /*
         * 关闭shiro自带的session，详情见文档
         * http://shiro.apache.org/session-management.html#SessionManagement-
         * StatelessApplications%28Sessionless%29
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);

        //将自定义的会话管理器注册到安全管理器中
//        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    public CORSAuthenticationFilter corsAuthenticationFilter() {
        return new CORSAuthenticationFilter();
    }

    /**
     * JTW 过滤器
     *
     * @return
     */
    public JwtFilter jwtFilter() {
        return new JwtFilter();
    }

    /**
     * Filter工厂，设置对应的过滤条件和跳转条件
     *
     * @param securityManager Shiro 安全管理器
     * @return ShiroFilterFactoryBean 定义 Shiro 过滤器
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //对所有用户认证
//        filterChainDefinitionMap.put("/**", "authc");
        //不验证路径
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/v2/api-docs", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/login/**", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
//        filterChainDefinitionMap.put("/index/**", "anon");
        //authc:所有url必须通过认证才能访问，anon:所有url都可以匿名访问
//        filterChainDefinitionMap.put("/**", "corsAuthenticationFilter");
        //自定义过滤器 LinkedHashMap 类型，是有顺序的
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        //配置过滤器 corsAuthenticationFilter
//        filterMap.put("corsAuthenticationFilter", corsAuthenticationFilter());
        //配置过滤器 jwtFilter 支持 token
        filterMap.put("jwt", jwtFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
//        过滤链定义，从上向下顺序执行，一般将/**放在最为下边
        filterChainDefinitionMap.put("/**", "jwt");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    /**
     * 匹配所有加了 Shiro 认证注解的方法
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /*Shiro中session会话管理配置*/

    /**
     * 1.redis的控制器，配置 redis 缓存
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redisProperties.getHost() + ":" + redisProperties.getPort());
        if (StringUtils.isNotBlank(redisProperties.getPassword())) {
            redisManager.setPassword(redisProperties.getPassword());
        }
        redisManager.setTimeout(redisManager.getTimeout());
        redisManager.setDatabase(redisProperties.getDatabase());
        return redisManager;
    }


    /**
     * 2.配置sessionDao
     */
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO sessionDAO = new RedisSessionDAO();
        sessionDAO.setRedisManager(redisManager());
        return sessionDAO;
    }

    /**
     * 3.配置话管理器
     */
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }
    /*Shiro中session会话管理配置*/


}

