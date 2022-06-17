package com.techelevator.view;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class SelectProductTest {

    @Test
    public void read_from_map(){

        Map<String, String> tests = GatherItems.gatherItems();
        String testString = tests.get("A4");
        String[] values = testString.split("[|]");

        Assert.assertTrue(tests.containsKey("A4"));
        Assert.assertTrue(values[0].equals("Cloud Popcorn"));
        Assert.assertTrue(values[1].equals("3.65"));
        Assert.assertTrue(values[2].equals("Chip"));
        Assert.assertTrue(values[3].equals("5"));
    }

}
