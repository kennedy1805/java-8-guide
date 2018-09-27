package com.kennedy.java.lib.yaml.converter.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.java.ee.working.beans.Mapped;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalaryItemType {
	
	@Mapped
	private String code;
	
	@Mapped
	private String name;
	
	@Mapped
	private String formula;
	
	private List<Variable> variables;
	
	private String description;
	
	private Map<Locale, String> i18n;
	
	private String tag;

	private Map<Locale, String> tagI18N;
	
	private final List<String> accountingVariables = Arrays.asList("ACCOUNTING_GROUP", "LINK_TYPE");
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(name)
				.append(code)
				.append(formula)
				.append(description)
				.append(i18n)
				.append(tag)
				.append(tagI18N)
				.append(variables)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		SalaryItemType other = (SalaryItemType) obj;
		return new EqualsBuilder()
				.append(name, other.name)
				.append(code, other.code)
				.append(formula, other.formula)
				.append(description, other.description)
				.append(i18n, other.i18n)
				.append(tag, other.tag)
				.append(tagI18N, other.tagI18N)
				.append(variables, other.variables)
				.isEquals();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
    			.append("name", name)
    			.append("code", code)
    			.append("formula", formula)
    			.append("description", description)
    			.append("i18n", i18n)
    			.append("tag", tag)
    			.append("tagI18N", tagI18N)
    			.append("validTo", variables)
    			.toString();
	}
	
	public static class SalaryItemTypeBuilder {
        public SalaryItemTypeBuilder putDescription(String locale, String value) {
        	if (i18n == null) {
        		i18n = new LinkedHashMap<>();
    		}
        	i18n.put(Locale.forLanguageTag(locale), value);
    		return this;
        }
        
        public SalaryItemTypeBuilder addVariable(String name, String value, boolean isFinal) {
    		if (variables == null) {
    			variables = new ArrayList<>();
    		}
    		variables.add(new Variable(name, value, isFinal, null, null));
    		return this;
    	}
    }
	
	public boolean containsAccountingVariables() {
		if (CollectionUtils.isEmpty(variables)) {
			return false;
		}
		for (Variable var : variables) {
			if (accountingVariables.contains(var.getName())) {
				return true;
			}
		}
		return false;
	}
	
	public String getVariableValueByName(String varName) {
		if (CollectionUtils.isEmpty(variables)) {
			return null;
		}
		for (Variable var : variables) {
			if (varName.equals(var.getName())) {
				return var.getValue();
			}
		}
		return null;
	}
	 
}
