package com.kennedy.java.lib.yaml.converter.model;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Variable {
	private String name;
	private String value;
	private Boolean isFinal;
	private String validFrom;
	private String validTo;

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(name)
				.append(value)
				.append(isFinal)
				.append(validFrom)
				.append(validTo)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Variable other = (Variable) obj;
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
		return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
    			.append("name", name)
    			.append("value", value)
    			.append("isFinal", isFinal)
    			.toString();
	}
	
}
