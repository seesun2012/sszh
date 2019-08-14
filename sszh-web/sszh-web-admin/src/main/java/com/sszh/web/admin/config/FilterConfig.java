package com.sszh.web.admin.config;

import com.sszh.web.admin.filter.LoginFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    // 不需要过滤的文件（包含）
    @Value("${system.unFilterFile:/css/,/js/,/script/,/html/,/image/,.css,.js,.ico}")
    private String[] unFilterFolder;

    // 不需要过滤的地址（相等）
    @Value("${system.unFilterUrl:/,/login,/doLogin,/logout,/system/getCheckCode,/system/syncSystemTime}")
    private String[] unFilterUrl;
    
    @Bean
    public FilterRegistrationBean registFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new LoginFilter(unFilterUrl, unFilterFolder));
        registration.addUrlPatterns("/*");
        registration.setName("loginFilter");
        registration.setOrder(1);
        return registration;
    }
    
}