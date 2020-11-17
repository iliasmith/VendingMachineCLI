package com.techelevator;

import java.math.BigDecimal;

public abstract class InventoryItem {
    private String position;
    private String name;
    private BigDecimal price;
    private static final int DEFAULT_INVENTORY = 5;
    private int quantity = DEFAULT_INVENTORY;
    private int purchasedAmount;


    public InventoryItem(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public int getPurchasedAmount() {
        return purchasedAmount;
    }

    public void setPurchasedAmount(int purchasedAmount) {
        this.purchasedAmount = purchasedAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String toString() {
        if (quantity == 0) {
            return "Sold Out";
        } else {
            return name + " " + price + " " + quantity;
        }

    }

    public void decreaseQuantity() {
        quantity = quantity - 1;
    }

    public abstract String getSound();

    public int returnsItemsSold() {
        return DEFAULT_INVENTORY - quantity;
    }

//    public void itemsPurchased() {
//        purchasedAmount = purchasedAmount + 1;
//
//    }


}
