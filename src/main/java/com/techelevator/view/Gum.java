package com.techelevator.view;

import java.util.Map;

public class Gum extends Item{
    public static String sound;


    public Gum(String location, String name, double price) {
        super(location, name, price);
        this.sound = "Chew Chew, Yum!";
    }

    //Getter - print this whenever someone purchases a Gum item
    public static String getSound() {
        return sound;
    }
}



