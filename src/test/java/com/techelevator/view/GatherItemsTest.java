package com.techelevator.view;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.Map;

public class GatherItemsTest {

    @Test
    public void DisplayItems(){

        Map<String,String> tests = GatherItems.gatherItems();
        //tests.put("A1", "Potato Crisps|3.05|Chip|5");

        Assert.assertTrue(tests.containsKey("A1"));
        Assert.assertTrue(tests.containsValue("Potato Crisps|3.05|Chip|5"));

    }


}
