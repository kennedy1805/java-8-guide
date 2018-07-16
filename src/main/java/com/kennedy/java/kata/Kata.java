package com.kennedy.java.kata;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Kata {
	public static long stairsIn20(int[][] stairs) {
		return Arrays.stream(stairs).mapToLong(array -> Arrays.stream(array).sum()).sum() * 20;
	}

	public static String twoSort(String[] strings) {
		return String.join("***", Arrays.stream(strings).sorted().findFirst().orElse("").split(""));
	}

	public static String countingSheep(int num) {
		return IntStream.rangeClosed(1, num).mapToObj(i -> i + " sheep...").collect(Collectors.joining());
	}

	public static double find_average(int[] array) {
		return Arrays.stream(array).mapToDouble(arr -> arr).average().orElse(Double.valueOf("0.00"));
	}

	public static String abbrevName(String name) {
		return Arrays.stream(name.toUpperCase().split(" ")).map(nx -> nx.charAt(0) + ".").collect(Collectors.joining()).replaceFirst(".$", "");
	}

	public static int makeNegative(final int x) {
		return Math.abs(x) * -1;
	}
	
	public static int getAverage(int[] marks){
		return (int) Arrays.stream(marks).average().orElse(0);
	}

	public static int strCount(String str, char letter) {
		return str.chars().mapToObj(i -> (char)i)
				.filter(c -> c.equals(letter))
				.collect(Collectors.counting()).intValue();
	}
}
