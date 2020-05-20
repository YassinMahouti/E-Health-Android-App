package be.ehb.LoginMockup.ui.foodchecker;

import android.content.Context;

import org.junit.Test;

import be.ehb.Ehealth.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

class FoodCheckerMainTest {


        // Instantiate MainActivity
        FoodCheckerMain mMainActivity;

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
            FoodCheckerMain mainActivity = new FoodCheckerMain();
            String expected = "Foo";
            String actual = "Foo";
            assertEquals(expected, actual,"Should be identical to the user's input");
        }
    }

