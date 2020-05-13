package be.ehb.LoginMockup.ui.registratie;


import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.test.rule.ActivityTestRule;

import com.google.firebase.auth.FirebaseAuth;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import be.ehb.Ehealth.R;
import be.ehb.LoginMockup.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.runner.RunWith;

public class RegActTest {
    private RegAct mRegistratieAct;
    @Rule
    public ActivityTestRule<RegAct> mRegistratieActivityTestRule = new ActivityTestRule<RegAct>(RegAct.class);
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(MainActivity.class.getName(),null,false);
    @Before
    public void setUp()throws Exception {
        //--Setup voor de Registratie Activiteit
        mRegistratieAct = mRegistratieActivityTestRule.getActivity();

    }

    @Test
    public void testIfPasswordFieldExists(){
        View view = mRegistratieAct.findViewById(R.id.password);
        assertNotNull(view);
    }

    @Test
    public void shouldClickOnRegisterButton(){
        onView(withId(R.id.regBtn)).perform(click());
    }
    @Test
    public void shouldWriteSomeTextInPasswordField(){

        onView(withId(R.id.email)).perform(typeText("email@email.com"));
    }

    @Test
    public void shouldCreateANewUserOnClickWithGivenValues()
    {
        onView(withId(R.id.email)).perform(typeText("email22@email.com"));
        onView(withId(R.id.password)).perform(typeText("hello1234"));
        //--Test if button is not null
        onView(withId(R.id.regBtn)).perform(click());
        //-- Aanmaak activity dat ik wil "monitorren""
        Activity Main = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(Main);
        Main.finish();
    }

    @After
    public void tearDown() throws Exception {
        mRegistratieAct =null;
    }
}