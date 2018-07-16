package com.java.ee.rest.handler.exeption;

import java.util.function.Predicate;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class AccountingException extends LocalizedRuntimeException  {
	
	private static final long serialVersionUID = 1L;
	private static final String ACCOUNTING_RESOURCE_BUNDLE_PATH = "messages/accounting/error_message";
	
	private final Object[] params;
	
	public AccountingException(String code, Object... params) {
		super(code, ACCOUNTING_RESOURCE_BUNDLE_PATH, params);
		this.params = params;
	}

	public Object[] getParams() {
		return params;
	}
	
	public static <T> void throwIf(Predicate<T> predicate, T t, String message) {
		if(predicate.test(t)) {
			throw new AccountingException(message);
		}
	}
}
