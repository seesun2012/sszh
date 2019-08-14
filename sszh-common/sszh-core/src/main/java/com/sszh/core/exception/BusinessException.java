package com.sszh.core.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -3442368262473318332L;
	
	private String code;
	public BusinessException(String code, String message) {
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
