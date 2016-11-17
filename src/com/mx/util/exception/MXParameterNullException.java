package com.mx.util.exception;

public class MXParameterNullException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2888384356196613094L;

	public MXParameterNullException(Exception e) {
		super(e.toString());
	}
	
	public MXParameterNullException(String data) {
		super(data);
	}
}
