package com.java.ee.working.salaryitemtype;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.LocaleUtils;

import com.java.ee.working.beans.Translator;

public class SalaryItemTypeYamlTranslator implements Translator<Map<Locale, String>, Map<String, String>>{

	@Override
	public Map<String, String> translateIn(Map<Locale, String> descriptionFromSIT) {
		Map<String, String> description = new HashMap<>();
		for(Entry<Locale, String> entry : descriptionFromSIT.entrySet()){
			description.put(entry.getKey().toString(), entry.getValue());
		}
		return description;
	}

	@Override
	public Map<Locale, String> translateOut(Map<String, String> descriptionFromYaml) {
		Map<Locale, String> i18ns = new HashMap<>();
		if (MapUtils.isNotEmpty(descriptionFromYaml)) {
			for (Entry<String, String> entry : descriptionFromYaml.entrySet()) {
				i18ns.put(LocaleUtils.toLocale(entry.getKey()), entry.getValue());
			}
		}
		return i18ns;
	}
}
