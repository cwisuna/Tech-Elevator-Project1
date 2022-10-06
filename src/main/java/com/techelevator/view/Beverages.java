package com.techelevator.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Beverages extends Item {
    public Beverages(Map<String, Double> itemAndPrice, int inventory) {
        super(itemAndPrice, inventory);
    }

    void addDrinks(){
        itemAndPrice.put("Coca Cola", 1.50);
        itemAndPrice.put("Sprite", 1.50);
        itemAndPrice.put("Dr Pepper", 1.50);
        itemAndPrice.put("Mello Yello", 1.50);
        itemAndPrice.put("Mountain Valley", 1.50);


    }

    void currentInventory(){

    }



}
