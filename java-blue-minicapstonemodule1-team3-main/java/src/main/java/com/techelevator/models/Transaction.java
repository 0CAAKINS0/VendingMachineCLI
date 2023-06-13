package com.techelevator.models;

import java.math.BigDecimal;

public class Transaction {
    private BigDecimal currentAmount = new BigDecimal("0.00");

    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(BigDecimal currentAmount){
        this.currentAmount = currentAmount;
    }

    public String feedMoney(BigDecimal amountFed){
        if(amountFed.equals(new BigDecimal("1")) || amountFed.equals(new BigDecimal("5")) || amountFed.equals(new BigDecimal("10")) || amountFed.equals(new BigDecimal("20"))){
            this.currentAmount = currentAmount.add(amountFed);
            return "Money Provided: $" + amountFed + ".00\nUpdated amount: $" + currentAmount;
        }
        return "Invalid denomination";
    }

    public String returnMoney() {
        int dollars = 0;
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;

        dollars = currentAmount.intValue();
        this.currentAmount = currentAmount.subtract(new BigDecimal(dollars));

        while(currentAmount.compareTo(new BigDecimal(".25")) >= 0) {
            currentAmount = currentAmount.subtract(new BigDecimal(".25"));
            quarters++;
        }
        while(currentAmount.compareTo(new BigDecimal(".1")) >= 0) {
            currentAmount = currentAmount.subtract(new BigDecimal(".10"));
            dimes++;
        }
        while(currentAmount.compareTo(new BigDecimal(".05")) >= 0) {
            currentAmount = currentAmount.subtract(new BigDecimal(".05"));
            nickels++;
        }
        this.currentAmount = new BigDecimal("0.00");

        return "Your change is " + dollars + " dollar(s), " + quarters + " quarter(s), " + dimes + " dime(s), and " + nickels + " nickel(s)";
        
    }

}
