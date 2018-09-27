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
	
	@Test
	public void extract_seq_from_link() {
		String link = "/luz_accounting/api/e3df4e2c-f1a3-4ba3-8202-6282d5ded65f/companies/1/bookings/41/booking-details/seq=2";
		Integer seq = Integer.parseInt(link.substring(link.indexOf("seq=") + 4));
		System.out.println("seq=" + seq);
	}

}
