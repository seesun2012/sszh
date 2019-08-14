package com.sszh.core.config;

import com.sszh.core.exception.BaseException;
import com.sszh.core.exception.BaseRuntimeException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Map;


/**
 * 异常配置
 *
 * @author Bob
 * @Date 2017-06-20
 *
 */
public class ExceptionConfig implements Serializable{
	
	private static final long serialVersionUID = -3473837674910928391L;
	
	@Autowired
	private CodeFileConfig codeFileConfig;

	/**
	 * 编码
	 */
	private String code;

	/**
	 * 描述
	 */
	private String desc;

	/**
	 * 原始异常
	 */
	private Throwable throwable;

	public ExceptionConfig(String code) {
		this(code, null, null);
	}

	public ExceptionConfig(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public ExceptionConfig(String code, Throwable throwable) {
		this(code, throwable, (Map<String, String>) null);
	}

	public ExceptionConfig(String code, Throwable throwable, Map<String, String> args) {
		if (throwable instanceof BaseException) {
			BaseException be = (BaseException) throwable;
			this.code = be.getCode();
			this.desc = be.getDesc();
			this.throwable = be.getCause();
		}
		else if (throwable instanceof BaseRuntimeException) {
			BaseRuntimeException bre = (BaseRuntimeException) throwable;
			this.code = bre.getCode();
			this.desc = bre.getDesc();
			this.throwable = bre.getCause();
		}
		else {
			this.code = code;
			this.desc = codeFileConfig.getValue(String.valueOf(code));
			this.throwable = throwable;
		}
	}

	/**
	 * 获取编码
	 * 
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 获取异常描述信息
	 * 
	 * @return
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * 获取原始异常
	 * 
	 * @return
	 */
	public Throwable getThrowable() {
		return throwable;
	}

	/**
	 * 获取堆栈信息
	 * 
	 * @return
	 */
	public String getStackMessage() {
		if (throwable != null) {
			return ExceptionUtils.getStackTrace(throwable);
		}
		return null;
	}

}
