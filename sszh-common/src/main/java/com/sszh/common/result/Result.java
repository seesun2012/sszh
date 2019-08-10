package com.sszh.common.result;

import java.io.Serializable;

public class Result implements Serializable {

    private static final long serialVersionUID = -4147622404034374851L;
    private String tip;
    private int status;
    private String code;
    private int totalCount;

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public String getTip() {
        return this.tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Result() {
    }
    
}
