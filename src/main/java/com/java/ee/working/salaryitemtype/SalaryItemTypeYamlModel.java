package com.java.ee.working.salaryitemtype;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.java.ee.working.beans.Mapped;

public class SalaryItemTypeYamlModel {

	@Mapped
	private String name;
	
	@Mapped
	private String code;
	
	@Mapped
	private String formula;
	
	@Mapped(name = "i18n", translator = SalaryItemTypeYamlTranslator.class)
	private Map<String, String> description;
	
	@Mapped(name = "tagI18N", translator = SalaryItemTypeYamlTranslator.class)
	private Map<String, String> tag;
	
	@Mapped
	private List<VariableYamlModel> variables;
	
	public SalaryItemTypeYamlModel() {
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

	public List<VariableYamlModel> getVariables() {
		return variables;
	}

	public void setVariables(List<VariableYamlModel> variables) {
		this.variables = variables;
	}

	public Map<String, String> getTag() {
		return tag;
	}

	public void setTag(Map<String, String> tag) {
		this.tag = tag;
	}
	
	private boolean isEqualTo(Collection<?> a, Collection<?> b) {
		if (a != null && b != null) return CollectionUtils.isEqualCollection(a, b);
		return (a == null && b == null);
    }

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(code)
				.append(description)
				.append(tag)
				.append(name)
				.append(formula)
				.append(variables)
				.toHashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		SalaryItemTypeYamlModel other = (SalaryItemTypeYamlModel) obj;
		return new EqualsBuilder()
				.appendSuper(super.equals(obj))
				.append(code, other.code)
				.append(description, other.description)
				.append(tag, other.tag)
				.append(name, other.name)
				.append(formula, other.formula)
				.appendSuper(isEqualTo(variables, other.variables))
				.isEquals();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getClass().getName()).append(" {");
		builder.append(Constants.NEW_LINE_AND_TAB).append("code: ").append(code);
		builder.append(Constants.NEW_LINE_AND_TAB).append("name: ").append(name);
		builder.append(Constants.NEW_LINE_AND_TAB).append("description: ").append(description);
		builder.append(Constants.NEW_LINE_AND_TAB).append("tag: ").append(tag);
		builder.append(Constants.NEW_LINE_AND_TAB).append("formula: ").append(formula);
		builder.append(Constants.NEW_LINE_AND_TAB).append("variables: ").append(variables);
		builder.append("\n}");
		return builder.toString();
	}
}
