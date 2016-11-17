package com.mx.util.exception;

/**
 * device传输错误，nfc传输错误
 * @author gaofeng
 *
 */
public class DeviceTransException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4160586081628017357L;
	public DeviceTransException(Exception e) {
		super(e.toString());
	}
}
