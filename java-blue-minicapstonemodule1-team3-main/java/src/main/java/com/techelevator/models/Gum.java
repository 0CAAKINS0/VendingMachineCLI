package com.techelevator.models;

import com.techelevator.models.Snack;

public class Gum extends Snack {

    public Gum(String[] snack){
        super(snack);
    }


    @Override
    public String returnMessage() {
        return "Chewy, Chewy, Lots O Bubbles!";
    }
}
