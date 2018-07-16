package com.java.ee.rest.handler.exeption;

import java.util.Locale;

import com.java.ee.rest.configuration.ErrorMessageBundle;

public class LocalizedRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String code;
	private String resouceBundlePath;
	private Object[] params;

	public String getLocalizedMessage(Locale preferredLanguage) {
		return ErrorMessageBundle.byPath(this.resouceBundlePath)
				.inClassPath(Thread.currentThread().getContextClassLoader()).build()
				.get(code, preferredLanguage, params);
	}

	public LocalizedRuntimeException(String code, String resourceBundlePath, Object... params) {
		this.code = code;
		this.resouceBundlePath = resourceBundlePath;
		this.params = params;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getResouceBundlePath() {
		return resouceBundlePath;
	}

	public void setResouceBundlePath(String resouceBundlePath) {
		this.resouceBundlePath = resouceBundlePath;
	}
}
