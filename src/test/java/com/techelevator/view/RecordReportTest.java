package com.techelevator.view;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class RecordReportTest {

    File outFile = new File("salesreport.txt");

    @Test
    public void file_exists() throws Exception {
        Assert.assertTrue(outFile.exists());
    }

    @Test
    public void file_can_read() throws Exception {
        Assert.assertTrue(outFile.canRead());
    }

    @Test
    public void file_can_write() throws Exception {
        Assert.assertTrue(outFile.canWrite());
    }
}
