package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GatherSold {

    private static final int SLOT_LOCATION = 0;
    private static final int PRODUCT_NAME = 1;
    private static final int PRICE = 2;
    private static final int TYPE = 3;

    // Reads input file and returns map of vending machine items to be used in the sales report
    public static Map<String,String> gatherSold(){
        File text = new File("vendingmachine.csv");

        // A map is used to store the items entries. Items sold are set to 0 upon starting the program.
        Map<String,String> items = new HashMap<>();
        int inventory = 0;

        // Scanner opens to read from file.
        try(Scanner input = new Scanner(text)){

            // Read file to gather items.
            while(input.hasNextLine()){

                // String of item information is split and inserted into a map.
                String line = input.nextLine();
                String[] lineSplit = line.split("[|]");
                items.put(lineSplit[SLOT_LOCATION], lineSplit[PRODUCT_NAME] + "|" + lineSplit[PRICE] + "|" + lineSplit[TYPE] + "|" + inventory);
            }
        }
        // If file cannot be found, output to user.
        catch(FileNotFoundException e){
            System.out.println("vendingmachine.csv cannot be opened for reading. Please check filepath.");
        }
        return items;
    }
}