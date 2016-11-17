package com.mx.util.exception;

/**
 * 数据转换出错
 * @author gaofeng
 *
 */
public class MXDataFormatExcetion extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6671038432908215081L;

	public MXDataFormatExcetion(Exception e) {
		super(e);
	}

	public MXDataFormatExcetion(String data) {
		super(data);
	}
}
