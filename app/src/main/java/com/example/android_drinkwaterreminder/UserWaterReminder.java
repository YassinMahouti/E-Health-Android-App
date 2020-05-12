package com.example.android_drinkwaterreminder;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class UserWaterReminder {
    private String userId;
    private double amountOfDrunkWater;
    private String weight;
    private String str_Date;

    //This is the default constructor
    public UserWaterReminder() {

    }

    //This is the parametirized constructor
    public UserWaterReminder(String userId, int amountOfDrunkWater, String weight, String str_Date) {
        this.userId = userId;
        this.amountOfDrunkWater = amountOfDrunkWater;
        this.weight = weight;
        this.str_Date = str_Date;
    }


    //---Getters en Setters--//

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getAmountOfDrunkWater() {
        return amountOfDrunkWater;
    }

    public void setAmountOfDrunkWater(double amountOfDrunkWater) {
        this.amountOfDrunkWater = amountOfDrunkWater;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getStr_Date() {
        return str_Date;
    }

    public void setStr_Date(String str_Date) {
        this.str_Date = str_Date;
    }
}
