package be.ehb.proj.mybfpapp;

import android.content.Intent;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

import javax.inject.Inject;

import be.ehb.LoginMockup.ui.corona.User;

import static org.junit.Assert.*;

public class UserTest {
    private User user;
    private User user2;


    @Before
    public void UserInitEmptyConstructor(){
        user = new User();
    }
    @Before
    public void UserInitWithConstructor()
    {
        //--Testing the constructor by giving some values.
        //--Most important values to check are: numberOfSymptoms, date, and the user_risk(%)
        user2 = new User(1,String.valueOf(new Date()),1,20.0f);
    }

    @Test
    public void shouldReturnEmptyConstructor(){
        //--By calling UserInitEmptyConstructor() I expect a user with an empty constructor is created.
        UserInitEmptyConstructor();
    }
    @Test
    public void shouldReturnFalseTestEmptyConstructor(){
        //--By calling assertNotEquals() I verify if a user with an empty constructor is created and unique,
        //--by verifying if a new user(with also an empty constructor) is different from the first.
        User expected = new User();
        User actual = user;
        assertNotEquals(expected,actual);
    }
    @Test
    public void shouldReturnFalseTestWithConstructor(){
        //--By calling assertNotEquals() I verify if a user with a constructor is created and is unique,
        //--by verifying if a new user(with the same values) is different from the first.
        User expected = new User();
        User actual = user2;
        assertNotEquals(expected,actual);
    }
    @Test
    public void shouldCreateUserWithGivenValues(){
        User actual = user2;
        User expected = new User(1,String.valueOf(new Date()),1,20.0f);

        assertEquals(expected.getCountSymptoms(),actual.getCountSymptoms());
        assertEquals(expected.getDate(),actual.getDate());
        assertEquals(expected.getUser_id(),actual.getUser_id());
        assertEquals(expected.getUser_risk(),actual.getUser_risk(),0.1f);
    }
    /*@Test
    public void shouldReturnAMessage()
    {
        float float_risk =30;
        String expected ="You are ill, you maybe have a Griep or maybe just a Verkoudheid.";
        mainActivity = new MainActivity();
        String actual = mainActivity.messageFromUserRisk(float_risk);
        assertEquals(expected,actual);
    }*/




}