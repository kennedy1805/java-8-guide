package com.java.ee.working.salaryitemtype;

import com.java.ee.working.beans.Translator;

public class ObjectToStringTranslator implements Translator<Object, Object>{

	@Override
	public Object translateIn(Object object) {
		return object != null ? object.toString() : null;
	}

	@Override
	public Object translateOut(Object object) {
		return object;
	}

}
