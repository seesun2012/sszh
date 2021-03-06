package com.sszh.common.util.string;

import com.sszh.common.util.number.Numbers;

import java.util.UUID;

public class StringUtils {


    public static boolean isEmpty(String param){
        if (null == param || "".equals(param) || param.length() <= 0) {
            return true;
        }
        return false;
    }

    /**
     * 获取UUID,返回32字符串
     */
    @Deprecated
    public static String uuid() {
        String uuid = java.util.UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    /**
     * 以62进制（字母加数字）生成19位UUID，最短的UUID
     *
     * @return
     */
    public static String uuid19() {
        UUID uuid = UUID.randomUUID();
        StringBuilder sb = new StringBuilder();
        sb.append(digits(uuid.getMostSignificantBits() >> 32, 8));
        sb.append(digits(uuid.getMostSignificantBits() >> 16, 4));
        sb.append(digits(uuid.getMostSignificantBits(), 4));
        sb.append(digits(uuid.getLeastSignificantBits() >> 48, 4));
        sb.append(digits(uuid.getLeastSignificantBits(), 12));
        return sb.toString();
    }

    private static String digits(long val, int digits) {
        long hi = 1L << (digits * 4);
        return Numbers.toString(hi | (val & (hi - 1)), Numbers.MAX_RADIX).substring(1);
    }
    
    public static void main(String[] args) {
        System.out.println(uuid19());
    }
    
}