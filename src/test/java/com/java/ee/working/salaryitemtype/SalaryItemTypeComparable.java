package com.java.ee.working.salaryitemtype;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

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
	private Boolean differentFormula;
	private String variable;
	private String tags;
	private String descriptions;
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(code)
				.append(differentFormula)
				.append(variable)
				.append(tags)
				.append(descriptions)
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
				.append(differentFormula, other.differentFormula)
				.append(variable, other.variable)
				.append(tags, other.tags)
				.append(descriptions, other.descriptions)
				.isEquals();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
    			.append("code", code)
    			.append("differentFormula", differentFormula)
    			.append("variable", variable)
    			.append("tags", tags)
    			.append("descriptions", descriptions)
    			.toString();
	}

	public boolean isEmpty() {
		List<Field> fields = Arrays.asList(SalaryItemTypeComparable.class.getDeclaredFields());
		try {
			for (Field declaredField : fields) {
				declaredField.setAccessible(true);
				if (declaredField.get(this) != null) {
					return true;
				}
			}
		} catch (Exception e) {

		}
		return false;
	}
}
