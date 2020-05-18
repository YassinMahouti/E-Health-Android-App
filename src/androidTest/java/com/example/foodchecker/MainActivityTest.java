package com.example.foodchecker;

import android.content.Context;

import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class MainActivityTest {
    // Instantiate MainActivity
    MainActivity mMainActivity;

    @Test
    public void useAppContext() {
        // Context of the app under test -> "standard test of Android"
        Context appContext = getInstrumentation().getTargetContext();
        assertEquals("be.ehb.haseb", appContext.getPackageName());
    }

    @Test
    public void testOnClick() {
        // Tests whether the button exists and is clickable
        assertNotNull(mMainActivity.findViewById(R.id.buttonFetchFood));
        // Then press the button onView (espresso) to perform a click
        onView(withId(R.id.createText)).perform(click());
    }

    @Test
    public void hasData() {
        MainActivity mainActivity = new MainActivity();
        String expected = "Foo";
        String actual = "Foo";
        assertEquals(expected, actual,"Should be identical to the user's input");
    }
}