package com.techelevator;

import java.math.BigDecimal;

public interface Items {

	public String getNameOfItem();

	public String getCodeOfItem();

	public String getPriceOfItem();

	public int getStockRemaining();

	public abstract String yuum();
}
