package com.sszh.common.code;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public enum ErrorCodeEnum {
    RET_HEADER_ERROR("101", "header参数错误"),
    RET_PARAMERROR("102", "参数格式不正确"),
    RET_SYSTEMEXCEPTION("104", "系统异常"),
    RET_FAIL("105", "执行失败"),
    RET_NULLDATA("106", "未找到数据"),
    RET_FLOW_ACTIVITY_ERROR_CODE("108", "您已经参加过该活动了！"),
    RET_EXIST("110", "帐号已经存在"),
    RET_VCODE_TIMEOUT("111", "验证码已超时"),
    RET_VCODE_ERRO("112", "验证码不正确"),
    RET_NOT_EXIST("114", "账号不存在"),
    RET_CODE_NOUPDATE("115", "数据无更新"),
    RET_CODE_REQUESTED("116", "退款请求已发送，不用重复操作"),
    RET_URNPAY_CODE_NOPERMISSION("117", "你的订单不允许退款"),
    RET_CODE_UPDATETOSERVER("118", "需要将客户端数据同步到服务器"),
    RET_REDENVELOPES_CODE("121", "至少选择一种流量套餐并填写红包数量"),
    RET_REDENVELOPES_USE_CODE("122", "红包领取失败。"),
    RET_IMG_VCODE_ERRO("131", "图片验证码不正确"),
    RET_IMG_VCODE_TIMEOUT("132", "图片验证码已超时"),
    RET_SESSIONINVALID("202", "会话ID无效"),
    RET_MANAGER_CANNOT_LOGIN_APP("203", "管理员限制登录客户端"),
    RET_NOPERMISSION("203", "没有接入权限"),
    RET_ERROR_ACCOUNT_PASSWORD("206", "帐号或密码错误"),
    RET_ERRORPASSORACCOUNT206("206", "帐号或密码错误,请重新输入"),
    RET_NOACTIVE("207", "帐号未激活,请联系贵公司管理员!"),
    RET_NOACTIVE_GRANT("207", "你没有评论权限"),
    RET_NOACTTIVE_ZHONG("208", "帐号未激活,请联系中琛源管理员!"),
    RET_ERRORPASSORACCOUNT208("208", "帐号或密码错误,请重新输入"),
    RET_THIRD_ERROR_CODE("234", "未验证手机号码"),
    RET_ALREADY_JOIN_CODE("300", "您已经加入其他公司，请退出原公司后再申请！"),
    RET_ALREADY_JOIN_REPEAT_CODE("301", "您的申请已经通知管理员，请耐心等待！"),
    RET_NOCOMPANY("302", "公司不存在"),
    ACCOUNT_LOCK_ERROR("505", "本帐号已被限制手机登录，请取消限制后重新登录"),
    RET_NOTUNIQUE("707", "没有该适配组！"),
    RET_LAST_PAGE("998", "已经是最后一页"),
    RET_INVALID_CODE("404", "页面已失效！"),
    ERROR_902("902", "该操作需要登录，请登录后重试"),
    ERROR_903("903", "您的账号在其他设备登录，请重新登录"),
    RET_FORBIDDEN("303", "公司不存在！"),
    RET_ManagerIsExist("506", "客户名称已经存在"),
    RET_isRelated("508", "该客户已经关联了项目，不可删除!"),
    RET_isCreated("509", "该客户下已创建联系人，不可删除!"),
    markMountResultCode("800", "您的费用申请已超过最高审批权限，请在额度范围内进行申请!"),
    RET_UNATTENDANCESETTTING("510", "考勤方案未设置"),
    RET_ORDER_FAILED("520", "下单失败"),
    RET_PHONE_IS_ERROR("1001", "手机号码错误！"),
    RET_PRIZE("521", "快去赚点积分再来吧~"),
    RET_FLOW("2", "您的号码信息未知，暂时无法充值"),
    RET_PASSWORD("523", "密码不能为空"),
    RET_MESSAGE("524", "短信验证失败"),
    RET_REGISTER("525", "注册失败"),
    RET_ACTIVITY("526", "该账号已经参加过该活动"),
    RET_IS_PHONE("527", "该账号电话号码错误，请重新输入"),
    RET_IS_COUNT("528", "活动礼品已经发放完毕"),
    RET_IS_FILE("529", "改号码已经注册"),
    NOT_SUPPORT_PRODUCT("1001", "不支持此手机号码订购此款产品"),
    NO_PRODUCT("1002", "产品信息不存在！"),
    PAY_SUCCESS("1003", "支付成功！"),
    BUY_SUCCESS("1004", "购买成功！"),
    BUY_FAIL("1005", "购买失败未退款！"),
    BUY_FAIL_REFUND("1006", "购买失败已退款！"),
    COUPON_COUNT_EXCESS("1101", "优惠券数量超过限制"),
    COUPON_NOT_EXISTS("1102", "优惠券不存在"),
    COUPON_OVERDUE("1103", "优惠券已过期"),
    COUPON_USE_ERROR("1104", "优惠券不支持该交易"),
    POINT_NOT_ENOUGH100("1105", "100积分起兑换"),
    RET_INPUT("1007", "姓名或手机号码或验证码不能为空"),
    RET_DISSLOVE("1008", "解散团队失败！"),
    RET_NODISSLOVE("1009", "不是公司创始人,没有解散权限！"),
    RET_NOATTCRE_REFID("1010", "已经关联过日程！"),
    RET_NUM_EXCEEDED("1112", "您好，您已绑定十张卡。"),
    RET_BLIND_NO("1113", "您好，该卡已绑定，请确定您的卡号"),
    RET_BLIND_OK("1114", "绑定成功"),
    RET_BLIND_WORRY("1115", "绑定失败，未查询到该卡信息，请确认卡号是否输入正确"),
    RET_CARD_NONE("1116", "卡号不存在"),
    RET_VERIFIED_IN("1117", "您的证件正在认证或者已经认证成功"),
    RET_BUYNUM_EXCEED("1119", "购买数量超出范围"),
    RET_STOCK_LACK("1120", "库存不足"),
    RET_CARD_ANNORMAL("1121", "卡号未激活或者已停机"),
    RET_SYSTEM_MAINTAIN("1122", "抱歉，每月1号凌晨5点前为系统数据维护时间，暂不受理业务"),
    RET_VERIFIED_NO("1123", "您的账户未完成实名认证，请在认证成功后再进行绑卡"),
    RET_FORMAT_ERROR("1118", "图片上传失败"),
    RET_EDIT_TYPE("1124", "您已续费或者已经更改过套餐，不可变更套餐"),
    RET_CARDNO_RESTORY("1125", "卡号已注销"),
    RET_SYSTEM_LASTTHREE("1126", "月末最后三天不受理套餐变更业务，给您带来不变敬请谅解"),
    RET_SYSTEM_LASTDAY("1127", "月末最后一天不可订购加油包，请您择日再办理此业务"),
    RET_VERIFIED_ERROR("1128", "认证失败，请重新提交资料"),
    RET_BUYPACK_EXCEED("1129", "本月已超出可办理次数，请于下月再办理"),
    RET_NOEXIST_REFID("1011", "不存在该日程！"),
    RET_NOBINDCARD("1012", "该用户没有绑定物联卡！"),
    ERROR_BINDCARD("1013", "您的卡号有误！"),
    RET_BODY_EMPTY("1014", "请求的body为空"),
    RET_NOT_LOGIN("1015", "未登录"),
    RET_DATABASE_ERROR("1016", "数据库异常，请联系管理员"),
    RET_FILE_TOO_BIG("1017", "文件超出了大小"),
    SUCCESS("100", "执行成功"),
    OK("200", "OK"),
    UNAUTHORIZED("401", "Unauthorized"),
    PAYMENT_REQUIRED("402", "Payment Required"),
    FORBIDDEN("403", "Forbidden"),
    NOT_FOUND("404", "Not Found"),
    METHOD_NOT_ALLOWED("405", "Method Not Allowed"),
    REQUEST_TIMEOUT("408", "Request Timeout"),
    GONE("410", "Gone"),
    TOO_MANY_REQUESTS("429", "Too Many Requests"),
    INTERNAL_SERVER_ERROR("500", "Internal Server Error"),
    NOT_IMPLEMENTED("501", "Not Implemented"),
    BAD_GATEWAY("502", "Bad Gateway"),
    SERVICE_UNAVAILABLE("503", "Service Unavailable"),
    GATEWAY_TIMEOUT("504", "Gateway Timeout");

    private String code;
    private String msg;
    private static Map<String, ErrorCodeEnum> enumMap = new HashMap();

    private ErrorCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static ErrorCodeEnum getErrorCode(String code) {
        return (ErrorCodeEnum)enumMap.get(code);
    }

    static {
        EnumSet<ErrorCodeEnum> set = EnumSet.allOf(ErrorCodeEnum.class);
        Iterator var1 = set.iterator();

        while(var1.hasNext()) {
            ErrorCodeEnum each = (ErrorCodeEnum)var1.next();
            enumMap.put(each.getCode(), each);
        }

    }
}
