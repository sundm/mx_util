package com.mx.util.exception;

/**
 * device连接错误，nfc打开失败
 * @author gaofeng
 *
 */
public class DeviceConnectException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4913073320496452747L;
	
	public DeviceConnectException(Exception e){
		super(e.toString());
	}
}
