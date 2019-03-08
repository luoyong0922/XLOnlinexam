package com.roy.model;

public class RespResult {

	private String code;
	private String message;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public RespResult() {
	}

	public RespResult(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	@Override
	public String toString() {
		return "RespResult [code=" + code + ", message=" + message + "]";
	}
	
	
}
