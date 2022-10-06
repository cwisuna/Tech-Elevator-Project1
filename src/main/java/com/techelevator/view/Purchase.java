package com.techelevator.view;

import java.math.BigDecimal;

public class Purchase {
    public double currentMoneyProvided = 0;
    public double change;
    public double moneyNeeded;

    public final double NICKLE = .05;
    public final double DIME = .10;
    public final double QUARTER = .25;



    // BUTTON #1 on Purchase Menu - Call This Method and the Customer adds money to the machine
    public void feedMoney(double amountOfMoney){
        this.currentMoneyProvided += amountOfMoney;
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
                    currentMoneyProvided -= NICKLE;
                    nicklesToReturn++;
                }
            }
        }
        return ("Quarters:" + quartersToReturn + " Dimes:" + dimesToReturn + " Nickles:" + nicklesToReturn);

    }


    public double getCurrentMoneyProvided() {
        return currentMoneyProvided;
    }

    public void setCurrentMoneyProvided(double currentMoneyProvided) {
        this.currentMoneyProvided = currentMoneyProvided;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public double getMoneyNeeded() {
        return moneyNeeded;
    }

    public void setMoneyNeeded(double moneyNeeded) {
        this.moneyNeeded = moneyNeeded;
    }


}
