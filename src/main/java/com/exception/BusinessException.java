package com.exception;


public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5717085985703058952L;
	
	private String status;
	
	private Object[] args;
	
	public BusinessException(String status, Object ...args) {
		this.args = args;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public Object[] getArgs() {
		return args;
	}


	public void setArgs(Object[] args) {
		this.args = args;
	}
	
}

