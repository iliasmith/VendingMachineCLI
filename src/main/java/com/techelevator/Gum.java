package com.techelevator;

import java.math.BigDecimal;

public class Gum extends InventoryItem {

    public Gum(String product, BigDecimal price) {
        super(product, price);
    }

    @Override
    public String getSound() {
        return "Chew Chew, Yum!";
    }
}
