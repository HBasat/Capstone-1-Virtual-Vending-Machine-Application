package com.techelevator.view;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FinishTransaction {

        private static final double QUARTER = 0.25;
        private static final double DIME = 0.10;
        private static final double NICKEL = 0.05;
        private static final double PENNY = 0.01;

        public static double finishTransaction(double amount, double total) {

            Date date = new Date(); // This object contains the current date value
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss aa");

            // Set to -1 to be an indicator. if finishTransaction() outputs -1 to the method in which it was called, it will trigger a different set of code.
            double change = -1;

            // If the total price to pay is more than the amount provided, there is no change given.
            if(total > amount){
                System.out.print("Insufficient funds. Please insert more dollars.");
            }

            // Otherwise, the total is less than the amount provided, and we can output change in lowest amount of coins.
            else {
                // Change is then changed to remainder of amount provided, and is logged into the audit log.
                change = amount - total;

                AuditLog.log("\n" + formatter.format(date) + " GIVE CHANGE: $" + String.format("%.2f",change));
                double remaining;

                int quarters = (int) (change / QUARTER);
                remaining = change % QUARTER;
                change = remaining;

                int dimes = (int) (change / DIME);
                remaining = change % DIME;
                change = remaining;

                int nickels = (int) (change / NICKEL);
                remaining = change % NICKEL;
                change = remaining;

                int pennies = (int) (change / PENNY);
                change = remaining;

                // Number of coins returned are output to user, and logged in the audit log.
                System.out.println("Dispensing " + quarters + " quarters, " + dimes + " dimes, " + nickels + " nickels, " + pennies + " pennies.");
                AuditLog.log(" $" + String.format("%.2f",remaining) + "\n");
                return change;
            }

            // By this point in the code, change is still == -1. The method returns -1 to the method in which it was called.
            return change;
        }
    }