package be.ehb.LoginMockup.ui.calorie;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import be.ehb.Ehealth.R;
import be.ehb.LoginMockup.ui.Calorie.CalorieCalc;
import be.ehb.LoginMockup.ui.Calorie.CalorieCustom;
import be.ehb.LoginMockup.ui.Calorie.CalorieOwn;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class CalorieCalcTest {
    //espresso: UI
    //junit : Logica en berekeningen met acces layout maar niet met activities werken
    //unit : Logica en berekingen --> gebeurt lokaal op de JVM (geen layout)
    int result;
    private CalorieCalc mCalActivity;
    private CalorieOwn mCalOwn;
    private CalorieCustom mCalCust;
    private RadioButton radioFemale, radioMale;

    @Rule
    public ActivityTestRule<CalorieCalc> mainCalTest = new ActivityTestRule<>(CalorieCalc.class);
    public ActivityTestRule<CalorieOwn> mainOwnCalc = new ActivityTestRule<>(CalorieOwn.class);
    public ActivityTestRule<CalorieCustom> mainCustomCalc = new ActivityTestRule<>(CalorieCustom.class);
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(CalorieCalc.class.getName(), null, false);
    @Before
    public void setup() throws Exception {
        // setup for calorie activity
        mCalActivity = mainCalTest.getActivity();
        mCalOwn = mainOwnCalc.getActivity();
        mCalCust = mainCustomCalc.getActivity();
    }
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = getInstrumentation().getTargetContext();
        assertEquals("be.ehb.haseb", appContext.getPackageName());
    }
    @Test
    public void testIfButtonExists() {
        View view = mCalActivity.findViewById(R.id.button_apply);
        assertNotNull(view);
    }
    /*
        @Test
        public void testIfAllWidgetsExists() {
            View viewFemale = mCalActivity.findViewById(R.id.radioFemale);
            View viewMale = mCalActivity.findViewById(R.id.radioMale);
            View viewApply = mCalActivity.findViewById(R.id.button_apply);
            assertNotNull(viewFemale);
            assertNotNull(viewMale);
            assertNotNull(viewApply);
        }

     */
    @Test
    public void shouldStartAnActivityWhenClicking() {
        assertNotNull(R.id.button_apply);
        onView(withId(R.id.button_apply)).perform(click());
        Activity calorie = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        //vermots hier geen foutencontrole is moet de waarde dat berekend zijn gelijk zijn aan 0
       assertNotNull(calorie);
    }

    @Test
    public void testIfRadioButtonExist() {
        assertNotNull(mCalActivity.findViewById(R.id.radio_one));
    }


    @After
    public void tearDown() throws Exception {
        // "stop the activity enfaite"
        mCalCust = null;
        mCalActivity = null;
        mCalOwn = null;
    }

}
