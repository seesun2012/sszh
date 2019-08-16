package com.sszh.mybatis.example.main;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户中心-代码生成器
 */
public class SsoRunMain {
    
    //项目地址（绝对路径）
    private static final String PROJECT_PATH = "E:/JavaProject/sszh/sszh-common/sszh-util-mybatis-generator";

    //配置文件地址
    private static final String MYBATIS_GENERATOR_PATH = "E:/JavaProject/sszh/sszh-common/sszh-util-mybatis-generator/src/main/resources/properties/sso/mybatis-generator.xml";
    
    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File(MYBATIS_GENERATOR_PATH);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
    
}