package com.kennedy.java.core.oop.polymorphism;

public class Manmal extends Animal{
	
	@Override
	public void getName() {
		System.out.println("Manmal");
	};
	
	
	@Override
	public void sound() {
		System.out.println("mamamama");
	}
	
	public static void main(String[] args) {
		Animal manmal = new Manmal();
		manmal.getName();
	}
}
