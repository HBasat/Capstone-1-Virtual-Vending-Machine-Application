package com.techelevator;
import com.techelevator.view.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Map;

public class VendingMachineCLI {

	/*
	----------- 6.8.22 additions -----------
	- added final string "EXIT" ; updated main menu options
	- added if/else statement to exit program upon choosing EXIT
	- changed choice.equals() >> choice.equalsIgnoreCase()

	- learned that menu options are picked by number ONLY
	- added final strings for PURCHASE menu
	- added skeleton method for navigating PURCHASE menu


	----------- 6.14.22 additions -----------
	- edited finishTransaction to account for not having enough funds
	- created AuditFile class to append to a log file
	- edited some code in runPurchase() to account for log file messages
	- made runPurchase() return to main menu after finishing transaction
	- moved gatherItems() to its own class, made it static
	- moved displayItems() to its own class, made it static
	- moved selectProduct() to its own class, made it static
	- moved finishTransaction() to its own class, made it static
	- made DisplayItems, SelectProduct have parameter of Map<String,String> items.
	- moved Map<String,String> items to beginning of code to be referenced to rest of code.
	- accounted for multiple items in one purchase.
	- added ability to show total price of purchase.

	 */

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	private static final String PURCHASE_MENU_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_FEED_MONEY, PURCHASE_MENU_SELECT_PRODUCT, PURCHASE_MENU_FINISH_TRANSACTION};

	private static Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	// Runs first menu of the program.
	public void run(Map<String, String> items, Map<String, String> soldItems) {

		// Amount and total are initialized here. It will be referenced and changed throughout the program.
		double amount = 0.00;
		double total = 0.00;

		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			// Display vending machine items.
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				DisplayItems.displayItems(items);

				// Allows purchase.
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				VendingMachineCLI cli = new VendingMachineCLI(menu);
				cli.runPurchase(items, soldItems, amount, total);

				// Exits program.
			} else if(choice.equalsIgnoreCase(MAIN_MENU_OPTION_EXIT)){
				System.exit(0);

				// Generates a sales report.
			} else if(choice.matches("4")){
				RecordReport.openReport(soldItems);
			}
		}
	}

	// STEP 6: Menu for purchasing a product
	public static void runPurchase(Map<String, String> items, Map<String, String> soldItems, double amount, double total) {

		Date date = new Date(); // This object contains the current date value
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss aa");

		while (true) {
			String choice = (String) menu.getChoiceFromPurchaseOptions(PURCHASE_MENU_OPTIONS, amount, total);
			if (choice.equalsIgnoreCase(PURCHASE_MENU_FEED_MONEY)) {

				// Allows the user to feed money into the machine and adds up amount of money.
				boolean run = true;
				System.out.println("Insert whole dollar amounts: ");
				Scanner userInput = new Scanner(System.in);

				double fedMoney = Double.parseDouble(userInput.nextLine());
				amount += fedMoney;

				// Money fed into machine is written into the audit log.
				AuditLog.log(formatter.format(date) + " FEED MONEY: $" + fedMoney + " $" + String.format("%.2f",amount) + "\n");

			} else if (choice.equalsIgnoreCase(PURCHASE_MENU_SELECT_PRODUCT)) {
				// Select the product of choice.
				total = SelectProduct.selectProduct(items, soldItems, amount, total);

			} else if(choice.equalsIgnoreCase(PURCHASE_MENU_FINISH_TRANSACTION)){
				// Finishes transaction by subtracting total price by amount provided.
				VendingMachineCLI cli = new VendingMachineCLI(menu);
				double temp = FinishTransaction.finishTransaction(amount, total);

				// Here, if temp == -1, it returns the user to the purchase menu, allowing them to add more money, select another item, or finish transaction again.
				// Otherwise, it returns to the first/main menu.
				if(temp == -1){ cli.runPurchase(items, soldItems, amount, total);}
				else{
					amount = temp;
					total = 0;
					cli.run(items, soldItems);
				}
			}
		}
	}

	public static void main(String[] args) {
		// Item map is initialized and filled here. Another map is initialized for keeping track of final sales.
		Map<String, String> items = GatherItems.gatherItems();
		Map<String, String> soldItems = GatherSold.gatherSold();

		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run(items, soldItems);
	}
}
