package com.kennedy.java.utils.BigDecimal;

import java.math.BigDecimal;

public class BigDecimalExtensionUtils {
	public static boolean isLessOrEquals50PercentOfTotal(BigDecimal input, BigDecimal total) {
		BigDecimal aHaflOfTotal = total.divide(new BigDecimal("2"));
		return aHaflOfTotal.stripTrailingZeros().compareTo(input.stripTrailingZeros()) < 0 ? false : true;
	}
}
