package com.sszh.core.enums;

import java.io.Serializable;

/**
 * 公共枚举类
 */
public class BaseEnum implements Serializable {

	private static final long serialVersionUID = 2080324062759589609L;

	private String val;				// 枚举值如：异常码、参数值
	private String content;			// 枚举描述：文字内容

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}