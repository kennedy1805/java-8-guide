package com.kennedy.java.kata;

public class Woman extends Human {
	public Woman() {
		System.out.println("Eva are a woman");
	}
	public static Human init() {
		return new Woman();
	}
}
