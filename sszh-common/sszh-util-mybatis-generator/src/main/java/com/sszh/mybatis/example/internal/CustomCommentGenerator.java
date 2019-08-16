package com.sszh.mybatis.example.internal;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.internal.DefaultCommentGenerator;

/**
 * 自定义生成注解的类
 */
public class CustomCommentGenerator extends DefaultCommentGenerator {

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        
        // 添加字段注释
        String remark = introspectedColumn.getRemarks();
        System.out.println(field.getName() + " " + introspectedColumn.getRemarks());
        if (remark != null && remark.trim().length()>0){
            field.addJavaDocLine("/**");
            field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
            field.addJavaDocLine(" */");
        }

    }
    
}
