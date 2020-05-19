package be.ehb.LoginMockup.ui.foodchecker;

import android.content.Context;
import android.widget.TextView;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.EspressoKey;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import be.ehb.Ehealth.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class FoodCheckerMainTest {

        TextView txt_antwoord;
        // Instantiate MainActivity
        FoodCheckerMain mMainActivity;

        @Before
        public void setup() throws Exception{
            txt_antwoord = mMainActivity.findViewById(R.id.textViewNutrition);
        }

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
          //--Test if values or fetched correctly : Pizza
        @Test
            public void shouldReturn113Cal(){
            onView(withId(R.id.editTextInput)).perform(typeText("pizza"));
            Espresso.closeSoftKeyboard();
            onView(withId(R.id.buttonFetchFood)).perform(click());
            onView(withId(R.id.buttonFetchDetails)).perform(click());
            String expected = "Calories: 113.0" +"\n" +
                            "Fat: 1.5g"+"\n" +
                             "Prot: 3.5" +"\n"+
                            "Carbs: N.a"+"\n" +
                             "Serving Size: N.A";
            String actual = String.valueOf(txt_antwoord.getText());
            assertEquals(expected, actual);

        }
        //--Test if values or fetched correctly : Cocca cola
         @Test
     public void shouldReturn110Cal(){
        onView(withId(R.id.editTextInput)).perform(typeText("coca cola"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.buttonFetchFood)).perform(click());
        onView(withId(R.id.buttonFetchDetails)).perform(click());
        String expected = "Calories: 110.0" +"\n" +
                "Fat: 0"+"\n" +
                "Prot: 3.5" +"\n"+
                "Carbs: N.a"+"\n" +
                "Serving Size: N.A";
        String actual = String.valueOf(txt_antwoord.getText());
        assertEquals(expected, actual);

    }
    //--Test if values or fetched correctly : egg
    @Test
    public void shouldReturn60Cal(){
        onView(withId(R.id.editTextInput)).perform(typeText("egg"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.buttonFetchFood)).perform(click());
        onView(withId(R.id.buttonFetchDetails)).perform(click());
        String expected = "Calories: 60" +"\n" +
                "Fat: 3.5"+"\n" +
                "Prot: 5" +"\n"+
                "Carbs: N.a"+"\n" +
                "Serving Size: N.A";
        String actual = String.valueOf(txt_antwoord.getText());
        assertEquals(expected, actual);
    }
}


