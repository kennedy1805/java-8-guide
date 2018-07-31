package com.java.design.pattern.creation;

import com.java.design.pattern.impl.Circle;
import com.java.design.pattern.impl.Rectangle;
import com.java.design.pattern.impl.Square;
import com.java.design.pattern.inf.Shape;

public class ShapeFactory {
	public Shape getShape(String shapeType) {
		if (shapeType == null) {
			return null;
		}
		if (shapeType.equalsIgnoreCase("CIRCLE")) {
			return new Circle();

		} else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
			return new Rectangle();

		} else if (shapeType.equalsIgnoreCase("SQUARE")) {
			return new Square();
		}

		return null;
	}
}
