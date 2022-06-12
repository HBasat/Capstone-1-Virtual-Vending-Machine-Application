package com.techelevator;

import com.techelevator.view.Menu;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.io.File;

public class VendingMachineCLI {

	/*
	----------- 6.8.22 additions -----------
	- added final string "EXIT" ; updated main menu options
	- added if/else statement to exit program upon choosing EXIT
	- changed choice.equals() >> choice.equalsIgnoreCase()

	- learned that menu options are picked by number ONLY
	- added final strings for PURCHASE menu
	- added skeleton method for navigating PURCHASE menu
	 */

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	private static final String PURCHASE_MENU_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_FEED_MONEY, PURCHASE_MENU_SELECT_PRODUCT, PURCHASE_MENU_FINISH_TRANSACTION };

	private static final int SLOT_LOCATION = 0;
	private static final int PRODUCT_NAME = 1;
	private static final int PRICE = 2;
	private static final int TYPE = 3;

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				displayItems();

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				VendingMachineCLI cli = new VendingMachineCLI(menu);
				cli.runPurchase();
			} else if(choice.equalsIgnoreCase(MAIN_MENU_OPTION_EXIT)){
				System.exit(0);
			}
		}
	}

	public Map<String,String> gatherItems(){
		File text = new File("C:\\Users\\hanni\\Desktop\\Capstones\\capstone-1\\vendingmachine.csv");
		Map<String,String> items = new HashMap<>();
		int inventory = 5;

		try(Scanner input = new Scanner(text)){

			// read file to gather items.
			while(input.hasNextLine()){
				String line = input.nextLine();
				String[] lineSplit = line.split("[|]");

				items.put(lineSplit[SLOT_LOCATION], lineSplit[PRODUCT_NAME] + "|" + lineSplit[PRICE] + "|" + lineSplit[TYPE] + "|" + inventory);
			}
		}
		catch(FileNotFoundException e){

		}

		return items;
	}

	public void runPurchase() {

		double amount = 0.00;

		while (true) {
			String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
			System.out.println("\nCurrent Money Provided: $" + amount);

			if (choice.equalsIgnoreCase(PURCHASE_MENU_FEED_MONEY)) {
				boolean run = true;
				System.out.println("Insert whole dollar amounts: ");
				Scanner userInput = new Scanner(System.in);

				double fedMoney = Double.parseDouble(userInput.nextLine());
				amount += fedMoney;
			} else if (choice.equalsIgnoreCase(PURCHASE_MENU_SELECT_PRODUCT)) {
				// do purchase

			} else if(choice.equalsIgnoreCase(PURCHASE_MENU_FINISH_TRANSACTION)){
				System.exit(0);
			}
		}
	}

	public void displayItems(){
		Map<String, String> items = gatherItems();

		// print each item.
		for(Map.Entry<String, String> key : items.entrySet()){

			String[] values = key.getValue().split("[|]");
			int inventory = Integer.parseInt(values[TYPE]);

			if(inventory == 0){
				System.out.println(key.getKey() + " - " + values[PRODUCT_NAME - 1] + " $" + values[PRICE - 1] + " SOLD OUT");
			}
			else System.out.println(key.getKey() + " - " + values[PRODUCT_NAME - 1] + " $" + values[PRICE - 1] + " x" + inventory);
		}
	}


	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
