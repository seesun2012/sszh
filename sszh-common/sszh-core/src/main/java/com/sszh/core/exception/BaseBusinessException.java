package com.sszh.core.exception;

/**
 * 业务运行时异常基类
 */
public class BaseBusinessException extends RuntimeException {

	private static final long serialVersionUID = -3442368262473318332L;
	
	private String code;
	public BaseBusinessException(String code, String message) {
		super(message);
		this.code = code;

	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

	public String getCode() {
		return code;
	}


}
