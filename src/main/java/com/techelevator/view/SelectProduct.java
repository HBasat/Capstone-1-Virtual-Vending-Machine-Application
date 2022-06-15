package com.techelevator.view;

import com.techelevator.VendingMachineCLI;

import javax.sound.midi.Soundbank;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class SelectProduct {

    private static final int PRICE = 2;
    private static final int TYPE = 3;

    // STEP 7.2: selecting the product with user input
    public static double selectProduct(Map<String, String> items, double amount, double total){

        Date date = new Date(); // This object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss aa");

        DisplayItems.displayItems(items);

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
                VendingMachineCLI.runPurchase(items, amount, total);
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
                inventory -= 1;

                double temp = amount;

                //System.out.println(values[0] + "|" + values[1] + "|" + values[2] + "|" + inventory);
                items.replace(userInput, values[0] + "|" + values[1] + "|" + values[2] + "|" + inventory--);
                //System.out.printf(items.get(userInput));

                AuditLog.log("\n" + formatter.format(date) + " " +  values[0] + " " + userInput + " " + String.format("%.2f",amount) + " " + String.format("%.2f",amount-total));

            }
        } else{
            System.out.printf("PRODUCT DOES NOT EXIST");
            VendingMachineCLI.runPurchase(items, amount, total);
        }

        return total;
    }

}
