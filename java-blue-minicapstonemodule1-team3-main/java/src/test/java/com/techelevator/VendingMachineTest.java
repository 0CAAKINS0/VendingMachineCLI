package com.techelevator;

import com.techelevator.application.VendingMachine;
import com.techelevator.models.Snack;
import com.techelevator.models.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineTest {

    @Test
    public void dispense_correct_item() {
        //Arrange
        VendingMachine test = new VendingMachine();
        test.stockMachine();
        Transaction transaction = test.getTransaction();
        transaction.setCurrentAmount(new BigDecimal("5.00"));
        //Act
        String actual = test.dispenseItem("A1");
        String expected = "U-Chews $1.65\nChewy, Chewy, Lots O Bubbles!";
        //Assert
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void dispense_insufficient_funds() {
        //Arrange
        VendingMachine test = new VendingMachine();
        test.stockMachine();
        Transaction transaction = test.getTransaction();
        transaction.setCurrentAmount(new BigDecimal("1"));
        //Act
        String actual = test.dispenseItem("A1");
        String expected = "Insufficient funds.";
        //Assert
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void dispense_wrong_identifier() {
        //Arrange
        VendingMachine test = new VendingMachine();
        test.stockMachine();
        Transaction transaction = test.getTransaction();
        transaction.setCurrentAmount(new BigDecimal("5"));
        //Act
        String actual = test.dispenseItem("Z1");
        String expected = "Invalid identifier";
        //Assert
        Assert.assertEquals(actual, expected);
    }

}
