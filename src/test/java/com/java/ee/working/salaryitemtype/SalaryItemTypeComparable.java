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
public class SalaryItemTypeComparable {
	private String code;
	private String variable;
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(code)
				.append(variable)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		SalaryItemTypeComparable other = (SalaryItemTypeComparable) obj;
		return new EqualsBuilder()
				.append(code, other.code)
				.append(variable, other.variable)
				.isEquals();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
    			.append("code", code)
    			.append("variable", variable)
    			.toString();
	}
}
