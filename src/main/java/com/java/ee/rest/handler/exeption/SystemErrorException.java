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

import javax.ws.rs.core.Response.Status;

public class SystemErrorException extends RestfulWebServiceException {
    private static final long serialVersionUID = 1L;

    public SystemErrorException(String msg) {
    	super(Status.INTERNAL_SERVER_ERROR.getStatusCode(), msg);
    }
    
    public SystemErrorException(String msg, Throwable ex) {
    	super(Status.INTERNAL_SERVER_ERROR.getStatusCode(), msg, ex);
    }
}
