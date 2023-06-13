package com.techelevator.models;

import java.math.BigDecimal;

public abstract class Snack {
    private String slotIdentifier;
    private String name;
    private BigDecimal itemPrice;
    private int stock = 6;

    public Snack (String[] snack){
        this.slotIdentifier = snack[0];
        this.name = snack[1];
        this.itemPrice =  new BigDecimal(snack[2]);
    }

    public abstract String returnMessage();

    public String getSlotIdentifier() {
        return slotIdentifier;
    }

    public void setSlotIdentifier(String slotIdentifier) {
        this.slotIdentifier = slotIdentifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
