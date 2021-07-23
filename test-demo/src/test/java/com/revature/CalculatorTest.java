package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.revature.exceptions.CalculatorException;

@TestMethodOrder(OrderAnnotation.class)
public class CalculatorTest {

	/*
	 * JUnit annotations
	 * 	- @BeforeEach
	 * 	- @AfterEach
	 * 	- @BeforeAll
	 * 	- @AfterAll
	 * 	- @Test
	 * 	- @Ignore
	 *  - @Order
	 */
	
	private static Calculator calc;
	
	
	@BeforeAll
	public static void setUp() {
		calc = new Calculator();
	}
	
	@AfterAll
	public static void tearDown() {
	}
	
	@Order(1)
	@Test
	public void addTwoAndTwo() {
		double expected = 4;
		double actualResult = calc.add(2, 2);
		assertEquals(expected, actualResult, "Adding 2 and 2 should be 4");
	}
	
	@Order(2)
	@Test
	public void subtractTwoAndTwo() {
		double expected = 0;
		double actualResult = calc.subtract(2, 2);
		assertEquals(expected, actualResult, "Subtracting 2 and 2 should be 0");
	}
	
	@Order(3)
	@Test
	public void multiplyTwoAndTwo() {
		double expected = 4;
		double actualResult = calc.multiply(2, 2);
		assertEquals(expected, actualResult, "Multiplying 2 by 2 should be 4");
	}
	
	@Order(4)
	@Test
	public void divide2By2() {
		double expected = 1;
		double actualResult = calc.divide(2, 2);
		assertEquals(expected, actualResult, "Dividing 2 by 2 should be 1");
	}
	
	@Order(5)
	@Test
	public void divideBy0() {
		assertThrows(CalculatorException.class, () -> calc.divide(1,0));
	}
	
	@Order(6)
	@Test
	public void is23Prime() {
		boolean expected = true;
		boolean actualResult = calc.isPrime(23);
		assertEquals(expected, actualResult, "23 should be counted as a prime number");
	}
	
	@Order(7)
	@Test
	public void is22Prime() {
		boolean expected = false;
		boolean actualResult = calc.isPrime(2);
		assertEquals(expected, actualResult, "22 should not be counted as a prime number");
	}
	
	@Order(8)
	@Test
	public void equalAt3DecPlace() {
		boolean expected = true;
		boolean actualResult = calc.compareThreeDecimal(2.0003, 2.0004);
		assertEquals(expected, actualResult, "2.003 and 2.004 should be counted "
				+ "as equal up to three decimal places");
	}
	
	@Order(9)
	@Test
	public void notEqualAt3DecPlace() {
		boolean expected = false;
		boolean actualResult = calc.compareThreeDecimal(2.0023, 2.0004);
		assertEquals(expected, actualResult, "2.023 and 2.004 should not be counted "
				+ "as equal up to three decimal places");
	}
}
