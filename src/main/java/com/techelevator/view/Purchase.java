package com.techelevator.view;

import java.math.BigDecimal;

public class Purchase {
    private double currentMoneyProvided = 0;
    private double change;
    private double moneyNeeded;

    private final double NICKEL = .05;
    private final double DIME = .10;
    private final double QUARTER = .25;



    // BUTTON #1 on Purchase Menu - Call This Method and the Customer adds money to the machine
    public void feedMoney(double amountOfMoney){
        currentMoneyProvided += amountOfMoney;
    }

    //BUTTON #2 on PURCHASE MENU
    //NEED TO CREATE METHOD FOR PURCHASING ITEM
    //Uses the Map to select item from the Key slot
    //
    //maybe we organize the keys alphabetically?


    //BUTTON #3 on Purchase Menu - Returns change using .5, .10, .25 (Use smallest amount of coins)
    public void finishTransaction(){
        if(currentMoneyProvided > 0 ){
            getChange(currentMoneyProvided);
        }
    }

    //Determines How Much Change To Return
    public String getChange(double currentMoneyProvided) {
        BigDecimal currentMoney = new BigDecimal(currentMoneyProvided);
        int quartersToReturn = 0;
        int dimesToReturn = 0;
        int nicklesToReturn = 0;

        while(currentMoneyProvided > 0){
            if(currentMoneyProvided - .25 != 0) {
                currentMoneyProvided -= QUARTER;
                change += QUARTER;
                quartersToReturn++;

            } else if(currentMoneyProvided - .10 != 0){
                currentMoneyProvided -= DIME;
                change += DIME;
                dimesToReturn++;

            } else {

                while(currentMoneyProvided > 0){
                    currentMoneyProvided -= NICKEL;
                    nicklesToReturn++;
                }
            }
        }
        return ("Quarters:" + quartersToReturn + " Dimes:" + dimesToReturn + " Nickles:" + nicklesToReturn);

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
