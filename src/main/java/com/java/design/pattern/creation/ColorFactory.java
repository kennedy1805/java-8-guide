package com.java.design.pattern.creation;

import com.java.design.pattern.impl.ColorBlue;
import com.java.design.pattern.impl.ColorGreen;
import com.java.design.pattern.impl.ColorRed;
import com.java.design.pattern.inf.Color;
import com.java.design.pattern.inf.Shape;

public class ColorFactory extends AbstractFactory {

	@Override
	Color getColor(String color) {
		if (color == null) {
			return null;
		}

		if (color.equalsIgnoreCase("RED")) {
			return new ColorRed();

		} else if (color.equalsIgnoreCase("GREEN")) {
			return new ColorGreen();

		} else if (color.equalsIgnoreCase("BLUE")) {
			return new ColorBlue();
		}

		return null;
	}

	@Override
	Shape getShape(String shape) {
		return null;
	}

}
