package com.techelevator;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.techelevator.view.Menu;

public class ItemsTests extends Connector {

	//Candy Tests
	Candy candy = new Candy();
	@Test
	public void getCandyStockRemaining() {

		int actualResult = candy.getStockRemaining();
		int expectedResult = 5;
		Assert.assertEquals(expectedResult, actualResult);
	}

	@Test
	public void candyYuumTest() {
		String actualResult = candy.yuum();
		String expectedResult = "Munch Munch, Yum";
		Assert.assertEquals(expectedResult, actualResult);
	}
	
	//Chip Tests
	Chip chip = new Chip();
	@Test
	public void getChipStockRemaining() {

		int actualResult = chip.getStockRemaining();
		int expectedResult = 5;
		Assert.assertEquals(expectedResult, actualResult);
	}

	@Test
	public void chipYuumTest() {
		String actualResult = chip.yuum();
		String expectedResult = "Crunch Crunch, Yum";
		Assert.assertEquals(expectedResult, actualResult);
	}
	
	//Drink Tests
		Drink drink = new Drink();
		@Test
		public void getDrinkStockRemaining() {

			int actualResult = drink.getStockRemaining();
			int expectedResult = 5;
			Assert.assertEquals(expectedResult, actualResult);
		}

		@Test
		public void DrinkYuumTest() {
			String actualResult = drink.yuum();
			String expectedResult = "Glug Glug, Yum";
			Assert.assertEquals(expectedResult, actualResult);
		}
		
		//Chip Tests
		Gum gum = new Gum();
		@Test
		public void getGumStockRemaining() {

			int actualResult = gum.getStockRemaining();
			int expectedResult = 5;
			Assert.assertEquals(expectedResult, actualResult);
		}

		@Test
		public void gumYuumTest() {
			String actualResult = gum.yuum();
			String expectedResult = "Chew Chew, Yum";
			Assert.assertEquals(expectedResult, actualResult);
		}
		
		//importChipInfoTest
		@Test
		public void importChipInfoTestReturnTrue() {
			boolean actualResult = chip.importChipInfo("C3");
			boolean expectedResult = true;
			Assert.assertEquals(expectedResult, actualResult);
		}
		
		@Test
		public void importChipInfoTestReturnFalse() {
			boolean actualResult = chip.importChipInfo("H9");
			boolean expectedResult = false;
			Assert.assertEquals(expectedResult, actualResult);
		}
		
}
