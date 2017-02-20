package com.kennedy.java.xml.xsd.reader;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

public class SimpleTypeXsd {
	private String name;
	private String type;
	
	private String pattern;
	private String minInclusive;
	private String maxInclusive;
	private String minLength;
	private String maxLength;
	
	private List<String> enumerics;
	
	public SimpleTypeXsd(){
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getMinInclusive() {
		return minInclusive;
	}

	public void setMinInclusive(String minInclusive) {
		this.minInclusive = minInclusive;
	}

	public String getMaxInclusive() {
		return maxInclusive;
	}

	public void setMaxInclusive(String maxInclusive) {
		this.maxInclusive = maxInclusive;
	}

	public String getMinLength() {
		return minLength;
	}

	public void setMinLength(String minLength) {
		this.minLength = minLength;
	}

	public String getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}

	public List<String> getEnumerics() {
		return enumerics;
	}

	public void setEnumerics(List<String> enumerics) {
		this.enumerics = enumerics;
	}

	public static SimpleTypeXsd createNew(){
		return new SimpleTypeXsd();
	}

	public SimpleTypeXsd withName(String name){
		this.setName(name);
		return this;
	}
	
	public SimpleTypeXsd withType(String type){
		this.setType(type);
		return this;
	}
	
	public SimpleTypeXsd withPattern(String pattern){
		this.setPattern(pattern);
		return this;
	}
	
	public SimpleTypeXsd withMinLength(String number){
		this.setMinLength(number);
		return this;
	}
	
	public SimpleTypeXsd withMaxLength(String number){
		this.setMinLength(number);
		return this;
	}
	
	public SimpleTypeXsd withMinInclusive(String number){
		this.setMinInclusive(number);
		return this;
	}
	
	public SimpleTypeXsd withMaxInclusive(String number){
		this.setMaxInclusive(number);
		return this;
	}
	
	public SimpleTypeXsd withEnumerics(List<String> enums) {
		this.setEnumerics(enums);
		return this;
	}
	
	public SimpleTypeXsd addEnumerics(String enumeric) {
		if (this.getEnumerics() == null) {
			enumerics = new ArrayList<>();
		}
		enumerics.add(enumeric);
		return this;
	}

	public SimpleTypeXsd withValue(String tag, String value) {
		if ("enumeration".equals(tag)) {
			this.addEnumerics(value);
		}
		if ("minInclusive".equals(tag)) {
			this.setMinInclusive(value);
		}
		if ("maxInclusive".equals(tag)) {
			this.setMaxInclusive(value);
		}
		if ("pattern".equals(tag)) {
			this.setPattern(value);
		}
		if ("minLength".equals(tag)) {
			this.setMinLength(value);
		}
		if ("maxLength".equals(tag)) {
			this.setMaxLength(value);
		}
		return this;
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("base=" + type);
		if (StringUtils.isNotBlank(minInclusive)) {
			string.append(" minInclusive=\"" + minInclusive + "\"");
		}
		if (StringUtils.isNotBlank(maxInclusive)) {
			string.append(" maxInclusive=\"" + maxInclusive + "\"");
		}
		if (StringUtils.isNotBlank(pattern)) {
			string.append(" pattern=\"" + pattern + "\"");
		}
		if (StringUtils.isNotBlank(minLength)) {
			string.append(" minLength=\"" + minLength + "\"");
		}
		if (StringUtils.isNotBlank(maxLength)) {
			string.append(" maxLength=\"" + maxLength + "\"");
		}
		if (CollectionUtils.isNotEmpty(enumerics)) {
			string.append(" enumerations=\"" + enumerics.toString() + "\"");
		}
		return string.toString();
	}
	
	
}
