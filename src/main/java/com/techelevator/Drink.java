package com.techelevator;

import java.math.BigDecimal;

public class Drink extends InventoryItem {

    public Drink(String product, BigDecimal price) {
        super(product, price);
    }

    @Override
    public String getSound() {
        return "Glub Glub, Yum!";
    }
}
