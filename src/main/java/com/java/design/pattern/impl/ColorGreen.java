package com.java.design.pattern.impl;

import com.java.design.pattern.inf.Color;

public class ColorGreen implements Color{

	@Override
	public void fill() {
		System.out.println("Inside Green::fill() method.");
	}

}
