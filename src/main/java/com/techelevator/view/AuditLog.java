package com.techelevator.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

// Opens a file object that appends to an existing log.txt. If it cannot append to a file, an exception is output to user.
public class AuditLog {

        public static void log(String message) {

            File logFile = new File("log.txt");

            try (PrintWriter writer = new PrintWriter(new FileOutputStream(logFile, true))) {
                writer.append(message);
            } catch (Exception e) {
                System.out.println("log.txt cannot be opened for audit logging. Please check filepath.");
            }
        }
    }