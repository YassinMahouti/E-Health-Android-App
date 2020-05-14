package be.ehb.proj.mybfpapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import be.ehb.Ehealth.R;
import be.ehb.LoginMockup.ui.corona.CoronaMain;
import be.ehb.LoginMockup.ui.corona.CoronaResult;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class CoronaMainTest {
    //---Properties-------------------------
    //---------Able to test the activity-------------
    private CoronaMain mActivity;
    //---------Able to test the Buttons-------------
    private RadioButton rd__check_first_question;
    private Button btn_check_calculation;
    private Button btn_check_show_results;

    @Rule
    //--launching mainactivity
    public ActivityTestRule<CoronaMain> mainActivityTestRule = new ActivityTestRule<CoronaMain>(CoronaMain.class);
    //--creating a activity monitor: need static mehtod Instrumentation: Monitor: (on witch you want to set it:name),result, block
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(CoronaResult.class.getName(),null,false);
    @Before
    public void setUp() throws Exception{
        //---Setup for the activity: MainActivity
        mActivity =mainActivityTestRule.getActivity();

    }
    @Before
    public void setUpButtons(){
        //---Setup for the RadioButton: (find by Id) in MainActivity-----------------
        rd__check_first_question = mActivity.findViewById(R.id.rd_accurate_symp1_ja);
        //---Setup for the Button-----------------------------------------------------
        btn_check_show_results = mActivity.findViewById(R.id.btn_show_results);
        btn_check_calculation = mActivity.findViewById(R.id.btn_saveSymptoms);
    }
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = getInstrumentation().getTargetContext();
        assertEquals("be.ehb.haseb", appContext.getPackageName());
    }
    @Test
    public void testLaunchActivity(){
        View view = mActivity.findViewById(R.id.txt_result_accurate);
        assertNotNull(view);
    }
    @Test
    public void testIfTextViewExists(){
        View view = mActivity.findViewById(R.id.txt_result_accurate);

    }
    @Test
    public void shouldWriteSomeTextInTextView(){
        //--Test write to a TextView on MainActivity
       /* TextView txt = mActivity.findViewById(R.id.txt_result_accurate);
        txt.setText("Testing message");*/
        Espresso.onView(withId(R.id.txt_result_accurate)).perform(typeText("Test"));
    }
   /* @Test
    public void tesIfProgressBarIsResponsif(){

    }*/
    @Test
    public void testIfRadioButtonExist(){
        //--One way to check if the radiobutton can be clicked is to call the method isChecked();
        rd__check_first_question.isChecked();
        //--An other way is by calling the TextView by Id and compare with 1, or assertNotNull
        assertNotNull(mActivity.findViewById(R.id.rd_accurate_symp4_nee));
    }
    @Test
    public void testIfButtonIsClickable(){
        //--One way to test if the button can be clicked is by calling the method isClickable();
        btn_check_show_results.isClickable();
    }

    @Test
    public void shouldPerformAClickOnTheCalculateButton(){
        //---Verifying the button is not Null------------------------
        assertNotNull(btn_check_show_results);
        //---Need Espresso: to have acces to the static method onView
        //----to perfrom a click:  need to import  click
        onView(withId(R.id.btn_show_results)).perform(click());
        //---------to use monitor: call instrumentation: monitor with timeout to set the monitor and a timeout
        Activity Corona = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        //---Test: activity should now be "not  null"
        assertNotNull(Corona);
        //--finish the launched activity
        Corona.finish();
    }

    @Test
    public void shouldReturnUserRisk0(){
        float expected =0.0f;
        onView(withId(R.id.btn_saveSymptoms)).perform(click());
        float actual = mActivity.userRisk;
        assertEquals(expected,actual,0.1f);
    }
    @Test
    public void submitATestResult32(){
        float expected =32.06f;
        onView(withId(R.id.rd_First_yes)).perform(click());
        onView(withId(R.id.btn_saveSymptoms)).perform(click());
        float actual = mActivity.userRisk;
        assertEquals(expected,actual,0.1f);
    }
    @Test
    public void shouldReturn25(){
        int expected= 25;
        onView(withId(R.id.rd_accurate_symp1_ja)).perform(click());
        onView(withId(R.id.btn_saveSymptoms)).perform(click());
        float actual = mActivity.accurate_risk;
        assertEquals(expected,actual,0.2);

    }

    @After
    public void tearDown() throws Exception{
        // "stop the activity enfaite"
        mActivity = null;

    }

}
