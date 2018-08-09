package com.java.ee.working.salaryitemtype;

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
public class TagComparable {
	private String key;
	private String devValue;
	private String masterValue;
}
