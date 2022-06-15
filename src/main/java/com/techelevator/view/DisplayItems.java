package com.techelevator.view;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.io.File;

    public class DisplayItems {

        private static final int SLOT_LOCATION = 0;
        private static final int PRODUCT_NAME = 1;
        private static final int PRICE = 2;
        private static final int TYPE = 3;

        // STEP 5: displays vending machine items. will show if inventory is sold out.
        public static void displayItems(Map<String, String> items){


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

    }

