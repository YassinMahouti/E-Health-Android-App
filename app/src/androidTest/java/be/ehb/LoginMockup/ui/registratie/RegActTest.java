package be.ehb.LoginMockup.ui.registratie;


import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.test.espresso.Espresso;
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
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.core.IsNot.not;
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
    { // DE EMAIL MOET GEWIJZIGD WORDEN OMDAT DEZE NU WEL AL BESTAAT
        onView(withId(R.id.email)).perform(typeText("email55@email.com"));
        onView(withId(R.id.password)).perform(typeText("hello1234"));
        //--Test if button is not null
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.regBtn)).perform(click());
        //-- Aanmaak activity dat ik wil "monitorren""
        //Hier kan wel iets fout lopen tijdens het starten van de test: dit is omdat email verificatie is toegevoegd
        Activity Main = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(Main);
        Main.finish();
    }
    @Test
    public void shouldClickTheRadioButton(){
        //---------Able to verify if the calculation is correct for a different gender
        //--Able to check if the result is correct, I launch the activity and let Espresso fill in
        //--I make a simulation of a man with: height: 170(cm), weight: 100, age: 22
        onView(withId(R.id.radio_female))
                .perform(click());

        onView(withId(R.id.radio_female))
                .check(matches(isChecked()));

        onView(withId(R.id.radio_male))
                .check(matches(not(isChecked())));

    }

    @After
    public void tearDown() throws Exception {
        mRegistratieAct =null;
    }
}