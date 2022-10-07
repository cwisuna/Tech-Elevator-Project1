package com.techelevator.view;

import java.util.Map;

public class Item {

    private String name;
    private double price;
    private int quantity;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantity = 5;
    }



    //To String (String Print out when Customer selects 1 from the main menu


    @Override
    public String toString() {
        return  name + " -> " + quantity + " remaining \n";
    }

    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }




}
