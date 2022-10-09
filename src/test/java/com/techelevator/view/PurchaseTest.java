package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PurchaseTest {
    private String location = "";
    private String name = "";
    private double price;
    private double balance = 0;




    @Test
    public void test_Feed_Money(){
        Purchase purchaseTest = new Purchase("B9", "Chris' Cookies", 4.99);
        purchaseTest.setCurrentMoneyProvided(0);

        //Arrange
        double money = 20.0;

        String expectedString = "Total Balance: $20.00";

        //Act
        purchaseTest.feedMoney(money);
        String actual = purchaseTest.currentMoneyAsString();

        //Assert
        Assert.assertEquals(expectedString, "Total Balance: $" + actual);
    }

    @Test
    public void test_Current_Money_As_String() {
        Purchase purchaseTest = new Purchase("B9", "Chris' Cookies", 4.99);
        purchaseTest.setCurrentMoneyProvided(0);


        //Arrange
        purchaseTest.setCurrentMoneyProvided(7.00);
        double expected = 12.00;

        //Act
        purchaseTest.feedMoney(5.00);
        double actual = purchaseTest.getCurrentMoneyProvided();

        //Assert
        Assert.assertEquals(expected, actual, .001);
    }


    @Test
    public void test_Purchase_Menu_Finish(){
        Purchase purchaseTest = new Purchase("B9", "Chris' Cookies", 4.99);

        //Arrange
        purchaseTest.setCurrentMoneyProvided(20);

        //Act
        purchaseTest.purchaseMenuFinish();
        double expected = 0;

        //Assert
        Assert.assertEquals(expected, purchaseTest.getCurrentMoneyProvided(), .001);

    }

    @Test
    public void test_Return_Change(){
        Purchase purchaseTest = new Purchase("B9", "Chris' Cookies", 4.99);

        //Arrange
        purchaseTest.setCurrentMoneyProvided(.40);
        String expected = "Quarters: 1, Dimes: 1, Nickles: 1";

        //Act
        purchaseTest.returnChange();
        String actual = purchaseTest.getChange();

        //Assert
        Assert.assertEquals(expected, actual);

    }


}
