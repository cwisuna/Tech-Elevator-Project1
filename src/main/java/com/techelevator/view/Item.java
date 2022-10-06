package com.techelevator.view;

import java.util.Map;

public abstract class Item {
    public Map<String, Double> itemAndPrice;
    private int inventory;

    public Item(Map<String, Double> itemAndPrice, int inventory) {
        this.itemAndPrice = itemAndPrice;
        this.inventory = inventory;
    }
}
