package com.techelevator.view;

import java.math.BigDecimal;

public class Purchase {

    private static double currentMoneyProvided = 0;
    private double change;
    private double moneyNeeded;

    private final double NICKEL = ((.05 * 100) /100.0);
    private final double DIME = ((.10 * 100) /100.0);
    private final double QUARTER = ((.25 * 100) / 100.0);



    // BUTTON #1 on Purchase Menu - Call This Method and the Customer adds money to the machine
    public void feedMoney(double amountOfMoney){
        currentMoneyProvided += amountOfMoney;
    }


    //BUTTON #2 on Purchase Menu - Feeds money to Machine
    public void purchaseItem(double itemPrice){
        currentMoneyProvided -= itemPrice;
    }

//    //BUTTON #3 on Purchase Menu - Returns change using .5, .10, .25 (Use smallest amount of coins)
//    public void finishTransaction(){
//        if(currentMoneyProvided > 0 ){
//            getChange(currentMoneyProvided);
//        }
//    }

    //Determines How Much Change To Return
    public String returnChange() {
        int quartersToReturn = 0;
        int dimesToReturn = 0;
        int nicklesToReturn = 0;

        while(this.currentMoneyProvided > 0){
            if(this.currentMoneyProvided - QUARTER >= 0) {
                this.currentMoneyProvided -= QUARTER;
                quartersToReturn++;
                System.out.println(quartersToReturn + "quarters");

            } else if(this.currentMoneyProvided - DIME >= 0){
                this.currentMoneyProvided -= DIME;
                dimesToReturn++;
                System.out.println(dimesToReturn + "dimes");

            } else {
                while(this.currentMoneyProvided - NICKEL >= 0){
                    this.currentMoneyProvided -= NICKEL;
                    nicklesToReturn++;
                    System.out.println(nicklesToReturn + "nickles");
                }
            }
        }
        return ("Quarters:" + quartersToReturn + ", Dimes:" + dimesToReturn + ", Nickles:" + nicklesToReturn);
    }


    //getters
    public double getCurrentMoneyProvided() {
        return currentMoneyProvided;
    }
    public double getChange() {
        return change;
    }
    public double getMoneyNeeded() {
        return moneyNeeded;
    }

    //setters
    public void setCurrentMoneyProvided(double currentMoneyProvided) {
        this.currentMoneyProvided = currentMoneyProvided;
    }

    public void setChange(double change) {
        this.change = change;
    }
    public void setMoneyNeeded(double moneyNeeded) {
        this.moneyNeeded = moneyNeeded;
    }


}
