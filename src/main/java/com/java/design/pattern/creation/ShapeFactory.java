package com.java.design.pattern.creation;

import com.java.design.pattern.impl.ShapeCircle;
import com.java.design.pattern.impl.ShapeRectangle;
import com.java.design.pattern.impl.ShapeSquare;
import com.java.design.pattern.inf.Color;
import com.java.design.pattern.inf.Shape;

public class ShapeFactory extends AbstractFactory{
	
	@Override
	public Shape getShape(String shapeType) {
		if (shapeType == null) {
			return null;
		}
		if (shapeType.equalsIgnoreCase("CIRCLE")) {
			return new ShapeCircle();

		} else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
			return new ShapeRectangle();

		} else if (shapeType.equalsIgnoreCase("SQUARE")) {
			return new ShapeSquare();
		}

		return null;
	}

	@Override
	public Color getColor(String color) {
		return null;
	}
}
