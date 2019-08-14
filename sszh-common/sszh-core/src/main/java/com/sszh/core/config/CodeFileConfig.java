package com.sszh.core.config;

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


    /**
     * 加载存储在resources目录下的properties/service-messages-dev.properties配置文件，内容如下：
     *
     * 100=执行成功
     * 104=系统异常
     * 105=执行失败
     *
     */
    @Bean
    public void setConfigMap() throws IOException {
        InputStream in = file.getInputStream();
        Properties pro = new Properties();
        pro.load(in);
        for (Object o : pro.keySet()) {
            CONFIG_MAP.put(o.toString(), pro.getProperty(o.toString()));
        }
        in.close();
    }

    public static String getValue(String config) {
        return CONFIG_MAP.get(config);
    }

}