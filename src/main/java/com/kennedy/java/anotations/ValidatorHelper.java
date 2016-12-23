/*
 * Copyright(c)2016 by AXON IVY AG, CH-6000 Lucerne. http://www.axonivy.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * AXON IVY AG. You shall not disclose such confidential information and
 * shall use it only in accordance with the terms of the license
 * agreement you entered into with AXON IVY AG.
 */
package com.kennedy.java.anotations;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;

/**
 * The Class ValidatorHelper.
 */
public class ValidatorHelper {

	/**
	 * Gets the error.
	 *
	 * @param <T>
	 *            the generic type
	 * @param violations
	 * @return the error
	 */
	private static <T> String getError(Set<ConstraintViolation<T>> violations) {
		StringBuilder errorBuilder = new StringBuilder();

		for (ConstraintViolation<T> violation : violations) {
			errorBuilder.append(violation.getMessage() + ";");
		}
		return errorBuilder.toString();
	}

	/**
	 * Valiates model object
	 *
	 * @param obj
	 * @throws ValidationException
	 */
	public static <T> void validate(T obj) throws ValidationException {
		Set<ConstraintViolation<T>> violations = getConstraintViolations(obj);
		if (!violations.isEmpty()) {
			throwException(violations);
		}
	}
	
	/**
	 * Get set ConstraintViolation
	 * @param <T>
	 *            the generic type
	 */
	public static <T> Set<ConstraintViolation<T>> getConstraintViolations(T obj){
		return Validation.buildDefaultValidatorFactory().getValidator().validate(obj);
	}

	public static <T> void throwException(Set<ConstraintViolation<T>> violations) throws ValidationException {
		throw new ValidationException(getError(violations));
	}
}
