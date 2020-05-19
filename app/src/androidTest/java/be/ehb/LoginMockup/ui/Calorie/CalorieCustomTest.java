package be.ehb.LoginMockup.ui.Calorie;


import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.w3c.dom.Text;

import be.ehb.Ehealth.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;

public class CalorieCustomTest {
    CalorieCustom CC;
    @Rule
    public ActivityTestRule<CalorieCustom> cc = new ActivityTestRule<CalorieCustom>(CalorieCustom.class);


    @Before
    public void setup() throws Exception{
        CC = cc.getActivity();
    }
    @Test
    public void shouldReturnAmountOfCaloriesFromInputUserFemale(){
        //--Able to check if the result is correct, I launch the activity and let Espresso fill in
        //--I make a simulation of a man with: height: 170(cm), weight: 100, age: 22
        onView(withId(R.id.radioFemale))
                .perform(click());

        onView(withId(R.id.radioFemale))
                .check(matches(isChecked()));

        onView(withId(R.id.radioMale))
                .check(matches(not(isChecked())));
        onView(withId(R.id.weight)).perform(typeText("100"));
        onView(withId(R.id.height)).perform(typeText("170"));
        onView(withId(R.id.age)).perform(typeText("22"));
        Espresso.closeSoftKeyboard();// able to click the button, need to close the key-board
        onView(withId(R.id.calc)).perform(click());

        int expected = 1791;
        int actual_female = CC.result; // call the function to calculate the amount of calories
        //Test if expected value is equals to actual value
        assertEquals(expected, actual_female);
    }
    @Test
    public void shouldReturnAmountOfCaloriesFromInputUserMale(){

        //---------Able to verify if the calculation is correct for a different gender
        //--Able to check if the result is correct, I launch the activity and let Espresso fill in
        //--I make a simulation of a man with: height: 170(cm), weight: 100, age: 22
        onView(withId(R.id.radioMale))
                .perform(click());

        onView(withId(R.id.radioMale))
                .check(matches(isChecked()));

        onView(withId(R.id.radioFemale))
                .check(matches(not(isChecked())));
        onView(withId(R.id.weight)).perform(typeText("100"));
        onView(withId(R.id.height)).perform(typeText("170"));
        onView(withId(R.id.age)).perform(typeText("22"));
        Espresso.closeSoftKeyboard();// able to click the button, need to close the key-board
        onView(withId(R.id.calc)).perform(click());

        int expect = 1957;
        int actual = CC.result; // call the function to calculate the amount of calories
        //Test if expected value is equals to actual value
        assertEquals(expect, actual);
    }

   @After
    public void tearDown() throws Exception{
        CC.finish();

   }
}