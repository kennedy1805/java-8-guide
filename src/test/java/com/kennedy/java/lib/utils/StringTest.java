package com.kennedy.java.lib.utils;

import org.junit.Test;

public class StringTest {

	@Test
	public void testForCase_isLessOrEquals50PercentOfTotal() {
		System.out.println("countXX = " + countXX("abcxxx"));
	}

	private int countXX(String str) {
		int countXX = 0;
		String sub = "";
		for (int i = 0; i < str.length(); i++) {
			System.out.println("i = " + i);
			System.out.println("before sub = " + sub);
			if (str.charAt(i) == 'x') {
				sub += str.charAt(i);
				System.out.println("after sub = " + sub);
				if (sub.length() != 0) {
					countXX ++;
				}
			} else {
				sub = "";
			}
		}
		
		return countXX;
	}

}
