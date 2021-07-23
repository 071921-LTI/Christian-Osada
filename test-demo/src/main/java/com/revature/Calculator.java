package com.revature;

import com.revature.exceptions.CalculatorException;

public class Calculator {

	/*
	 * Should be able to:
	 * 		- add
	 * 		- subtract
	 * 		- multiply
	 * 		- divide
	 * 			- throw a CalculatorException when attempting to divide by 0
	 * 		- isPrime: checks if a number is a prime number
	 * 		- compareThreeDecimals: returns true if the same up to 3 decimals
	 * 				- 3.123.compare...(3.1233445) should return true
	 */
	
	public double add(double x, double y) {
		
		return x + y;
	}
	
	public double subtract(double x, double y) {
		return x - y;
	}
	
	public double multiply(double x, double y) {
		return x * y;
	}
	
	public double divide(double x, double y) throws CalculatorException{
		if (y == 0) {
			throw new CalculatorException();
		} else {
			return x/y;
		}
	}
	
	public boolean isPrime(int x) {
		if (x == 0) {
			return false;
		} else if (x%2 == 0) {
			return false;
		} else if (x%3 == 0) {
			return false;
		} else if (x%5 == 0) {
			return false;
		} else if (x%7 == 0) {
			return false;
		} else if (x%11 == 0) {
			return false;
		}
		
		return true;
	}
	
	public boolean compareThreeDecimal(double x, double y) {
		return Math.abs(x - y) < 1e-3;
	}
}
