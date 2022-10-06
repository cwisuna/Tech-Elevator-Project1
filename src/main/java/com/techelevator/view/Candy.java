package com.techelevator.view;

import java.util.Map;

public abstract class Candy extends Item {

    public Candy(Map<String, Double> itemAndPrice, int inventory) {
        super(itemAndPrice, inventory);
    }
}
