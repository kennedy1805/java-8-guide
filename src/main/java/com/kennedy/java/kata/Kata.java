package com.kennedy.java.kata;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

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

	public static double findAverage(int[] array) {
		IntStream.of(array).average().orElse(0);
		return Arrays.stream(array).mapToDouble(arr -> arr).average().orElse(Double.valueOf("0.00"));
	}

	public static String abbrevName(String name) {
		return Arrays.stream(name.toUpperCase().split(" ")).map(nx -> nx.charAt(0) + ".").collect(Collectors.joining()).replaceFirst(".$", "");
	}

	public static int makeNegative(final int x) {
		return Math.abs(x) * -1;
	}

	public static int getAverage(int[] marks) {
		return (int) Arrays.stream(marks).average().orElse(0);
	}

	public static int strCount(String str, char letter) {
		return str.chars().mapToObj(i -> (char) i).filter(c -> c.equals(letter)).collect(Collectors.counting()).intValue();
	}

	public static int rowSumOddNumbers(int n) {
		if (n == 1) {
			return 1;
		}
		int sum = 0;
		int noE = n - 1;
		int startNo = noE * n + 1;
		int endNo = startNo + noE * 2;

		int couple = n / 2;
		int totalStartEnd = startNo + endNo;
		int mod = n % 2 == 0 ? 0 : startNo + couple * 2;
		sum = totalStartEnd * couple + mod;
		return sum;
	}

	public static String toAlternativeString(String string) {
		return Arrays.stream(string.split("")).map(x -> {
			return x.toUpperCase() == x ? x.toLowerCase() : x.toUpperCase();
		}).collect(Collectors.joining());
	}

	public static int findSmallestInt(int[] args) {
		return Arrays.stream(args).min().orElse(Integer.MIN_VALUE);
	}

	public static String repeatStr(final int repeat, final String string) {
		return IntStream.rangeClosed(1, repeat).mapToObj(i -> string).collect(Collectors.joining());
	}

	public static String switchItUp(int number) {
		String value = "";
		switch (number) {
		case 1:
			value = "One";
			break;
		case 2:
			value = "Two";
			break;
		case 3:
			value = "Three";
			break;
		case 4:
			value = "Four";
			break;
		case 5:
			value = "Five";
			break;
		case 6:
			value = "Six";
			break;
		case 7:
			value = "Seven";
			break;
		case 8:
			value = "Eight";
			break;
		case 9:
			value = "Nine";
			break;
		case 10:
			value = "Ten";
			break;
		default:
			value = "Zero";
			break;
		}
		return value;
	}

	public static int[] reverse(int n) {
		return IntStream.rangeClosed(-n, 1).map(Math::abs).toArray();
	}

	public static String stringy(int size) {
		return IntStream.rangeClosed(1, size).mapToObj(i -> i % 2 == 0 ? "0" : "1").collect(Collectors.joining());
	}

	public static String findNeedle(Object[] haystack) {
		int pos = IntStream.rangeClosed(1, haystack.length).filter(str -> "needle".equals(str)).findFirst().orElse(0);
		return pos != 0 ? "found the needle at position " + pos : "";
	}

	public String dnaToRna(String dna) {
		return dna.replaceAll("T", "U");
	}

	public static int[] countPositivesSumNegatives(int[] input) {
		if (input == null || input.length == 0) {
			return new int[] {};
		}
		int count = (int) Arrays.stream(input).filter(x -> x > 0).count();
		int total = Arrays.stream(input).filter(x -> x < 0).sum();
		return new int[] { count, total };
	}

	public static String remove(String str) {
		return str.length() < 3 ? "" : str.substring(1, str.length() - 2);
	}

	public static String reverse(String str) {
		return new StringBuilder(str).reverse().toString();

	}

	public static int[] digitize(long n) {
		return Arrays.stream(String.valueOf(n).split("")).mapToInt(Integer::parseInt).toArray();
	}

	public static int sum(int n) {
		return Arrays.stream(String.valueOf(n).split("")).mapToInt(Integer::parseInt).sum();
	}

	public static int hexToDecimal(final String hexString) {
		return Integer.parseInt(hexString, 16);
	}

	public static String subtractSum(int n) {
		int sum = 0;
		Map<String, String> data = initData();
		while (!data.containsKey(String.valueOf(n))) {
			sum = sumAllElementOfNumber(n);
			n -= sum;
			if (n <= 0) {
				break;
			}
		}
		return data.get(String.valueOf(n));
	}

	private static int sumAllElementOfNumber(int n) {
		return Arrays.stream(String.valueOf(n).split("")).mapToInt(Integer::parseInt).sum();
	}

	private static Map<String, String> initData() {
		Map<String, String> data = new HashMap<>();
		data.put("1","kiwi");data.put("2","pear");data.put("3","kiwi");data.put("4","banana");data.put("5","melon");
		data.put("6","banana");data.put("7","melon");data.put("8","pineapple");data.put("9","apple");data.put("10","pineapple");
		data.put("11","cucumber");data.put("12","pineapple");data.put("13","cucumber");data.put("14","orange");data.put("15","grape");
		data.put("16","orange");data.put("17","grape");data.put("18","apple");data.put("19","grape");data.put("20","cherry");
		data.put("21","pear");data.put("22","cherry");data.put("23","pear");data.put("24","kiwi");data.put("25","banana");
		data.put("26","kiwi");data.put("27","apple");data.put("28","melon");data.put("29","banana");data.put("30","melon");
		data.put("31","pineapple");data.put("32","melon");data.put("33","pineapple");data.put("34","cucumber");data.put("35","orange");
		data.put("36","apple");data.put("37","orange");data.put("38","grape");data.put("39","orange");data.put("40","grape");
		data.put("41","cherry");data.put("42","pear");data.put("43","cherry");data.put("44","pear");data.put("45","apple");
		data.put("46","pear");data.put("47","kiwi");data.put("48","banana");data.put("49","kiwi");data.put("50","banana");
		data.put("51","melon");data.put("52","pineapple");data.put("53","melon");data.put("54","apple");data.put("55","cucumber");
		data.put("56","pineapple");data.put("57","cucumber");data.put("58","orange");data.put("59","cucumber");data.put("60","orange");
		data.put("61","grape");data.put("62","cherry");data.put("63","apple");data.put("64","cherry");data.put("65","pear");
		data.put("66","cherry");data.put("67","pear");data.put("68","kiwi");data.put("69","pear");data.put("70","kiwi");
		data.put("71","banana");data.put("72","apple");data.put("73","banana");data.put("74","melon");data.put("75","pineapple");
		data.put("76","melon");data.put("77","pineapple");data.put("78","cucumber");data.put("79","pineapple");data.put("80","cucumber");
		data.put("81","apple");data.put("82","grape");data.put("83","orange");data.put("84","grape");data.put("85","cherry");
		data.put("86","grape");data.put("87","cherry");data.put("88","pear");data.put("89","cherry");data.put("90","apple");
		data.put("91","kiwi");data.put("92","banana");data.put("93","kiwi");data.put("94","banana");data.put("95","melon");
		data.put("96","banana");data.put("97","melon");data.put("98","pineapple");data.put("99","apple");data.put("100","pineapple");
		return data;
	}

	public static String[] kataExampleTwist() {
		return IntStream.rangeClosed(1, 1000).mapToObj(i -> "codewars").collect(Collectors.joining(",")).split(",");
	}

	public static int kataExampleTwist(int[] a, int[] b) {
		return Arrays.stream(new int[][] { a, b }).mapToInt(array -> Arrays.stream(array).sum()).sum();
	}

	public static String makeUpperCase(String str) {
		return str.chars().mapToObj(c -> (char) upperCase(c)).map(String::valueOf).collect(Collectors.joining());
	}

	private static int upperCase(int c) {
		return 96 < c && c < 122 ? (c - 32) : c;
	}

	public static int howOld(final String herOld) {
		Pattern pattern = Pattern.compile("([0-9]*) years old");
        Matcher matcher = pattern.matcher(herOld);
        matcher.find();
		return Integer.valueOf(matcher.group(1));
	}

	public static List<String> sort(List<String> textbooks) {
		return textbooks.stream().sorted(String::compareToIgnoreCase).collect(Collectors.toList());
	}
}
