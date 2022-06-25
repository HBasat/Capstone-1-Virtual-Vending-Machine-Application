package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class RecordReport {
    private static final int PRODUCT_NAME = 0;
    private static final int INVENTORY = 3;

    // Opens a file to record number of sales.
    public static void openReport(Map<String, String> items){

        Date date = new Date(); // This object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss aa");

        // Creates a file object to be written into.
        File logFile = new File("salesreport.txt");

        try (PrintWriter writer = new PrintWriter(new FileOutputStream(logFile, true))) {

            // For every item, it is written in the sales report file.
            for(Map.Entry<String, String> key : items.entrySet()){

                String[] values = key.getValue().split("[|]");
                writer.append(formatter.format(date) + " " + values[PRODUCT_NAME] + "|" + values[3] + "\n");
            }

        } catch (FileNotFoundException e) {
            System.out.println("salesreport.txt cannot be opened for logging. Please check filepath.");
        }

    }

}
