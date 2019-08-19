package com.sszh.core.code;


import com.sszh.core.enums.BaseEnum;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @title:	优惠券-【获取方式】（枚举）
 * @version	v1.0.0
 * @author	研发中心-张辉
 * @date	2019年03月08日  下午10:57:23  周五
 *
 */
public enum BaseExceptionCodeEnum {

    /** 成功、失败 **/
    FAILED("-1", "success"),
    SUCCESS("1", "failed"),

    /** 系统 **/
    BASE_100("100", "执行成功"),
    BASE_104("104", "系统异常"),
    BASE_105("105", "执行失败"),
    BASE_10000("10000", "自定义异常（这里不提供文案，文案由产品提供）"),


    /** 网络请求 **/
    REQUEST_200("200", "OK"),
    REQUEST_401("401", "Unauthorized"),
    REQUEST_402("402", "Payment Required"),
    REQUEST_403("403", "Forbidden"),
    REQUEST_404("404", "Not Found"),
    REQUEST_405("405", "Method Not Allowed"),
    REQUEST_408("408", "Request Timeout"),
    REQUEST_410("410", "Gone"),
    REQUEST_429("429", "Too Many Requests"),
    REQUEST_500("500", "Internal Server Error"),
    REQUEST_501("501", "Not Implemented"),
    REQUEST_502("502", "Bad Gateway"),
    REQUEST_503("503", "Service Unavailable"),
    REQUEST_504("504", "Gateway Timeout"),
    
    
    /** 公共：1000 - 9999 */
    COMMON_1078("1078", "文件超出服务器限制大小！"),

    
    /** 
     * 自定义异常码：10000 - 19999
     */
    API_10000("10000", "自定义异常（这里不提供文案，文案由产品提供）"),
    
    ;

    private String code;		// 枚举值如：异常码、参数值
    private String msg;		    // 枚举描述：文字内容
    BaseExceptionCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public String getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }

    private static ConcurrentHashMap<String, BaseExceptionCodeEnum> enumMap = new ConcurrentHashMap<String, BaseExceptionCodeEnum>();
    private static ConcurrentLinkedQueue<BaseEnum> enumList = new ConcurrentLinkedQueue<BaseEnum>();
    static {
        EnumSet<BaseExceptionCodeEnum> set = EnumSet.allOf(BaseExceptionCodeEnum.class);
        BaseEnum vc;
        for (BaseExceptionCodeEnum each : set) {
            enumMap.put(each.getCode(), each);
            vc = new BaseEnum();
            vc.setVal(each.getMsg());
            vc.setContent(each.getMsg());
            enumList.add(vc);
        }
    }

    public static BaseExceptionCodeEnum get(String val) {
        return enumMap.get(val);
    }

    public static String getContent(String val) {
        if(get(val)!=null){
            return get(val).getCode();
        }
        return null;
    }

    /**
     * 获取枚举选项列表
     * <br>
     * 不直接返回全局list,使用深度复制的方式
     * @return
     */
    public static List<BaseEnum> list() {
        List<BaseEnum> list = new ArrayList<BaseEnum>();
        BaseEnum vc;
        for (BaseEnum item : enumList) {
            vc = new BaseEnum();
            vc.setVal(item.getVal());
            vc.setContent(item.getContent());
            list.add(vc);
        }
        return list;
    }
    
    
}