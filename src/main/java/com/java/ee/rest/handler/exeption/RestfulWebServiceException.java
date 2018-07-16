/*
 * Copyright(c)2016 by AXON IVY AG, CH-6000 Lucerne. http://www.axonivy.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * AXON IVY AG. You shall not disclose such confidential information and
 * shall use it only in accordance with the terms of the license
 * agreement you entered into with AXON IVY AG.
 */
package com.java.ee.rest.handler.exeption;

public class RestfulWebServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final int code;
	
	public RestfulWebServiceException(int code, String msg) {
		super(msg);
		this.code = code;
	}
	
	public RestfulWebServiceException(int code, String msg, Throwable ex) {
		super(msg, ex);
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
