package com.techelevator.models;

import com.techelevator.models.Snack;

public class Candy extends Snack {

    public Candy(String[] snack){
        super(snack);
    }

    @Override
    public String returnMessage() {
        return "Sugar, Sugar, so Sweet!";
    }
}
