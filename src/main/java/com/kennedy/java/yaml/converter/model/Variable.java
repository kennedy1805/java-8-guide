package com.kennedy.java.yaml.converter.model;

public class Variable {
	private String name;
	private String value;
	private Boolean isFinal;
	private String validFrom;
	private String validTo;

	public Variable() {
	}
	public Variable(String name, String value, boolean isFinal, String from, String to) {
		this.setName(name);
		this.setValue(value);
		this.setIsFinal(isFinal);
		this.setValidFrom(from);
		this.setValidTo(to);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Boolean getIsFinal() {
		return isFinal;
	}
	public void setIsFinal(Boolean isFinal) {
		this.isFinal = isFinal;
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
	
	@Override
	public String toString() {
		return "Variable [name=" + name + ", value=\"" + value + "\", isFinal=" + isFinal + ", validFrom=" + validFrom
				+ ", validTo=" + validTo + "]";
	}
	
	public String toJson() {
		StringBuilder json = new StringBuilder();
		json.append("{name: " + name);
		json.append(", value: \"\"" + value + "\"\"");
		json.append(", isFinal: " + isFinal);
		json.append(", validFrom: " + validFrom);
		json.append(", validTo: " + validTo);
		json.append("}");
		return "{name: " + name + ", value: " + value + ", isFinal:" + isFinal + ", validFrom:" + validFrom
				+ ", validTo:" + validTo + "}";
	}
	
}
