package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

    public class GatherItems {

        private static final int SLOT_LOCATION = 0;
        private static final int PRODUCT_NAME = 1;
        private static final int PRICE = 2;
        private static final int TYPE = 3;

        // STEP 3: reads input file and returns map of vending machine items
        public static Map<String,String> gatherItems(){
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
    }

