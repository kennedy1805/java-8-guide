package com.kennedy.java.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;

import com.kennedy.java.utils.BigDecimal.BigDecimalExtensionUtils;

public class BigDecimalLearnTest {

	@Test
	public void testForCase_isLessOrEquals50PercentOfTotal() {
		BigDecimal total = new BigDecimal("0.01086");
		
		BigDecimal input = BigDecimal.ZERO;
		assertTrue(BigDecimalExtensionUtils.isLessOrEquals50PercentOfTotal(input, total));
		
		input = new BigDecimal("0.00500");
		assertTrue(BigDecimalExtensionUtils.isLessOrEquals50PercentOfTotal(input, total));
		
		input = new BigDecimal("0.00543");
		assertTrue(BigDecimalExtensionUtils.isLessOrEquals50PercentOfTotal(input, total));
		
		input = new BigDecimal("0.00544");
		assertFalse(BigDecimalExtensionUtils.isLessOrEquals50PercentOfTotal(input, total));
		
		total = new BigDecimal("100.00");
		input = new BigDecimal("49.99999999999");
		assertTrue(BigDecimalExtensionUtils.isLessOrEquals50PercentOfTotal(input, total));
	}

	@Test
	public void test() {
		System.out.print("a");
		try {
		System.out.print("b");
		throw new IllegalArgumentException();
		} catch (RuntimeException e) {
		System.out.print("c");
		} finally {
		System.out.print("d");
		}
		System.out.print("e");
	}

}
