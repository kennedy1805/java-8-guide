package com.java.ee.working.salaryitemtype;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VariableComparable {
	private String name;
	private String devValue;
	private String masterValue;
	private String isFinal;
	private String json;
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(name)
				.append(devValue)
				.append(isFinal)
				.append(masterValue)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		VariableComparable other = (VariableComparable) obj;
		return new EqualsBuilder()
				.append(name, other.name)
				.append(devValue, other.devValue)
				.append(masterValue, other.masterValue)
				.append(isFinal, other.isFinal)
				.isEquals();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
    			.append("name", name)
    			.append("firstValue", devValue)
    			.append("secondValue", masterValue)
    			.append("isFinal", isFinal)
    			.toString();
	}
}
