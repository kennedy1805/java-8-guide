package com.java.ee.working.salaryitemtype;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.java.ee.working.beans.Mapped;

public class SalaryItemTypeGroupYamlModel {

	@Mapped
	private String name;
	
	@Mapped
	private String code;
	
	private String salaryItemTypeCodes;
	
	@Mapped
	private String description;
	
	@Mapped
	private List<VariableYamlModel> variables;
	
	public static SalaryItemTypeGroupYamlModel createNew() {
		return new SalaryItemTypeGroupYamlModel();
	}

	public SalaryItemTypeGroupYamlModel() {
		// no-arg constructor for json library to construct current object
	}

	public String getName() {
		return name;
	}

	public SalaryItemTypeGroupYamlModel withName(String name) {
		this.name = name;
		return this;
	}

	public String getCode() {
		return code;
	}

	public SalaryItemTypeGroupYamlModel withCode(String code) {
		this.code = code;
		return this;
	}

	public SalaryItemTypeGroupYamlModel withSalaryItemTypeCodes(String salaryItemTypeCodes) {
		this.salaryItemTypeCodes = salaryItemTypeCodes;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public SalaryItemTypeGroupYamlModel withDescription(String description) {
		this.description = description;
		return this;
	}

	public List<VariableYamlModel> getVariables() {
		return variables;
	}

	public SalaryItemTypeGroupYamlModel withVariables(List<VariableYamlModel> variables) {
		this.variables = variables;
		return this;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setVariables(List<VariableYamlModel> variables) {
		this.variables = variables;
	}
	
	public String getSalaryItemTypeCodes() {
		return salaryItemTypeCodes;
	}

	public void setSalaryItemTypeCodes(String salaryItemTypeCodes) {
		this.salaryItemTypeCodes = salaryItemTypeCodes;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(code)
				.append(description)
				.append(name)
				.append(salaryItemTypeCodes)
				.append(variables)
				.toHashCode();
	}
	private boolean isEqualTo(Collection<?> a, Collection<?> b) {
		if (a != null && b != null) return CollectionUtils.isEqualCollection(a, b);
		return (a == null && b == null);
    }	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		SalaryItemTypeGroupYamlModel other = (SalaryItemTypeGroupYamlModel) obj;
		return new EqualsBuilder()
				.append(code, other.code)
				.append(description, other.description)
				.append(name, other.name)
				.appendSuper(isEqualTo(salaryItemTypeCodes, other.salaryItemTypeCodes))
				.appendSuper(isEqualTo(variables, other.variables))
				.isEquals();
	}

	private boolean isEqualTo(String first, String second) {
		List<String> sitCodesO1 = Arrays.asList(first.replace(" ", "").split(","));
		List<String> sitCodesO2 = Arrays.asList(second.replace(" ", "").split(","));
		return sitCodesO1.size() == sitCodesO2.size() && sitCodesO1.containsAll(sitCodesO2);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getName()).append(" {");
		builder.append(Constants.NEW_LINE_AND_TAB).append("code: ").append(code);
		builder.append(Constants.NEW_LINE_AND_TAB).append("name: ").append(name);
		builder.append(Constants.NEW_LINE_AND_TAB).append("description: ").append(description);
		builder.append(Constants.NEW_LINE_AND_TAB).append("salaryItemTypeCodes: ").append(salaryItemTypeCodes);
		builder.append(Constants.NEW_LINE_AND_TAB).append("variables: ").append(variables);
		builder.append("\n}");
		return builder.toString();
	}

}
