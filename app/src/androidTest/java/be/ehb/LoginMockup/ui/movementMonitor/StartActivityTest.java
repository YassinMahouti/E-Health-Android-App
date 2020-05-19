package be.ehb.LoginMockup.ui.movementMonitor;

import android.content.Context;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingPolicies;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import be.ehb.Ehealth.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class StartActivityTest {

    StartActivity startActivity;

    @Rule
    public ActivityTestRule<StartActivity> startActivityTestRule = new ActivityTestRule<>(StartActivity.class);
    public StartActivityTest() {
        IdlingPolicies.setMasterPolicyTimeout(1000, TimeUnit.SECONDS);
    }

    @Before
    public void setStartActivity()throws Exception{
        startActivity = startActivityTestRule.getActivity();
    }

    @Test
    public void userAppContext(){
        Context appContext = getInstrumentation().getTargetContext();

        assertEquals("be.ehb.haseb",appContext.getPackageName());
    }

    @Test(timeout = 1000)
    public void performClickButttonStartActivity(){
        onView(withId(R.id.button_start_activity_activity_time)).perform(click());
       // onView(withId(R.id.button_reset)).perform(click());
    }

    @After
    public void tearDown() throws Exception {
        startActivity =null;
    }
}
