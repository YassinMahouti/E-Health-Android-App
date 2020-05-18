package be.ehb.LoginMockup.ui.movementMonitor;

import android.app.Instrumentation;
import android.content.Context;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import be.ehb.Ehealth.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class MonitorActivityTest {

    MonitorActivity monitorActivity;
    MovementMonitor monitor;

    @Rule
    public ActivityTestRule<MonitorActivity> monitorActivityTestRule = new ActivityTestRule<>(MonitorActivity.class);

    @Before
    public void setmonitorActivity() throws Exception{
        monitorActivity = monitorActivityTestRule.getActivity();
    }

    @Test
    public void userAppContext(){
        Context appContext = getInstrumentation().getTargetContext();
        assertEquals("be.ehb.haseb",appContext.getPackageName());
    }

    @Test
    public void performInputs(){
        onView(withId(R.id.editTextInput_monitor_activity_question_two)).perform(typeText("10"));
        onView(withId(R.id.radioButton_monitor_activity_question_two)).perform(click());
        onView(withId(R.id.editTextInput_monitor_activity_question_one)).perform(typeText("200"));
        onView(withId(R.id.radioButton_monitor_activity_question_one)).perform(click());
    }

    @Test
    public void perform_Click_Button_To_ListActivity(){
        onView(withId(R.id.editTextInput_monitor_activity_question_one)).perform(typeText("200"));
        onView(withId(R.id.radioButton_monitor_activity_question_one)).perform(click());
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.button_monitor_activity_show_activity_list)).perform(click()); //button is niet zichtbaar voor de test
    }

    @After
    public void tearDown() throws Exception {
        monitorActivity =null;
    }
}