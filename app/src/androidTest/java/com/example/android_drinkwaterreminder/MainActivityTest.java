package com.example.android_drinkwaterreminder;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.android_drinkwaterreminder.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class MainActivityTest {
    private MainActivity mainActivity;
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

   @Before     //init
    public void setup() {
       // mainActivity = new MainActivity();
       mainActivity = mainActivityActivityTestRule.getActivity();
    }

    @Test
    public void shouldReturnAnAmountOfWaterToDrink() {
        double expected = 2.80;
        double actual = mainActivity.calcToDrinkWater(87);
        //Test the calc, is expected == actual
        assertEquals(expected, actual);
    }

    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }

}