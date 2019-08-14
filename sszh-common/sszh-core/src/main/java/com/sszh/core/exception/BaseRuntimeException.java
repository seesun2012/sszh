package com.sszh.core.exception;

import com.sszh.core.config.ExceptionConfig;
import com.sszh.core.enums.code.BaseExceptionCodeEnum;

import java.util.Map;

/**
 * 运行时异常基类
 *
 * @author Bob
 *
 */
public class BaseRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * 异常配置
	 */
	private ExceptionConfig config;

	public BaseRuntimeException(String code) {
		this(code, null, (Map<String, String>) null);
	}

	public BaseRuntimeException(Throwable throwable) {
		this(BaseExceptionCodeEnum.BASE_104.getCode(), throwable, (Map<String, String>) null);
	}

	public BaseRuntimeException(String code, String desc) {
		config = new ExceptionConfig(code, desc);
	}

	public BaseRuntimeException(String code, Throwable t) {
		this(code, t, (Map<String, String>) null);
	}

	public BaseRuntimeException(String code, Map<String, String> args) {
		this(code, null, args);
	}

	public BaseRuntimeException(String code, Throwable t, Map<String, String> args) {
		config = new ExceptionConfig(code, t, args);
	}

	/**
	 * 获取描述信息
	 */
	@Override
	public String getMessage() {
		return this.getDesc();
	}

	/**
	 * 获取编码
	 * 
	 * @return
	 */
	public String getCode() {
		return config.getCode();
	}

	/**
	 * 获取描述信息
	 * 
	 * @return
	 */
	public String getDesc() {
		return config.getDesc();
	}

	/**
	 * 获取堆栈信息
	 * 
	 * @return
	 */
	public String getStackMessage() {
		return config.getStackMessage();
	}

}
