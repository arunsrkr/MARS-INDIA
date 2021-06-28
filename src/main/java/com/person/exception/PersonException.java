package com.person.exception;

public class PersonException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	public PersonException(String msg) {
		this.msg = msg;
	
	}

	public String getMsg() {
		return msg;
	}
	
	
	
}
