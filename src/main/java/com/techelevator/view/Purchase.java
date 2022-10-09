package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Purchase extends Item{

    private static double currentMoneyProvided = 0;
    private String change = "";



    private static double quarter = .25;
    private static double dime = .10;
    private static double nickel = .05;

    public Purchase(String location, String name, double price) {
        super(location, name, price);
    }


    // BUTTON #1 on Purchase Menu - FEED Money - adds money, logs deposit, prints balance
    public void feedMoney(double amountOfMoney){
        currentMoneyProvided += amountOfMoney;
        writeFeedToFile(amountOfMoney);
        System.out.println("Total Balance: $" + currentMoneyAsString());
    }

    public void purchaseMenuSelectItem(Item itemSelection){
        if(itemSelection.getQuantity() > 0){
            if (this.currentMoneyProvided >= itemSelection.getPrice()) {
                this.currentMoneyProvided -= itemSelection.getPrice();

                itemSelection.dispenseItem(itemSelection);

                System.out.println("Remaining total: " + currentMoneyAsString());

            } else {
                System.out.println("Insert More Money to Purchase Item");
            }

            //If item is out of Stock, Print Error Message
        } else {
            System.out.println("Sorry, this item is out of stock.");
        }

        writePurchaseToFile(itemSelection);

    }
    public void purchaseMenuFinish(){
        writeGiveChangeToFile();
        returnChange();
        System.out.println(getChange());
        setCurrentMoneyProvided(0);

    }

    //subtracts change from machine, creates String saying how much change was returned
    public void returnChange() {
        int quartersToReturn = 0;
        int dimesToReturn = 0;
        int nicklesToReturn = 0;


        int moneyAsInt = (int) Math.round(this.currentMoneyProvided * 100);


        while(moneyAsInt > 0){
            if(moneyAsInt - 25 >= 0) {
                this.currentMoneyProvided -= quarter;
                quartersToReturn++;
                moneyAsInt -= 25;

            } else if(moneyAsInt - 10 >= 0){
                this.currentMoneyProvided -= dime;
                dimesToReturn++;
                moneyAsInt -= 10;

            } else {
               while(moneyAsInt - 5 > 0)
                this.currentMoneyProvided -= nickel;
                nicklesToReturn++;
                moneyAsInt -= 5;
            }
        }
        this.change = "Quarters: " + quartersToReturn + ", Dimes: " + dimesToReturn + ", Nickles: " + nicklesToReturn;
    }
    public String currentMoneyAsString(){
        String returnCurrentMoneyProvided = String.format("%.2f", currentMoneyProvided);
        return returnCurrentMoneyProvided;
    }




    //getters
    public double getCurrentMoneyProvided() {
        return this.getCurrentMoneyProvided();
    }
    public String getChange() {
        return this.change;
    }

    //setters
    public void setCurrentMoneyProvided(double money) {
        this.currentMoneyProvided = currentMoneyProvided;
    }



    public void writePurchaseToFile(Item itemSelection){
        //Writing transaction log to Log.txt
        File targetFile = new File("src", "Log.txt");

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
        String dateString = dateFormat.format(new Date());

        try(PrintWriter writer = new PrintWriter(new FileOutputStream(targetFile, true))){
            writer.println(dateString + " " + itemSelection.getName() + " "
                    + itemSelection.getLocation() + " " + itemSelection.getPrice() + " "
                    + currentMoneyAsString());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    public void writeFeedToFile(double moneyFed){
        //Writing transaction log to Log.txt
        File targetFile = new File("src", "Log.txt");

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
        String dateString = dateFormat.format(new Date());

        try(PrintWriter writer = new PrintWriter(new FileOutputStream(targetFile, true))){
            writer.println(dateString + " FEED MONEY: $" + moneyFed + " $" + currentMoneyAsString());
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
    public void writeGiveChangeToFile(){
        //Writing transaction log to Log.txt
        File targetFile = new File("src", "Log.txt");

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
        String dateString = dateFormat.format(new Date());

        try(PrintWriter writer = new PrintWriter(new FileOutputStream(targetFile, true))){
            writer.println(dateString + " GIVE CHANGE: $" + currentMoneyAsString() + " $0.00");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
