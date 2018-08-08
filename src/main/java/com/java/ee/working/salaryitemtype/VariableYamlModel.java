package com.java.ee.working.salaryitemtype;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.java.ee.working.beans.Mapped;

public class VariableYamlModel {
	
	@Mapped
	private String name;
	
	@Mapped(translator = ObjectToStringTranslator.class)
	private Object value;
	
	@Mapped
	private Boolean isFinal;
	
	@Mapped(translator = LocalDateToStringTranslator.class)
	private String validFrom;
	
	@Mapped(translator = LocalDateToStringTranslator.class)
	private String validTo;
	
	public VariableYamlModel() {
		// no-arg constructor for json library to construct current object
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(String validFrom) {
		this.validFrom = validFrom;
	}

	public String getValidTo() {
		return validTo;
	}

	public void setValidTo(String validTo) {
		this.validTo = validTo;
	}

	public Boolean getIsFinal() {
		return isFinal;
	}

	public void setIsFinal(Boolean isFinal) {
		this.isFinal = isFinal;
	}
	
	public static VariableYamlModel createNew() {
		return new VariableYamlModel();
	}

	public VariableYamlModel withName(String name) {
		this.name = name;
		return this;
	}

	public VariableYamlModel withValue(Object value) {
		this.value = value;
		return this;
	}

	public VariableYamlModel withValidFrom(String validFrom) {
		this.validFrom = validFrom;
		return this;
	}

	public VariableYamlModel withValidTo(String validTo) {
		this.validTo = validTo;
		return this;
	}

	public VariableYamlModel withIsFinal(Boolean isFinal) {
		this.isFinal = isFinal;
		return this;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(name).append(name).append(isFinal)
				.append(validFrom).append(validTo)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		VariableYamlModel other = (VariableYamlModel) obj;
		return new EqualsBuilder()
				.append(name, other.name)
				.append(value, other.value)
				.append(isFinal, other.isFinal)
				.append(validFrom, other.validFrom)
				.append(validTo, other.validTo)
				.isEquals();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getName()).append(" {");
		builder.append(Constants.NEW_LINE_AND_TAB).append("name: ").append(name);
		builder.append(Constants.NEW_LINE_AND_TAB).append("value: ").append(value);
		builder.append(Constants.NEW_LINE_AND_TAB).append("isFinal: ").append(isFinal);
		builder.append(Constants.NEW_LINE_AND_TAB).append("validFrom: ").append(validFrom);
		builder.append(Constants.NEW_LINE_AND_TAB).append("validTo: ").append(validTo);
		builder.append("\n}");
		return builder.toString();
	}
	
	
}
