package com.techelevator.models;

import com.techelevator.models.Snack;

public class Drink extends Snack {

    public Drink(String[] snack){
        super(snack);
    }

    @Override
    public String returnMessage() {
        return "Drinky, Drinky, Slurp Slurp!";
    }
}
