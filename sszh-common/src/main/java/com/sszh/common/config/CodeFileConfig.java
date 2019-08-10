package com.sszh.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 编码配置文件
 */
@Component
public class CodeFileConfig {


    @Value("classpath:properties/service-messages-dev.properties")
    private Resource file;

    private static final Map<String, String> CONFIG_MAP = new ConcurrentHashMap<String, String>();
    
    @Bean
    public void setConfigMap() throws IOException {
        InputStream in = file.getInputStream();
        Properties pro = new Properties();
        //properties对象封装配置文件的输入流，现在文件里面的信息都已被封装成String
        pro.load(in);
        for (Object o : pro.keySet()) {
            CONFIG_MAP.put(o.toString(), pro.getProperty(o.toString()));
        }
        in.close();
    }

    public String getValue(String config) {
        return CONFIG_MAP.get(config);
    }

}