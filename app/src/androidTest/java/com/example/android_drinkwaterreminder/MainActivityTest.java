package com.example.android_drinkwaterreminder;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
class MainActivityTest {
    private MainActivity mainActivity;

    @Before     //init
    public void setup() throws Exception {
        mainActivity = new MainActivity();
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