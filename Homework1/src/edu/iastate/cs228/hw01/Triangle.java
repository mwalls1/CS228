package edu.iastate.cs228.hw01;

/**
 * @author Mason Walls
 * 
 * 
 */

public class Triangle extends GeometricObject {
	private double side1;
	/** Side 1 of the triangle */
	private double side2;
	/** Side 2 of the triangle */
	private double side3;/** Side 3 of the triangle */

	// TODO
	/** Creates a triangle object with three sides defaulted to 1 */
	public Triangle() {
		side1 = 1;
		side2 = 1;
		side3 = 1;
	}

	/**
	 * Creates a triangle object with values given in the constructor
	 * 
	 * @param Three
	 *            doubles to represent the sides of the triangle
	 */
	public Triangle(double a, double b, double c) {
		side1 = a;
		side2 = b;
		side3 = c;
	}

	/**
	 * Returns the value of side 1
	 * 
	 * @return a double
	 */
	public double getSide1() {
		return side1;
	}

	/**
	 * Returns the value of side 2
	 * 
	 * @return a double
	 */
	public double getSide2() {
		return side2;
	}

	/**
	 * Returns the value of side 3
	 * 
	 * @return a double
	 */
	public double getSide3() {
		return side3;
	}

	/**
	 * Calculates the area using Herons Formula
	 * 
	 * @return a double
	 */
	public double getArea() {
		double s = (side1 + side2 + side3) / 2;
		return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
	}

	/**
	 * Returns the Perimeter of the triangle
	 * 
	 * @return a double
	 */
	public double getPerimeter() {
		return side1 + side2 + side3;
	}

	/**
	 * Returns the values of the sides of the triangle in a string form
	 * 
	 * @return a string
	 */
	@Override
	public String toString() {
		return "Triangle: side1 = " + side1 + " side2 = " + side2 + " side3 = " + side3;
	}
}
