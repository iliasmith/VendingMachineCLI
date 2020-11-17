package com.techelevator;

import java.io.*;
import java.math.BigDecimal;
import java.util.Map;

public class SalesReport {
    public static void printSalesReport(Map<String, Integer> salesRecord, BigDecimal totalSold) {

        try (FileOutputStream stream = new FileOutputStream("SalesReport.txt", false);
             PrintWriter writer = new PrintWriter(stream)) {
            for (Map.Entry<String, Integer> entry : salesRecord.entrySet()) {
                String line = entry.getKey() + "|" + entry.getValue();
                writer.println(line);

            }
            writer.println("\n**TOTAL SALES**" + " " + "$" + totalSold);


        } catch (IOException e) {
            System.out.print("error message");
        }


    }
}
