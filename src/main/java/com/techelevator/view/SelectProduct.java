package com.techelevator.view;

import com.techelevator.VendingMachineCLI;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class SelectProduct {

    private static final int PRICE = 1;
    private static final int TYPE = 2;
    private static final int INVENTORY = 3;

    // STEP 7: Selects the product with user input
    public static double selectProduct(Map<String, String> items, Map<String, String> soldItems, double amount, double total){

        Date date = new Date(); // This object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss aa");

        // Calls class method to display items to user
        DisplayItems.displayItems(items);

        // Takes input from user
        System.out.println("Select Item >>> ");

        Scanner selection = new Scanner(System.in);
        String userInput = selection.nextLine();

        // Searches for user input key from map of items. If item is found,
        if(items.containsKey(userInput)){

            // The following string takes the value from the key in the map,
            String wantedItem = items.get(userInput);

            // Sets the string into an array of values and a number for the inventory.
            String[] values = wantedItem.split("[|]");
            int inventory = Integer.parseInt(values[INVENTORY]);

            // Tells user if the inventory is sold out
            if(inventory == 0){
                System.out.println("ITEM IS NOW SOLD OUT");
                VendingMachineCLI.runPurchase(items, soldItems, amount, total);
            }

            // Otherwise, if the inventory is NOT sold out, system outputs a line to the user.
            else{
                double price = Double.parseDouble(values[PRICE]);
                String type = values[TYPE];


                if(type.equalsIgnoreCase("Chip")){ System.out.println("Crunch Crunch, Yum!"); }
                else if(type.equalsIgnoreCase("Candy")){ System.out.println("Munch Munch, Yum!"); }
                else if(type.equalsIgnoreCase("Drink")){ System.out.println("Glug Glug, Yum!"); }
                else if(type.equalsIgnoreCase("Gum")){ System.out.println("Chew Chew, Yum!"); }

                // Finds the key item in the soldItems list, and updates the number sold for the sales report.
                if(soldItems.containsKey(userInput)){
                    String wantedSoldItem = soldItems.get(userInput);
                    String[] soldValues = wantedSoldItem.split("[|]");
                    int soldInventory = Integer.parseInt(soldValues[INVENTORY]);
                    soldInventory++;

                    soldItems.replace(userInput, soldValues[0] + "|" + soldValues[1] + "|" + soldValues[2] + "|" + soldInventory);
                    System.out.println(userInput + soldValues[0] + "|" + soldValues[1] + "|" + soldValues[2] + "|" + soldInventory);
                }

                // Total is added up and inventory is decreased by 1.
                total += price;
                inventory -= 1;

                double temp = amount;

                // Changes in inventory are done through the following line. The audit log records the choice of purchase.
                items.replace(userInput, values[0] + "|" + values[1] + "|" + values[2] + "|" + inventory--);
                AuditLog.log("\n\n" + formatter.format(date) + " " +  values[0] + " " + userInput + " " + String.format("%.2f",amount) + " " + String.format("%.2f",amount-total));

            }
        }
        // If the product does not exist, it tells the user, and sends them back to the purchasing menu.
        else{
            System.out.printf("PRODUCT DOES NOT EXIST");
            VendingMachineCLI.runPurchase(items, soldItems, amount, total);
        }

        // Returns total price after selecting the product.
        return total;
    }
}