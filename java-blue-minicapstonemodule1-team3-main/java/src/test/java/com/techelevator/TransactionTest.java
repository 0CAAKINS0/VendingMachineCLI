package com.techelevator;

import com.techelevator.models.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class TransactionTest {
    Transaction transaction = new Transaction();

    @Test
    public void returnsCorrectChange() {
        transaction.setCurrentAmount(new BigDecimal("10.35"));
        String expectedValue = "Your change is 10 dollar(s), 1 quarter(s), 1 dime(s), and 0 nickel(s)";
        String actualValue = transaction.returnMoney();

        Assert.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void feed_money_accepts_correctly(){
        String actualValue = transaction.feedMoney(new BigDecimal("5"));
        String expectedValue = "Money Provided: $5.00\nUpdated amount: $5.00";

        Assert.assertEquals(expectedValue,actualValue);
    }


}
