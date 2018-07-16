package com.kennedy.java.core.oop.polymorphism;

public class Dog extends Manmal {

	@Override
	public void getName() {
		System.out.println("Dog");
	}

	@Override
	public void sound() {
		System.out.println("Woof");
	}

	public static void main(String args[]) {
		Animal obj = new Dog();
		obj.sound();
		obj.getName();
	}
}
