package com.java.design.pattern.impl;

import com.java.design.pattern.inf.Shape;

public class Circle implements Shape{

	@Override
	public void draw() {
		System.out.println("Shape.Circle::draw()");
	}

}
