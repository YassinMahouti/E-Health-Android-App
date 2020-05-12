package be.ehb.LoginMockup;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;

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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoginActTest {
    //--Instantie aanmaken van de LoginActivity
    LoginAct mLoginAct;
    //---------------------------------------------------------------------------------------------------------------------------------------------------------
    @Rule
    //-- aanmaak van de test activity m.bv. ActivityTestRule (-> de activiteit dat je wil testen meegeven)
    public ActivityTestRule<LoginAct> mLoginActivityTestRule = new ActivityTestRule<LoginAct>(LoginAct.class);

    //--Instantie aanmaken van de klasse Instrumentation en daar de klasse ActivityMonitor aanroepen (om de geteste activity de "monitoren")
    Instrumentation.ActivityMonitor monitorForNavDrawerAct = getInstrumentation().addMonitor(NavDrawerAct.class.getName(),null,false);

    @Before
    public void setUpActivities() throws Exception{
        //-- "Probeer de setUp voor activities uit te voeren, anders geef exception"
        //-----Login Activity
        mLoginAct = mLoginActivityTestRule.getActivity();
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------------------
    @Test
    public void useAppContext() {
        // Context of the app under test.-> "standaard test van Android"
        Context appContext = getInstrumentation().getTargetContext();
        assertEquals("be.ehb.haseb", appContext.getPackageName());
    }

    @Test
    public void performAClickOnTextViewToGoOnRegisterActivity(){
        //Hier wordt enkel getest of de button bestaat, aanspreekbaar(clickable) is
        //Om na te gaan of de button bestaat: assertNotNul (=!0)
        assertNotNull(mLoginAct.findViewById(R.id.loginBtn));
        //Vervolgens de button indrukken: onView(espresso) perform a click
        onView(withId(R.id.createText)).perform(click());
    }

    @Test
    public void testLogginWithCredentialsExceptingMainActivityStart(){
        //--Om na te gaan of er gebruiker kan toegevoegd worden kan het volgende gedaanworden:
        //--geef een (nieuw) email adres in, vervolgens een wachtwoord, vermits dit de 2 vereisten zijn om een nieuw account aan te maken
        //--Dit doe je mbv. espresso: onView(withId(R.id.naam_van_EditText)).perform(typeText("met de gewenste text")
        onView(withId(R.id.emailTxt)).perform(typeText("email22@email.com"));
        onView(withId(R.id.psswdTxt)).perform(typeText("hello1234"));
        onView(withId(R.id.loginBtn)).perform((click()));
        //Initialiseer de gewenster "te monitoren activiteit" en zet deze gelijk aan
        // de getInstrumentation().waitForMonitorWithTimeout, en geef de aangemaakt monitor en een timeout mee(bvb 5000)
        Activity activity = getInstrumentation().waitForMonitorWithTimeout(monitorForNavDrawerAct,5000);
        //-- Als nu de activity niet null is, dan is de activiteit gestart. maw onze test is dan geslaagd=> assertNotNull(activityNaam)
        assertNotNull(activity);
        //-- sluit de activiteit af
        activity.finish();
    }
    //---------------------------------------------------------------------------------------------------------------------------------------------------------
    @After
    public void tearDown() throws Exception {
        // Voor het correct afsluiten van de test, zet je de activity op null (om deze "te stoppen"). anders geef exception. Aangemaakt activity op null zetten
        //--Veiligheidshalve met trhows Exception(=probeer dit,anders geef Exception)
        mLoginAct =null;

    }

}