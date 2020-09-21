package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Gum implements Items {
	public String name;
	public String code;
	public String price;
	public int stockRemaining = 5;

	public Gum() {
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
		return "Chew Chew, Yum";
	}
	
}
