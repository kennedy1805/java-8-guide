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

public class NotFoundException extends RestfulWebServiceException {
    private static final long serialVersionUID = 1L;
    
    public NotFoundException(String msg) {
    	super(Status.NOT_FOUND.getStatusCode(), msg);
    }
    
    public NotFoundException(String msg, Throwable ex) {
    	super(Status.NOT_FOUND.getStatusCode(), msg, ex);
    }
}
