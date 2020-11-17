package com.techelevator;

import java.math.BigDecimal;

public class Chip extends InventoryItem {


    public Chip(String name, BigDecimal price) {
        super(name, price);
    }

    @Override
    public String getSound() {
        return "Crunch Cruch, Yum!";
    }


}
