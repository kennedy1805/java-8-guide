package com.kennedy.java.core.oop.polymorphism;

public class Cat extends Manmal {

	@Override
	public void sound() {
		System.out.println("Meowe");
	}

	public static void main(String args[]) {
		Animal obj = new Cat();
		obj.sound();
		obj.getName();
	}
}
