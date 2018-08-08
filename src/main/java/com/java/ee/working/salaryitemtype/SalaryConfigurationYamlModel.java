package com.java.ee.working.salaryitemtype;

import java.io.Serializable;
import java.util.List;

import com.java.ee.working.beans.Mapped;

public class SalaryConfigurationYamlModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Mapped
	private Long id;
	
	@Mapped
	private String name;
	
	@Mapped
	private String code;
	
	@Mapped
	private String description;
	
	@Mapped
	private List<VariableYamlModel> variables;
	
	private String salaryItemTypeCodes;
	
	private String salaryItemTypeGroupCodes;
	
	public SalaryConfigurationYamlModel() {
		// no-arg constructor for json library to construct current object
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<VariableYamlModel> getVariables() {
		return variables;
	}

	public void setVariables(List<VariableYamlModel> variables) {
		this.variables = variables;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSalaryItemTypeCodes() {
		return salaryItemTypeCodes;
	}

	public void setSalaryItemTypeCodes(String salaryItemTypeCodes) {
		this.salaryItemTypeCodes = salaryItemTypeCodes;
	}

	public String getSalaryItemTypeGroupCodes() {
		return salaryItemTypeGroupCodes;
	}

	public void setSalaryItemTypeGroupCodes(String salaryItemTypeGroupCodes) {
		this.salaryItemTypeGroupCodes = salaryItemTypeGroupCodes;
	}

}
