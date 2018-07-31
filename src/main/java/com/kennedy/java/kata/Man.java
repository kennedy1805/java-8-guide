package com.kennedy.java.kata;

public class Man extends Human {
	public Man() {
		System.out.println("Adam are a man");
	}

	public static Human init() {
		return new Man();
	}
}
