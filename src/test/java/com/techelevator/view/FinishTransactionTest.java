package com.techelevator.view;

import org.junit.Assert;
import org.junit.Test;

public class FinishTransactionTest {

    @Test
    public void quarters_5_dollars(){
        double amount = 5.00;
        double total = 3.65;
        double remaining;

        double change = amount - total;

        int quarters = (int) (change / 0.25);
        remaining = change % 0.25;
        change = remaining;

        Assert.assertEquals(5,quarters);
    }

    @Test
    public void dimes_5_dollars(){
        double amount = 5.00;
        double total = 3.65;
        double remaining;

        double change = amount - total;

        int quarters = (int) (change / 0.25);
        remaining = change % 0.25;
        change = remaining;

        int dimes = (int) (change / 0.10);
        remaining = change % 0.10;
        change = remaining;

        int nickels = (int) (change / 0.05);
        remaining = change % 0.05;
        change = remaining;

        int pennies = (int) (change / 0.01);
        change = remaining;

        Assert.assertEquals(5,quarters);
        Assert.assertEquals(1,dimes);
    }

    @Test
    public void quarters_10_dollars(){
        double amount = 10.00;
        double total = 7.30;
        double remaining;

        double change = amount - total;

        int quarters = (int) (change / 0.25);
        remaining = change % 0.25;
        change = remaining;

        Assert.assertEquals(10,quarters);
    }

    @Test
    public void dimes_10_dollars(){
        double amount = 10.00;
        double total = 7.30;
        double remaining;

        double change = amount - total;

        int quarters = (int) (change / 0.25);
        remaining = change % 0.25;
        change = remaining;

        int dimes = (int) (change / 0.10);
        remaining = change % 0.10;
        change = remaining;

        int nickels = (int) (change / 0.05);
        remaining = change % 0.05;
        change = remaining;

        int pennies = (int) (change / 0.01);
        change = remaining;

        Assert.assertEquals(10,quarters);
        Assert.assertEquals(2,dimes);
    }
}
