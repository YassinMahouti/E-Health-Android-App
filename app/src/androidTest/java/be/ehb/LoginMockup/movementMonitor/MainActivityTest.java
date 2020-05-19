package be.ehb.LoginMockup.movementMonitor;

import android.content.Context;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import be.ehb.Ehealth.R;
import be.ehb.LoginMockup.ui.movementMonitor.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MainActivityTest {
    MainActivity mainActivity;

    @Rule
    public ActivityTestRule<MainActivity> mainActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        mainActivity = mainActivityTestRule.getActivity();
    }

    @Test
    public void userAppContext(){
        Context appContext = getInstrumentation().getTargetContext();
        assertEquals("be.ehb.haseb",appContext.getPackageName());
    }

    @Test
    public void performClickButtonToListActivity(){
        assertNotNull(mainActivity.findViewById(R.id.button_main_activity_choose_an_activity));
        onView(withId(R.id.button_main_activity_choose_an_activity)).perform(click());
    }

    @Test
    public void performClickButtonToInputActivity(){
        assertNotNull(mainActivity.findViewById(R.id.button_main_activity_burn_some_calories));
        onView(withId(R.id.button_main_activity_burn_some_calories)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
        mainActivity =null;
    }
}