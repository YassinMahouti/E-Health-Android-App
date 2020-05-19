package com.example.android_drinkwaterreminder;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import be.ehb.LoginMockup.ui.drinkwater.DrinkWaterReminderActivity;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;

public class DrinkWaterReminderActivityTest {
    private DrinkWaterReminderActivity drinkWaterReminderActivity;
    //Creates another Local Date instance and set time to notif
    Date date;
    DateFormat dateFormat;
    String str_Date;

    @Rule
    public ActivityTestRule<DrinkWaterReminderActivity> mainActivityActivityTestRule = new ActivityTestRule<DrinkWaterReminderActivity>(DrinkWaterReminderActivity.class);

    //--Initialisation of the screen
    @Before
    public void setup() throws Exception {
        drinkWaterReminderActivity = mainActivityActivityTestRule.getActivity();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date =java.util.Calendar.getInstance().getTime();
        str_Date = dateFormat.format(date);
    }

    @Test
    public void shouldReturnAnAmountOfWaterToDrink() {
        double expected = 2.87;
        double actual = drinkWaterReminderActivity.calcToDrinkWater(87);
        assertEquals(expected, actual , 0.1);
    }
    @Test
    public void compareDates(){//compares the str_Date method
        String expected = "2020-05-19"; // expected datum van vandaag, moet aangepast worden
        String actual = str_Date;
       assertEquals(expected , actual);
    }
    @After
    public void tearDown() throws Exception {
        drinkWaterReminderActivity = null;
    }

}