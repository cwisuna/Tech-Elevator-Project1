package com.techelevator.view;

import java.util.Map;

public class Item {

    private String location;
    private String name;
    private double price;
    private int quantity;

    public Item(String location, String name, double price) {
        this.location = location;
        this.name = name;
        this.price = price;
        this.quantity = 5;
    }


    //Getters and Setters
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    //To String (String Print out when Customer selects 1 from the main menu
    @Override
    public String toString(){
        return location + ", " +  name + ", " + "$" + price + ", " + quantity + " are remaining \n";
    }

}
