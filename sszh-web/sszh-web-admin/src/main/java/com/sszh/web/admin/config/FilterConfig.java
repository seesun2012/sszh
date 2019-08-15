package com.sszh.web.admin.config;

import com.sszh.web.admin.cache.AdminCacheFactory;
import com.sszh.web.admin.filter.LoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置Servlet过滤器
 */
@Configuration
public class FilterConfig {

    @Autowired
    private AdminCacheFactory adminCacheFactory;

    // 不需要过滤的文件（判断是否包含）
    @Value("${system.unFilterFile:/css/,/js/,/script/,/html/,/image/,.css,.js,.ico}")
    private String[] unFilterFolder;

    // 不需要过滤的地址（判断是否相等）
    @Value("${system.unFilterUrl:/,/login,/doLogin,/logout,/system/getCheckCode,/system/syncSystemTime}")
    private String[] unFilterUrl;


    /**
     * 启动项目向IOC注入拦截器
     */
    @Bean
    public FilterRegistrationBean registFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new LoginFilter(unFilterUrl, unFilterFolder, adminCacheFactory));
        registration.addUrlPatterns("/*");
        registration.setName("loginFilter");
        registration.setOrder(1);
        return registration;
    }
    
    
}