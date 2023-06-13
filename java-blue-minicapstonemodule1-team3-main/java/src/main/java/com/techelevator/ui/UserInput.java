package com.techelevator.ui;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

/**
 * Responsibilities: This class should handle receiving ALL input from the User
 *
 * Dependencies: None
 */
public class UserInput
{
    private static Scanner scanner = new Scanner(System.in);

    public static String getHomeScreenOption()
    {
        System.out.println("What would you like to do?");
        System.out.println();

        System.out.println("D) Display Vending Machine Items");
        System.out.println("P) Purchase");
        System.out.println("E) Exit");

        System.out.println();
        System.out.print("Please select an option: ");

        String selectedOption = scanner.nextLine().toLowerCase();
        String option = selectedOption.trim();

        if (option.equals("d"))
        {
            return "display";
        }
        else if (option.equals("p"))
        {
            return "purchase";
        }
        else if (option.equals("e"))
        {
            return "exit";
        }
        else
        {
            return "";
        }

    }

    public static String getPurchaseMenuOption(BigDecimal currentMoney) {
        System.out.println("(M) Feed Money");
        System.out.println("(S) Select Item");
        System.out.println("(F) Finish Transaction");
        System.out.println("\nCurrent Money: $" + currentMoney);

        String response = scanner.nextLine().trim().toLowerCase();

        return response;
    }

    public static String getItemKeyInput(){
        System.out.print("Please enter the item key: ");
        return scanner.nextLine().trim();
    }

    public static String getMoneyInput(){
        System.out.println("Please enter a bill (1s, 5s, 10s, 20s): ");
        System.out.println("Enter 'e' to go back to purchasing menu");
        String response = scanner.nextLine();


        return response;
    }


}
