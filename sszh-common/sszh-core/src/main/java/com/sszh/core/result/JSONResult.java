package com.sszh.core.result;


import com.sszh.core.config.CodeFileConfig;
import com.sszh.core.enums.code.BaseExceptionCodeEnum;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 封装统一返回JSON格式
 */
public class JSONResult<T> extends Result {
    
    private static final long serialVersionUID = 8458347401968527996L;
    
    private T data;

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public JSONResult() {
        super.setCode(BaseExceptionCodeEnum.BASE_100.getCode());
        super.setStatus(Integer.parseInt(BaseExceptionCodeEnum.SUCCESS.getCode()));
        super.setTip(BaseExceptionCodeEnum.BASE_100.getMsg());
    }

    public JSONResult(String tip) {
        super.setCode(BaseExceptionCodeEnum.BASE_100.getCode());
        super.setStatus(Integer.parseInt(BaseExceptionCodeEnum.SUCCESS.getCode()));
        super.setTip(tip);
    }

    public JSONResult(BaseExceptionCodeEnum error) {
        super.setCode(error.getCode());
        super.setTip(error.getMsg());
        super.setStatus(Integer.parseInt(BaseExceptionCodeEnum.FAILED.getCode()));
    }

    public JSONResult(BaseExceptionCodeEnum error, String msg) {
        super.setCode(error.getCode());
        super.setTip(error.getMsg());
        if (msg != null) {
            super.setTip(msg);
        }
        super.setStatus(Integer.parseInt(BaseExceptionCodeEnum.FAILED.getCode()));
    }

    public JSONResult(T data) {
        super.setCode(BaseExceptionCodeEnum.BASE_100.getCode());
        super.setTip(BaseExceptionCodeEnum.BASE_100.getMsg());
        super.setStatus(Integer.parseInt(BaseExceptionCodeEnum.SUCCESS.getCode()));
        this.data = data;
    }

    public JSONResult(BaseExceptionCodeEnum error, T data2) {
        super.setCode(error.getCode());
        super.setTip(error.getMsg());
        if (data2 != null) {
            this.data = data2;
        }
        super.setStatus(Integer.parseInt(BaseExceptionCodeEnum.FAILED.getCode()));
    }

    public JSONResult(T data2, String tip) {
        super.setCode(BaseExceptionCodeEnum.BASE_100.getCode());
        if (StringUtils.isNotBlank(tip)) {
            super.setTip(tip);
        } else {
            super.setTip(BaseExceptionCodeEnum.BASE_100.getMsg());
        }
        super.setStatus(Integer.parseInt(BaseExceptionCodeEnum.SUCCESS.getCode()));
        this.data = data2;
    }

    public JSONResult(String code, String msg, T data) {
        super.setCode(code);
        super.setStatus(Integer.parseInt(BaseExceptionCodeEnum.BASE_100.getCode().equals(code) ? BaseExceptionCodeEnum.SUCCESS.getCode() : BaseExceptionCodeEnum.FAILED.getCode()));
        super.setTip(msg == null ? CodeFileConfig.getValue(this.getStatus() == Integer.parseInt(BaseExceptionCodeEnum.SUCCESS.getCode()) ? code : BaseExceptionCodeEnum.BASE_100.getCode()) : msg);
        this.setData(data);
    }

    public JSONResult(String code, String msg, T data, int totalCount) {
        super.setCode(code);
        super.setStatus(Integer.parseInt(BaseExceptionCodeEnum.BASE_100.getCode().equals(code) ? BaseExceptionCodeEnum.SUCCESS.getCode() : BaseExceptionCodeEnum.FAILED.getCode()));
        super.setTotalCount(totalCount);
        this.setData(data);
    }

    public static <T> JSONResult<T> newFailureResult(String code) {
        return new JSONResult(code, (String)null, (Object)null);
    }

    public static <T> JSONResult<T> newFailureResult(String code, String msg) {
        return new JSONResult(code, msg, (Object)null);
    }

    public static <T> JSONResult<T> newFailureResult(String code, T data) {
        return new JSONResult(code, (String)null, data);
    }

    public static <T> JSONResult<T> newSuccessResult() {
        return new JSONResult(BaseExceptionCodeEnum.BASE_100.getCode(), (String)null, (Object)null);
    }

    public static <T> JSONResult<T> newSuccessResult(T result) {
        return new JSONResult(BaseExceptionCodeEnum.BASE_100.getCode(), (String)null, result);
    }

    public static <T> JSONResult<T> newSuccessResult(T result, int totalCount) {
        return new JSONResult(BaseExceptionCodeEnum.BASE_100.getCode(), (String)null, result, totalCount);
    }
    
}
