package com.techelevator.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

    public class AuditLog {

        public static void log(String message)  {

        /*
        String auditPath = "exercise/logs";
        File logFile = new File(auditPath, "search.log");
        logFile.createNewFile();
        */

            File logFile = new File("C:\\Users\\hanni\\Desktop\\Capstones\\capstone-1\\log.txt");

            try(PrintWriter writer = new PrintWriter(new FileOutputStream(logFile, true))){
                writer.append(message);
            }
            catch(Exception e){
            }


        /*
        File output = new File("exercise/logs/search.log");

        try(PrintWriter writer = new PrintWriter(output)){
            writer.println(message);
        }
        catch (FileNotFoundException e){
            //throw new TELogException(e);
        }
        */
        }
    }

