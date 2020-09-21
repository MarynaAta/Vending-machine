package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.techelevator.view.Menu;

public class VendingMachineCLI extends Connector {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";

	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
			MAIN_MENU_OPTION_EXIT, };

	private static final String[] PURCHASE_MENU = { "Feed Money", "Select Product", "Finish Transaction", "Back" };
	private static final String[] MONEY_MENU = { "$1 Bill", "$2 Bill", "$5 Bill", "$10 Bill", "Back" };

	public Menu menu;

	Chip chip = new Chip();
	Chip firstChip = new Chip();
	Chip secondChip = new Chip();
	Chip thirdChip = new Chip();
	Chip fourthChip = new Chip();

	Candy candy = new Candy();
	Candy firstCandy = new Candy();
	Candy secondCandy = new Candy();
	Candy thirdCandy = new Candy();
	Candy fourthCandy = new Candy();

	Drink drink = new Drink();
	Drink firstDrink = new Drink();
	Drink secondDrink = new Drink();
	Drink thirdDrink = new Drink();
	Drink fourthDrink = new Drink();

	Gum gum = new Gum();
	Gum firstGum = new Gum();
	Gum secondGum = new Gum();
	Gum thirdGum = new Gum();
	Gum fourthGum = new Gum();

	private BigDecimal currentBalance = BigDecimal.valueOf(0);
	MoneyHandler moneyHandler = new MoneyHandler(currentBalance);

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() throws FileNotFoundException {

		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			System.out.println(choice);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				listItems();

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {

				processPurchaseMenuOptions();

			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				exit();
			}
		}
	}

	private void exit() {
		System.exit(0);
	}

	public void processPurchaseMenuOptions() throws FileNotFoundException {
		String purchaseMenuOption = "";
		while (!purchaseMenuOption.equals("Back")) {
			purchaseMenuOption = (String) menu.getChoiceFromOptions(PURCHASE_MENU);

			if (purchaseMenuOption.equals("Feed Money")) {
				processMoneyFeed(MONEY_MENU);
			}
			if (purchaseMenuOption.equals("Select Product")) {
				listItems();
				System.out.println("Please enter a code of the item you want to purchase: ");
				Scanner codeOfItem = new Scanner(System.in);
				String itemCode = codeOfItem.nextLine();
				boolean foundChip = chip.importChipInfo(itemCode);
				boolean hasStock = isStockEmpty(itemCode);

				if (foundChip && hasStock) {
					updateStock(itemCode);
				} else {
					System.out.println("This is invalid.");
					break;
				}

				File inputFile = new File("vendingmachine.csv");
				int counter = 0;
				try (Scanner inputScanner = new Scanner(inputFile)) {
					while (inputScanner.hasNextLine()) {
						String food = inputScanner.nextLine();
						if (!food.contains(itemCode)) {
							counter += 1;
						}

						if (counter == 16) {
							System.out.println("Invalid product code.");
							break;
						}
					}
					System.out.println("You have chosen to purchase " + chip.getNameOfItem() + " for "
							+ chip.getPriceOfItem() + ". ");
					BigDecimal one = new BigDecimal(chip.getPriceOfItem());
					BigDecimal costNegative = new BigDecimal(-1);
					BigDecimal costFinal = one.multiply(costNegative);
					BigDecimal two = costFinal.add(moneyHandler.getCurrentBalance());
					String reportString = dateAndTime() + " " + chip.getNameOfItem() + " " + chip.getCodeOfItem() + " $"
							+ moneyHandler.getCurrentBalance() + " $" + two;
					prepareReport(reportString, "Log.txt");

				} catch (Exception e) {
					e.printStackTrace();
				}

				String costOfItem = chip.getPriceOfItem();

				System.out.println(costOfItem);

				BigDecimal cost = new BigDecimal(costOfItem);
				BigDecimal costNegative = new BigDecimal(-1);
				BigDecimal costFinal = cost.multiply(costNegative);
				moneyHandler.moneyInput(costFinal);

				System.out.println("You have " + moneyHandler.getCurrentBalance() + " remaining");

			}
			if (purchaseMenuOption.equals("Finish Transaction")) {
//				BigDecimal one = new BigDecimal(chip.getPriceOfItem());
//				BigDecimal two = one.add(moneyHandler.getCurrentBalance());
//				String reportString = ">>> " + dateAndTime() + " " + chip.getNameOfItem() + " " + chip.getCodeOfItem() + " $" + two + " $" + moneyHandler.getCurrentBalance() ;
//				prepareReport(reportString, "Log.txt");
				try {
					List<Integer> changeOutput = MoneyHandler.changeGiven();
					MoneyHandler.changeForUser(changeOutput);
				} catch (NullPointerException e) {
					e.printStackTrace();
				}

			}

		}

	}

	private void updateStock(String itemCode) {
		if (itemCode.contains("A")) {
			System.out.println(chip.yuum());
		}
		if (itemCode.contains("A1")) {
			firstChip.setStockRemaining();
			System.out.println("There are " + firstChip.getStockRemaining() + " remaining.");
		} else if (itemCode.contains("A2")) {
			secondChip.setStockRemaining();
			System.out.println("There are " + secondChip.getStockRemaining() + " remaining.");
		} else if (itemCode.contains("A3")) {
			thirdChip.setStockRemaining();
			System.out.println("There are " + thirdChip.getStockRemaining() + " remaining.");
		}
		if (itemCode.contains("A4")) {
			fourthChip.setStockRemaining();
			System.out.println("There are " + fourthChip.getStockRemaining() + " remaining.");
		} else if (itemCode.contains("B")) {
			System.out.println(candy.yuum());
			if (itemCode.contains("B1")) {
				firstCandy.setStockRemaining();
				System.out.println("There are " + firstCandy.getStockRemaining() + " remaining.");
			} else if (itemCode.contains("B2")) {
				secondCandy.setStockRemaining();
				System.out.println("There are " + secondCandy.getStockRemaining() + " remaining.");
			} else if (itemCode.contains("B3")) {
				thirdCandy.setStockRemaining();
				System.out.println("There are " + thirdCandy.getStockRemaining() + " remaining.");
			} else if (itemCode.contains("B4")) {
				fourthCandy.setStockRemaining();
				System.out.println("There are " + fourthCandy.getStockRemaining() + " remaining.");
			}
		} else if (itemCode.contains("C")) {
			System.out.println(drink.yuum());
			if (itemCode.contains("C1")) {
				firstDrink.setStockRemaining();
				System.out.println("There are " + firstDrink.getStockRemaining() + " remaining.");
			} else if (itemCode.contains("C2")) {
				secondDrink.setStockRemaining();
				System.out.println("There are " + secondDrink.getStockRemaining() + " remaining.");
			} else if (itemCode.contains("C3")) {
				thirdDrink.setStockRemaining();
				System.out.println("There are " + thirdDrink.getStockRemaining() + " remaining.");
			} else if (itemCode.contains("C4")) {
				fourthDrink.setStockRemaining();
				System.out.println("There are " + fourthDrink.getStockRemaining() + " remaining.");
			}
		} else if (itemCode.contains("D")) {
			System.out.println(gum.yuum());
			if (itemCode.contains("D1")) {
				firstGum.setStockRemaining();
				System.out.println("There are " + firstGum.getStockRemaining() + " remaining.");
			} else if (itemCode.contains("D2")) {
				secondGum.setStockRemaining();
				System.out.println("There are " + secondGum.getStockRemaining() + " remaining.");
			} else if (itemCode.contains("D3")) {
				thirdGum.setStockRemaining();
				System.out.println("There are " + thirdGum.getStockRemaining() + " remaining.");
			} else if (itemCode.contains("D4")) {
			} else {
				fourthGum.setStockRemaining();
				System.out.println("There are " + fourthGum.getStockRemaining() + " remaining.");
			}
		}
	}

	private boolean isStockEmpty(String itemCode) {
		boolean hasStock = true;
		if (itemCode.contains("A")) {
			if (itemCode.contains("A1")) {
				if (firstChip.getStockRemaining() < 1) {
					hasStock = false;
					System.out.println("You cannot purchase this. There is not stock left.");
				}
			} else if (itemCode.contains("A2")) {
				if (secondChip.getStockRemaining() < 1) {
					hasStock = false;
					System.out.println("You cannot purchase this. There is not stock left.");
				}
			} else if (itemCode.contains("A3")) {
				if (thirdChip.getStockRemaining() < 1) {
					hasStock = false;
					System.out.println("You cannot purchase this. There is not stock left.");
				}
			} else if (itemCode.contains("A4")) {
				if (fourthChip.getStockRemaining() < 1) {
					hasStock = false;
					System.out.println("You cannot purchase this. There is not stock left.");
				}
			}
		} else if (itemCode.contains("B")) {
			if (itemCode.contains("B1")) {
				if (firstCandy.getStockRemaining() < 1) {
					hasStock = false;
					System.out.println("You cannot purchase this. There is not stock left.");
				}
			} else if (itemCode.contains("B2")) {
				if (secondCandy.getStockRemaining() < 1) {
					hasStock = false;
					System.out.println("You cannot purchase this. There is not stock left.");
				}
			} else if (itemCode.contains("B3")) {
				if (thirdCandy.getStockRemaining() < 1) {
					hasStock = false;
					System.out.println("You cannot purchase this. There is not stock left.");
				}
			} else if (itemCode.contains("B4")) {
				if (fourthCandy.getStockRemaining() < 1) {
					hasStock = false;
					System.out.println("You cannot purchase this. There is not stock left.");
				}
			}
		} else if (itemCode.contains("C")) {
			if (itemCode.contains("C1")) {
				if (firstDrink.getStockRemaining() < 1) {
					hasStock = false;
					System.out.println("You cannot purchase this. There is not stock left.");
				}
			} else if (itemCode.contains("C2")) {
				if (secondDrink.getStockRemaining() < 1) {
					hasStock = false;
					System.out.println("You cannot purchase this. There is not stock left.");
				}
			} else if (itemCode.contains("C3")) {
				if (thirdDrink.getStockRemaining() < 1) {
					hasStock = false;
					System.out.println("You cannot purchase this. There is not stock left.");
				}
			} else if (itemCode.contains("C4")) {
				if (fourthDrink.getStockRemaining() < 1) {
					hasStock = false;
					System.out.println("You cannot purchase this. There is not stock left.");
				}
			}
		} else if (itemCode.contains("D")) {
			if (itemCode.contains("D1")) {
				if (firstGum.getStockRemaining() < 1) {
					hasStock = false;
					System.out.println("You cannot purchase this. There is not stock left.");
				}
			} else if (itemCode.contains("D2")) {
				if (secondGum.getStockRemaining() < 1) {
					hasStock = false;
					System.out.println("You cannot purchase this. There is not stock left.");
				}
			} else if (itemCode.contains("D3")) {
				if (thirdGum.getStockRemaining() < 1) {
					hasStock = false;
					System.out.println("You cannot purchase this. There is not stock left.");
				}
			} else if (itemCode.contains("D4")) {
				if (fourthGum.getStockRemaining() < 1) {
					hasStock = false;
					System.out.println("You cannot purchase this. There is not stock left.");
				}
			}
		}
		return hasStock;
	}

	public void processMoneyFeed(String[] moneyMenu) throws FileNotFoundException {

		BigDecimal moneyFeed1 = new BigDecimal(1);
		BigDecimal moneyFeed2 = new BigDecimal(2);
		BigDecimal moneyFeed3 = new BigDecimal(5);
		BigDecimal moneyFeed4 = new BigDecimal(10);

		String feedOptions = "";

		feedOptions = (String) menu.getChoiceFromOptions(moneyMenu);

		if (feedOptions.equals("$1 Bill")) {
			moneyHandler.moneyInput(moneyFeed1);
			System.out.println("You have added $1");
			feedOptions = "1.00";
		} else if (feedOptions.equals("$2 Bill")) {
			moneyHandler.moneyInput(moneyFeed2);
			System.out.println("You have added $2");
			feedOptions = "2.00";
		} else if (feedOptions.equals("$5 Bill")) {
			moneyHandler.moneyInput(moneyFeed3);
			System.out.println("You have added $5");
			feedOptions = "5.00";
		} else if (feedOptions.equals("$10 Bill")) {
			moneyHandler.moneyInput(moneyFeed4);
			System.out.println("You have added $10");
			feedOptions = "10.00";
		}
		System.out.println("You have deposited: $" + moneyHandler.getCurrentBalance());
	}

	public static String dateAndTime() {
		SimpleDateFormat dateTime = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
		Date date = new Date();
		String dateReport = dateTime.format(date);
		return dateReport;
	}

	public static void main(String[] args) throws IOException {
		Menu menu = new Menu(System.in, System.out);
		File reportFile = new File("Log.txt");
		reportFile.createNewFile();
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}

	public static void prepareReport(String rep, String file) throws FileNotFoundException {
		try (PrintWriter writer = new PrintWriter(new FileOutputStream(file, true))) {
			writer.println(rep);
		}
	}

	public void listItems() {
		File inputFile = new File("vendingmachine.csv");
		{
			try (Scanner inputScanner = new Scanner(inputFile)) {
				while (inputScanner.hasNextLine()) {
					String food = inputScanner.nextLine();
					if (food.contains("A1")) {
						System.out.println(food + "|Stock: " + firstChip.getStockRemaining() + " ");
					}
					if (food.contains("A2")) {
						System.out.println(food + "|Stock: " + secondChip.getStockRemaining() + " ");
					}
					if (food.contains("A3")) {
						System.out.println(food + "|Stock: " + thirdChip.getStockRemaining() + " ");
					}
					if (food.contains("A4")) {
						System.out.println(food + "|Stock: " + fourthChip.getStockRemaining() + " ");
					}
					if (food.contains("B1")) {
						System.out.println(food + "|Stock: " + firstCandy.getStockRemaining() + " ");
					}
					if (food.contains("B2")) {
						System.out.println(food + "|Stock: " + secondCandy.getStockRemaining() + " ");
					}
					if (food.contains("B3")) {
						System.out.println(food + "|Stock: " + thirdCandy.getStockRemaining() + " ");
					}
					if (food.contains("B4")) {
						System.out.println(food + "|Stock: " + fourthCandy.getStockRemaining() + " ");
					}
					if (food.contains("C1")) {
						System.out.println(food + "|Stock: " + firstDrink.getStockRemaining() + " ");
					}
					if (food.contains("C2")) {
						System.out.println(food + "|Stock: " + secondDrink.getStockRemaining() + " ");
					}
					if (food.contains("C3")) {
						System.out.println(food + "|Stock: " + thirdDrink.getStockRemaining() + " ");
					}
					if (food.contains("C4")) {
						System.out.println(food + "|Stock: " + fourthDrink.getStockRemaining() + " ");
					}
					if (food.contains("D1")) {
						System.out.println(food + "|Stock: " + firstGum.getStockRemaining() + " ");
					}
					if (food.contains("D2")) {
						System.out.println(food + "|Stock: " + secondGum.getStockRemaining() + " ");
					}
					if (food.contains("D3")) {
						System.out.println(food + "|Stock: " + thirdGum.getStockRemaining() + " ");
					}
					if (food.contains("D4")) {
						System.out.println(food + "|Stock: " + fourthGum.getStockRemaining() + " ");
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
