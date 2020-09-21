package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

import com.techelevator.view.Menu;

public class Chip extends Connector implements Items {

	public String name;
	public String code;
	public String price;
	public int stockRemaining = 5;
	public int stockSold = 5;

	public Chip() {
	}

	public boolean importChipInfo(String itemCode) {
		File inputFile = new File("vendingmachine.csv");
		try (Scanner inputScanner = new Scanner(inputFile)) {
			while (inputScanner.hasNextLine()) {
				String food = inputScanner.nextLine();
				if (food.contains(itemCode)) {
					String[] info = food.split("\\|" + "");
					this.code = info[0];
					this.name = info[1];
					this.price = info[2];
					return true;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return false;
	}

	public String getNameOfItem() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public String getCodeOfItem() {
		// TODO Auto-generated method stub
		return this.code;
	}

	@Override
	public String getPriceOfItem() {
		// TODO Auto-generated method stub
		return this.price;
	}

	@Override
	public int getStockRemaining() {
		if (stockRemaining < 1) {
			System.out.println("SOLD OUT");
		}
		return this.stockRemaining;
	}

	public void setStockRemaining() {
		this.stockRemaining -= 1;
	}

	@Override
	public String yuum() {
		// TODO Auto-generated method stub
		return "Crunch Crunch, Yum";

	}

}