package com.techelevator.models;

import com.techelevator.models.Snack;

public class Munchy extends Snack {

    public Munchy(String[] snack){
        super(snack);
    }


    @Override
    public String returnMessage() {

        return "Munchy, Munchy, so Good!";
    }
}
