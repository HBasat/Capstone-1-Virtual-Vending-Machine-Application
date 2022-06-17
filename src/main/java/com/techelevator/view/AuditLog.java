package com.techelevator.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;


// Opens a file object that appends to an existing log.txt. If it cannot append to a file, an exception is output to user.
public class AuditLog {

        public static void log(String message) {

            File logFile = new File("C:\\Users\\hanni\\Desktop\\Capstones\\capstone-1\\log.txt");

            try (PrintWriter writer = new PrintWriter(new FileOutputStream(logFile, true))) {
                writer.append(message);
            } catch (Exception e) {
                System.out.println("File cannot be opened for audit logging. Please check filepath.");
            }
        }
    }