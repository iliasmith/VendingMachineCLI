package com.techelevator;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class VendingMachine {
    private BigDecimal balance = BigDecimal.ZERO;

    public static Map<String, InventoryItem> inventoryPlacement = new LinkedHashMap<>();

    AuditFile log = new AuditFile();
    SalesReport salesReport = new SalesReport();


    public BigDecimal getBalance() {
        return balance;
    }

    //deposit money method
    public void depositMoney(int dollars) {
        this.balance = balance.add(BigDecimal.valueOf(dollars));
        this.balance = balance.setScale(2);
        BigDecimal decimalDollars = BigDecimal.valueOf(dollars);
        log.logDeposit(balance, decimalDollars.setScale(2));

    }

    // lists the inventory when option one is pressed
    public String listInventory() {
        String result = "";
        for (Map.Entry<String, InventoryItem> itemEntry : inventoryPlacement.entrySet()) {
            result += itemEntry.getKey() + " " + " " + itemEntry.getValue() + "\n";

        }
        return result;
    }

    //gets price from item
    public BigDecimal getPrice(String slotNumber) {
        InventoryItem item = inventoryPlacement.get(slotNumber);
        return item.getPrice();
    }


    public VendingMachine() {
        loadInventory();
    }

    public void loadInventory() {
        Path inventoryFile = Paths.get("inventory.txt");
        try (Scanner input = new Scanner(inventoryFile)) {
            while (input.hasNextLine()) {
                String line = input.nextLine();
                String[] lineParts = line.split("\\|");
                String position = lineParts[0];
                String name = lineParts[1];
                BigDecimal price = new BigDecimal(lineParts[2]);
                String type = lineParts[3];
                if (type.equals("Chip")) {
                    Chip product = new Chip(name, price);
                    inventoryPlacement.put(position, product);
                } else if (type.equals("Candy")) {
                    Candy product = new Candy(name, price);
                    inventoryPlacement.put(position, product);
                } else if (type.equals("Gum")) {
                    Gum product = new Gum(name, price);
                    inventoryPlacement.put(position, product);
                } else if (type.equals("Drink")) {
                    Drink product = new Drink(name, price);
                    inventoryPlacement.put(position, product);
                }
            }
        } catch (IOException e) {

        }

    }

    public String makePurchase(String position) {
        String result = "";
        InventoryItem selectedProduct = inventoryPlacement.get(position);
        if (selectedProduct.getQuantity() > 0) {
            selectedProduct.decreaseQuantity();
            balance = balance.subtract(selectedProduct.getPrice());
            result = selectedProduct.getSound();
            log.logPurchase(balance, position, selectedProduct.getName(), selectedProduct.getPrice());
        } else {
            result = "Sorry, out of stock!";
        }
        return result;
    }

    public String giveChange() {
        BigDecimal beforeBalance = getBalance();

        String result = "";
        BigDecimal balanceInPennies = balance.multiply(BigDecimal.valueOf(100));
        int numberOfPennies = balanceInPennies.intValue();
        int quarterValue = 25;
        int dimeValue = 10;
        int nickelValue = 5;
        int numberOfQuarters = numberOfPennies / quarterValue;
        numberOfPennies = numberOfPennies % quarterValue;
        int numberOfDimes = numberOfPennies / dimeValue;
        numberOfPennies = numberOfPennies % dimeValue;
        int numberOfNickels = numberOfPennies / nickelValue;
        numberOfPennies = numberOfPennies % nickelValue;


        result = "Your Change Here: " + numberOfQuarters + " quarters, " + numberOfDimes + " dimes, " + numberOfNickels + " nickels.";
        balance = BigDecimal.ZERO;
        log.logTransaction(beforeBalance, balance.setScale(2));
        return result;
    }

    public void salesReport() {
        BigDecimal totalSold = BigDecimal.ZERO;
        Map<String, Integer> saleRecord = new HashMap<>();
        for (Map.Entry<String, InventoryItem> entry : inventoryPlacement.entrySet()) {
            String itemName = entry.getValue().getName();
            int quantitySold = entry.getValue().returnsItemsSold();
            saleRecord.put(itemName, quantitySold);
            totalSold = totalSold.add(entry.getValue().getPrice().multiply(BigDecimal.valueOf(quantitySold)));

        }
        SalesReport.printSalesReport(saleRecord, totalSold);

    }


}
