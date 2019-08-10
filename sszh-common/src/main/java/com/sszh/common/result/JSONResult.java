package com.sszh.common.result;

import com.sszh.common.code.ErrorCodeEnum;
import com.sszh.common.config.CodeFileConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 封装统一返回JSON格式
 */
public class JSONResult<T> extends Result {
    
    private static final long serialVersionUID = 8458347401968527996L;
    
    @Autowired
    private CodeFileConfig codeFileConfig;

    private T data;

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public JSONResult() {
        super.setCode("100");
        super.setStatus(1);
        super.setTip("执行成功！");
    }

    public JSONResult(String tip) {
        super.setCode("100");
        super.setStatus(1);
        super.setTip(tip);
    }

    public JSONResult(ErrorCodeEnum error) {
        super.setCode(error.getCode());
        super.setTip(error.getMsg());
        super.setStatus(0);
    }

    public JSONResult(ErrorCodeEnum error, String msg) {
        super.setCode(error.getCode());
        super.setTip(error.getMsg());
        if (msg != null) {
            super.setTip(msg);
        }
        super.setStatus(0);
    }

    public JSONResult(T data) {
        super.setCode("100");
        super.setTip("执行成功");
        super.setStatus(1);
        this.data = data;
    }

    public JSONResult(ErrorCodeEnum error, T data2) {
        super.setCode(error.getCode());
        super.setTip(error.getMsg());
        if (data2 != null) {
            this.data = data2;
        }
        super.setStatus(0);
    }

    public JSONResult(T data2, String tip) {
        super.setCode("100");
        if (StringUtils.isNotBlank(tip)) {
            super.setTip(tip);
        } else {
            super.setTip("执行成功");
        }
        super.setStatus(1);
        this.data = data2;
    }

    public JSONResult(String code, String msg, T data) {
        super.setCode(code);
        super.setStatus("100".equals(code) ? 1 : 0);
        super.setTip(msg == null ? codeFileConfig.getValue(this.getStatus() == 0 ? code : "100") : msg);
        this.setData(data);
    }

    public JSONResult(String code, String msg, T data, int totalCount) {
        super.setCode(code);
        super.setStatus("100".equals(code) ? 1 : 0);
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
        return new JSONResult("100", (String)null, (Object)null);
    }

    public static <T> JSONResult<T> newSuccessResult(T result) {
        return new JSONResult("100", (String)null, result);
    }

    public static <T> JSONResult<T> newSuccessResult(T result, int totalCount) {
        return new JSONResult("100", (String)null, result, totalCount);
    }
    
}
