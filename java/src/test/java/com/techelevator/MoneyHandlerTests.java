package com.techelevator;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class MoneyHandlerTests {

	BigDecimal start = new BigDecimal(2);
	MoneyHandler moneyHandler = new MoneyHandler(start);
	
	@Test
	public void getCurrentBalanceTest() {
		BigDecimal actual = moneyHandler.getCurrentBalance();
		BigDecimal expected = start;
		Assert.assertEquals(expected, actual);
	}

	BigDecimal test1 = new BigDecimal(1.15);
	BigDecimal test2 = new BigDecimal(3.15);
	@Test
	public void moneyInputTest() throws FileNotFoundException {
		moneyHandler.moneyInput(test2);
		
		BigDecimal actual = moneyHandler.getCurrentBalance();
		BigDecimal expected = test1;
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void changeGivenTest() {
		List<Integer> actual = moneyHandler.changeGiven();
		List<Integer> expected = new ArrayList<Integer>();
		expected.add(12);
		expected.add(1);
		expected.add(1);
		Assert.assertEquals(expected, actual);
	}
	
}
