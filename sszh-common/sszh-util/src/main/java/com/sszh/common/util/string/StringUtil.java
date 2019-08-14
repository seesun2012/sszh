package com.sszh.common.util.string;

public class StringUtil {


    public static boolean isEmpty(String param){
        if (null == param || "".equals(param) || param.length() <= 0) {
            return true;
        }
        return false;
    }
}