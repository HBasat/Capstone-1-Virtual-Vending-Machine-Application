package com.techelevator;
import com.techelevator.view.Menu;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.text.NumberFormat;
import java.time.LocalDateTime;

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

	private static final double QUARTER = 0.25;
	private static final double DIME = 0.10;
	private static final double NICKEL = 0.05;
	private static final double PENNY = 0.01;



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

	// STEP 3: reads input file and returns map of vending machine items
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

	// STEP 6: menu for purchasing a product
	public void runPurchase() {

		double amount = 0.00;
		double total = 0.00;

		while (true) {
			String choice = (String) menu.getChoiceFromPurchaseOptions(PURCHASE_MENU_OPTIONS, amount);
			if (choice.equalsIgnoreCase(PURCHASE_MENU_FEED_MONEY)) {
				//STEP 7: feed
				boolean run = true;
				System.out.println("Insert whole dollar amounts: ");
				Scanner userInput = new Scanner(System.in);

				double fedMoney = Double.parseDouble(userInput.nextLine());
				amount += fedMoney;
			} else if (choice.equalsIgnoreCase(PURCHASE_MENU_SELECT_PRODUCT)) {
				// do purchase
				total = selectProduct();

			} else if(choice.equalsIgnoreCase(PURCHASE_MENU_FINISH_TRANSACTION)){
				amount = finishTransaction(amount, total);

			}
		}
	}

	// STEP 5: displays vending machine items. will show if inventory is sold out.
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

	// STEP 7.2: selecting the product with user input
	public double selectProduct(){
		Map<String, String> items = gatherItems();
		double total = 0;

		displayItems();

		System.out.println("Select Item >>> ");

		Scanner selection = new Scanner(System.in);
		String userInput = selection.nextLine();

		if(items.containsKey(userInput)){

			String wantedItem = items.get(userInput);

			String[] values = wantedItem.split("[|]");
			int inventory = Integer.parseInt(values[TYPE]);


			/*
			for(int i = 0; i < values.length; i++){
				System.out.println(values[i]);
			}
			System.out.println("\n" + inventory);
			*/


			if(inventory == 0){
				System.out.println("ITEM IS NOW SOLD OUT");
				runPurchase();
			} else{

				double price = Double.parseDouble(values[PRICE - 1]);
				String type = values[TYPE-1];

				if(type.equalsIgnoreCase("Chip")){
					System.out.println("Crunch Crunch, Yum!");
				}
				else if(type.equalsIgnoreCase("Candy")){
					System.out.println("Munch Munch, Yum!");
				}
				else if(type.equalsIgnoreCase("Drink")){
					System.out.println("Glug Glug, Yum!");
				}
				else if(type.equalsIgnoreCase("Gum")){
					System.out.println("Chew Chew, Yum!");
				}

				total += price;
			}
		} else{
			System.out.printf("PRODUCT DOES NOT EXIST");
			runPurchase();
		}

		return total;
	}

public double finishTransaction(double amount, double total) {
		double change = 0;
		change = amount - total;

		if(change < 0){
			System.out.print("You can't do that :/");
		}

		double temp = 0;
		temp = change;
		double remaining;

		int quarters = (int) (temp / QUARTER);
		remaining = temp % QUARTER;

		temp = remaining;

		int dimes = (int) (temp / DIME);
	    remaining = temp % DIME;

	    temp = remaining;

		int nickels = (int) (temp / NICKEL);
		remaining = temp % NICKEL;

		temp = remaining;

		int pennies = (int) (temp / PENNY);

	    System.out.println("Dispensing " + quarters + " quarters, " + dimes + " dimes, " + nickels + " nickels, " + pennies + " pennies.");
		return 0;
		//NumberFormat remains = NumberFormat.getCurrencyInstance();
		//return Double.parseDouble(remains.format(remaining));





		/*
		7.50.......... 30 quarters ; 75 dimes ; 150 nickels
		3.65.......... 14 quarters + 1 dime + 1 nickel ; 36 dimes + 1 nickel ; 73 nickels
		*/


}




	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
