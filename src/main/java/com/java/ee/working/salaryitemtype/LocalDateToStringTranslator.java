package com.java.ee.working.salaryitemtype;

import java.time.LocalDate;

import com.java.ee.working.beans.Translator;

public class LocalDateToStringTranslator implements Translator<LocalDate, String>{

	@Override
	public String translateIn(LocalDate localDate) {
		return localDate != null ? localDate.toString() : null;
	}

	@Override
	public LocalDate translateOut(String string) {
		return DateUtils.stringToLocalDateMultipleFormatters(string);
	}
}
