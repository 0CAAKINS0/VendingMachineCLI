package com.techelevator.application;

import com.techelevator.log.Audit;
import com.techelevator.models.*;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine
{
    private List<Snack> inventory = new ArrayList();
    private Transaction transaction = new Transaction();
    private Boolean discount = false;
    private Audit audit = new Audit("audit.txt");
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyy hh:mm:ss a");


    public Transaction getTransaction() {
        return transaction;
    }

    public void run()
    {
        stockMachine();
        while(true)
        {
            UserOutput.displayHomeScreen();
            String choice = UserInput.getHomeScreenOption();

            if(choice.equals("display"))
            {
                // display the vending machine slots
                UserOutput.displayItems(inventory);
            }
            else if(choice.equals("purchase"))
            {
                // make a purchase
                while(true) {
                    choice = UserInput.getPurchaseMenuOption(transaction.getCurrentAmount());
                    if(choice.equalsIgnoreCase("m")){
                        while(true){
                            String moneyInput = UserInput.getMoneyInput();
                            if(moneyInput.equalsIgnoreCase("e")){
                                break;
                            }
                            else{
                                UserOutput.displayMessage(transaction.feedMoney(new BigDecimal(moneyInput)));
                                audit.write(dateTimeFormatter.format(LocalDateTime.now()) + " MONEY FED:\t\t$" + moneyInput + ".00\t$" + transaction.getCurrentAmount());
                            }
                        }
                    }
                    else if (choice.equalsIgnoreCase("s")){
                        UserOutput.displayItems(inventory);
                        UserOutput.displayMessage(dispenseItem(UserInput.getItemKeyInput()));
                    }
                    else if(choice.equalsIgnoreCase("f")){
                        BigDecimal previousAmount = transaction.getCurrentAmount();
                        UserOutput.displayMessage(transaction.returnMoney());
                        audit.write(dateTimeFormatter.format(LocalDateTime.now()) + " CHANGE GIVEN:\t$" + previousAmount + "\t$" + transaction.getCurrentAmount());
                        try {
                            audit.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
            else if(choice.equals("exit"))
            {
                // good bye
                System.out.println("Thanks for visiting Taste Elevator, goodbye!");
                break;
            }
        }
    }

    public void stockMachine(){
        File destinationFile = new File("catering.csv");
        try(Scanner fileScanner = new Scanner(destinationFile)){
            while(fileScanner.hasNextLine()){
                String[] currentItem = fileScanner.nextLine().split(",");
                if(currentItem[3].equals("Munchy")){
                    Snack snack = new Munchy(currentItem);
                    inventory.add(snack);
                } else if (currentItem[3].equals("Gum")){
                    Snack snack2 = new Gum(currentItem);
                    inventory.add(snack2);
                } else if (currentItem[3].equals("Drink")){
                    Snack snack3 = new Drink(currentItem);
                    inventory.add(snack3);
                } else if (currentItem[3].equals("Candy")){
                    Snack snack4 = new Candy(currentItem);
                    inventory.add(snack4);
                }
            }

        } catch(Exception e){
            System.out.println("Sorry an error occurred.");
        }
    }

    public String dispenseItem(String slotIdentifier){
        for(Snack item: inventory){
            if(slotIdentifier.equalsIgnoreCase(item.getSlotIdentifier())){
                if(item.getStock() > 0){
                    if (!discount) {
                        if (transaction.getCurrentAmount().compareTo(item.getItemPrice()) >= 0) {
                            BigDecimal previousAmount = new BigDecimal(transaction.getCurrentAmount().toString()) ;
                            transaction.setCurrentAmount(transaction.getCurrentAmount().subtract(item.getItemPrice()));
                            discount = !discount;
                            item.setStock(item.getStock() - 1);
                            audit.write(dateTimeFormatter.format(LocalDateTime.now()) + " " + item.getName() + " " + " \t" + item.getSlotIdentifier() + " $" + previousAmount + " $" + transaction.getCurrentAmount());
                            return item.getName() + " $" + item.getItemPrice() + "\n" + item.returnMessage();
                        }
                    }
                    else if (discount) {
                        if (transaction.getCurrentAmount().compareTo(item.getItemPrice().subtract(new BigDecimal("1"))) >= 0) {
                            BigDecimal previousAmount = new BigDecimal(transaction.getCurrentAmount().toString());
                            transaction.setCurrentAmount(transaction.getCurrentAmount().subtract(item.getItemPrice().subtract(new BigDecimal("1"))));
                            item.setStock(item.getStock() - 1);
                            discount = !discount;
                            audit.write(dateTimeFormatter.format(LocalDateTime.now()) + " " + item.getName() + " " + " \t" + item.getSlotIdentifier() + " $" + previousAmount + " $" + transaction.getCurrentAmount());
                            return item.getName() + " $" + (item.getItemPrice().subtract(new BigDecimal("1"))) + "\n" + item.returnMessage();
                        }
                    }
                    return "Insufficient funds.";
                } else {
                    return "Sorry, item out of stock.";
                }
            }
        } return "Invalid identifier";

    }

}
