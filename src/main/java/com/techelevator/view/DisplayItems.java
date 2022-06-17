package com.techelevator.view;

import java.util.Map;

    public class DisplayItems {

        private static final int PRODUCT_NAME = 1;
        private static final int PRICE = 2;
        private static final int TYPE = 3;

        // STEP 5: displays vending machine items. will show if inventory is sold out.
        public static void displayItems(Map<String, String> items){

            // Print each item.
            for(Map.Entry<String, String> key : items.entrySet()){

                String[] values = key.getValue().split("[|]");
                int inventory = Integer.parseInt(values[TYPE]);

                // If the inventory of an item is 0, prints information of item and "SOLD OUT" to screen.
                if(inventory == 0){
                    System.out.println(key.getKey() + " - " + values[PRODUCT_NAME - 1] + " $" + values[PRICE - 1] + " SOLD OUT");
                }
                // Otherwise, prints out information of item and remaining inventory.
                else System.out.println(key.getKey() + " - " + values[PRODUCT_NAME - 1] + " $" + values[PRICE - 1] + " x" + inventory);
            }
        }

    }