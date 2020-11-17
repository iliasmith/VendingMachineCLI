package com.techelevator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class AuditFile {


    private static LocalDateTime myDateObj = LocalDateTime.now();

    private static DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private static String formattedDate = myDateObj.format(myFormatObj);


    public void logDeposit(BigDecimal balance, BigDecimal dollars) {
        myDateObj = LocalDateTime.now();

        String formattedDate = myDateObj.format(myFormatObj);
        String auditPrintOut = (formattedDate + " " + "FEED MONEY:  " + "$" + dollars + " $" + balance);
        writeToFile(auditPrintOut);

    }

    public static void logPurchase(BigDecimal balance, String position, String item, BigDecimal price) {
        myDateObj = LocalDateTime.now();


        writeToFile(formattedDate + " " + item + " " + position + " " + "$" + price + " $" + balance);


    }

    public void logTransaction(BigDecimal changeGiven, BigDecimal zeroBalance) {
        myDateObj = LocalDateTime.now();


        String formattedDate = myDateObj.format(myFormatObj);
        String auditPrintOut = (formattedDate + " " + "GIVE CHANGE:  " + "$" + changeGiven + " $" + zeroBalance);
        writeToFile(auditPrintOut);

    }

    private static void writeToFile(String line) {
        try (FileOutputStream stream = new FileOutputStream("log.txt", true);
             PrintWriter writer = new PrintWriter(stream)) {
            writer.println(line);

        } catch (IOException e) {
            System.out.println("Couldn't write to file");

        }
    }

}
