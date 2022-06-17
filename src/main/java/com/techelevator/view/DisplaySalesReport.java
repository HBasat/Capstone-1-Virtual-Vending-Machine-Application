package com.techelevator.view;
import java.util.Map;

public class DisplaySalesReport {

    private static final int PRODUCT_NAME = 1;
    private static final int PRICE = 2;
    private static final int TYPE = 3;

    // Displays vending machine items sales.
    public static void displayItems(Map<String, String> items){

        // Print each item.
        for(Map.Entry<String, String> key : items.entrySet()){

            String[] values = key.getValue().split("[|]");
            int inventory = Integer.parseInt(values[TYPE]);

            System.out.println(values[PRODUCT_NAME - 1] + "|" + inventory);
        }
    }
}


// System.out.println(values[PRODUCT_NAME - 1] + "|" + inventory);