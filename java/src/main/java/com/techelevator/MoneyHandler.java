package com.techelevator;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MoneyHandler extends Connector {
	private static BigDecimal currentBalance;

	public MoneyHandler(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}

	public static BigDecimal getCurrentBalance() {
		return currentBalance;
	}

	public void moneyInput(BigDecimal input) throws FileNotFoundException {
		this.currentBalance = currentBalance.add(input);
		String amount1 = input.toString();
		double amount2 = Double.parseDouble(amount1);
		String amount3 = currentBalance.toString();
		double amount4 = Double.parseDouble(amount3);
		if (amount2 > 0) {
		String reportString = VendingMachineCLI.dateAndTime() + " FEED MONEY: $" + amount2 + "0" + " $" + amount4 + "0";
		VendingMachineCLI.prepareReport(reportString, "Log.txt");
		}
	}

	public static List<Integer> changeReturn = new ArrayList<Integer>();

	public static List<Integer> changeGiven( ) {
		BigDecimal oneHundred = new BigDecimal(100);
		BigDecimal test = currentBalance.multiply(oneHundred);
		int changeMoney = test.intValue();

		int quarters = 0;
		int dimes = 0;
		int nickels = 0;

		while (changeMoney >= 25) {
			quarters += 1;
			changeMoney -= 25;
		}
		while (changeMoney >= 10) {
			dimes += 1;
			changeMoney -= 10;
		}
		while (changeMoney >= 5) {
			nickels += 1;
			
			changeMoney -= 5;
		}

		changeReturn.add(quarters);
		changeReturn.add(dimes);
		changeReturn.add(nickels);

		return changeReturn;
	}
	
	public static void changeForUser(List<Integer> coins) throws FileNotFoundException {
		int quarterReturns = coins.get(0);
		int dimesReturns = coins.get(1);
		int nickelReturns = coins.get(2);
		
		System.out.println("Your change in quarters: " + quarterReturns);
		System.out.println("Your change in dimes: " + dimesReturns);
		System.out.println("Your change in nickels: " + nickelReturns);

		String reportString = VendingMachineCLI.dateAndTime() + " GIVE CHANGE: $" + getCurrentBalance() + " $0.00";
		VendingMachineCLI.prepareReport(reportString, "Log.txt");
		
		
	}
}