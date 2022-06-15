package com.techelevator.view;

    public class FinishTransaction {

        private static final double QUARTER = 0.25;
        private static final double DIME = 0.10;
        private static final double NICKEL = 0.05;
        private static final double PENNY = 0.01;

        public static double finishTransaction(double amount, double total) {
            double change = -1;


            if(total > amount){
                System.out.print("Insufficient funds. Please insert more dollars.");
            }
            else {
                change = amount - total;

                double temp = 0;
                temp = change;
                double remaining;

                int quarters = (int) (temp / QUARTER);
                remaining = temp % QUARTER;
                temp = remaining;

                int dimes = (int) (temp / DIME);
                remaining = temp % DIME;
                temp = remaining;

                int nickels = (int) (temp / NICKEL);
                remaining = temp % NICKEL;
                temp = remaining;

                int pennies = (int) (temp / PENNY);

                System.out.println("Dispensing " + quarters + " quarters, " + dimes + " dimes, " + nickels + " nickels, " + pennies + " pennies.");
                return remaining;
            }
            return change;
        }
    }

