package com.java.ee.rest.handler.exeption;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class MasterDataException extends LocalizedRuntimeException {

	private static final long serialVersionUID = 1L;
	private static final String MASTER_DATA_RESOURCE_BUNDLE_PATH = "messages/master_data/error_message";
	
	public MasterDataException(String code, Object... params) {
		super(code, MASTER_DATA_RESOURCE_BUNDLE_PATH, params);
	}

}
