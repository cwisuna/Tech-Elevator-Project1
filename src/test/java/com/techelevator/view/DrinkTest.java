package com.techelevator.view;

import org.junit.*;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DrinkTest {

    Drink drinkItem = new Drink("B4", "Chris' Cola", 4.99);

    @Before

    @Test
    public void item_Returns_Correct_Sound(){
        //Arrange
        String expected = "Glug Glug, Yum!";
        //Act
        String actual = drinkItem.getSound();
        //Assert
        Assert.assertEquals(expected, actual);
    }
}
