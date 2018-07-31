package com.kennedy.java.kata;

public class Human {
	public static final Human INST = new Human();
	public Human() {
		System.out.println("Human");
	}
	public static Human init() {
		return new Human();
	}
	public static Human[] create() {
		Human[] human = new Human[2];
		human[0] = Man.init();
		human[1] = Woman.init();
		return human;
	}
}
