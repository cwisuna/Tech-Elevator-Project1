package com.techelevator.view;

import java.math.BigDecimal;

public class Purchase {

    private static double currentMoneyProvided = 0;
    private String change = "";
    private double moneyNeeded;



    private static double quarter = .25;
    private static double dime = .10;
    private static double nickel = .05;



    // BUTTON #1 on Purchase Menu - Call This Method and the Customer adds money to the machine
    public void feedMoney(double amountOfMoney){
        currentMoneyProvided += amountOfMoney;
    }


    //BUTTON #2 on Purchase Menu - Feeds money to Machine
    public void purchaseItem(double itemPrice){
        currentMoneyProvided -= itemPrice;
    }


    //subtracts change from machine, creates String saying how much change was returned
    public void returnChange() {
        int quartersToReturn = 0;
        int dimesToReturn = 0;
        int nicklesToReturn = 0;


        int moneyAsInt = (int) Math.round(currentMoneyProvided * 100);


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
        return currentMoneyProvided;
    }

    public String getChange() {
        return change;
    }
    public double getMoneyNeeded() {
        return moneyNeeded;
    }

    //setters
    public void setCurrentMoneyProvided(double currentMoneyProvided) {
        currentMoneyProvided = currentMoneyProvided;
    }

}
