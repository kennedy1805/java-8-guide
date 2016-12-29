package com.kennedy.java.yaml.converter.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class SalaryItemType {
	
	private String name;
	private String code;
	private String formula;
	private Map<String, String> description;
	private List<Variable> variables;
	
	public SalaryItemType(){
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public Map<String, String> getDescription() {
		return description;
	}
	public void setDescription(Map<String, String> description) {
		this.description = description;
	}
	
	public List<Variable> getVariables() {
		return variables;
	}
	public void setVariables(List<Variable> variables) {
		this.variables = variables;
	}
	public static SalaryItemType createNew() {
		return new SalaryItemType();
	}
	public SalaryItemType withName(String name) {
		this.setName(name);
		return this;
	}
	public SalaryItemType withCode(String code) {
		this.setCode(code);
		return this;
	}
	public SalaryItemType withFormula(String formula) {
		this.setFormula(formula);
		return this;
	}
	public SalaryItemType withDescription(Map<Locale, String> descriptions) {
		this.setDescription(description);
		return this;
	}
	@Override
	public String toString() {
		return "SalaryItemType [name=" + name + ", code=" + code + ", formula=" + formula + ", description="
				+ description + "]";
	}
	public SalaryItemType putDescription(String locale, String value) {
		if (description == null) {
			description = new LinkedHashMap<>();
		}
		this.getDescription().put(locale, value);
		return this;
	}
	public SalaryItemType addVariable(String name, String value, boolean isFinal) {
		if (variables == null) {
			variables = new ArrayList<>();
		}
		this.getVariables().add(new Variable(name, value, isFinal, null, null));
		return this;
	}
	
}
