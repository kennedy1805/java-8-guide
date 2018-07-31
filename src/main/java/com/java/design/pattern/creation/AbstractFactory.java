package com.java.design.pattern.creation;

import com.java.design.pattern.inf.Color;
import com.java.design.pattern.inf.Shape;

public abstract class AbstractFactory {
	abstract Color getColor(String color);
	abstract Shape getShape(String shape);
}
