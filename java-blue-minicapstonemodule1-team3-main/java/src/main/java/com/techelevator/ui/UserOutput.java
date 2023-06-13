package com.techelevator.ui;

import com.techelevator.models.Snack;

import java.math.BigDecimal;
import java.util.List;


/**
 * Responsibilities: This class should handle formatting and displaying ALL
 * messages to the user
 *
 * Dependencies: None
 */
public class UserOutput
{

    public static void displayMessage(String message)
    {
        System.out.println();
        System.out.println(message);
        System.out.println();
    }

    public static void displayHomeScreen()
    {
        System.out.println();
        System.out.println("***************************************************");
        System.out.println("                      Home");
        System.out.println("***************************************************");
        System.out.println();
    }

    public static void displayPurchaseMenu(BigDecimal currentMoney) {
        System.out.println("(M) Feed Money");
        System.out.println("(S) Select Item");
        System.out.println("(F) Finish Transaction");
        System.out.println("\nCurrent Money: $" + currentMoney);
    }

    public static void displayItems(List<Snack> inventory) {
        for (Snack item : inventory) {
            System.out.println(item.getSlotIdentifier() + ") " + item.getName() + " $" + item.getItemPrice() + " there are " + item.getStock() + " left in stock\n");
        }
    }

}
