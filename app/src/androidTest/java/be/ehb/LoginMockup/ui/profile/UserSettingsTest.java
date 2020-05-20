package be.ehb.LoginMockup.ui.profile;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;
import android.widget.TextView;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import be.ehb.Ehealth.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
/**
 * This test is a test for the UserprofileSettings
 * In the these settings the user will see all his results, edit them and sent them back to the DB
 * The main functionalities in this features are:
 * The layout should be visible w/ all informations about a user
 * The editText should not be empty
 * The user should be able to edit a text
 * The user should be able to confirm the AlertDialog
 * When the confirm button is clicked, it should be able to edit the value of a local variable
 */
public class UserSettingsTest {
    //--Properties-------------------//
    private UserSettings mUsersetting;//--Propertie to test the view-----------//
    EditText editTextUsername;
    private AlertDialog dialog;
    UserProfile userProfile;


    @Rule
//--Calls on activity for this test---------------------------------------------------------------------//
    public ActivityTestRule<UserSettings> mainActivityTestRule = new ActivityTestRule<UserSettings>(UserSettings.class);

    @Before
    public void setUp() throws Exception {
        mUsersetting = mainActivityTestRule.getActivity();
        userProfile = new UserProfile();

    }

    @Test
    /**
     * Methode initialisationDialog tests if there is an actionlistener on a Textview
     * If the Textview gets clicked, then a AlertDialog should pop up
     */
    public void initializeDialog() {
        // User clicks on the his username(Textview)
        onView(withId(R.id.txt_username)).perform(click());
        // If the click is performed, then their should be a AlertDialog with an editText pop up
        assertTrue(mUsersetting.dialog.isShowing());
    }

    @Test
    public void EditTextShouldHaveText() {
        //--Gets the userName in a local String variable
        String str_editText = editTextUsername.getText().toString();
        //--Checks if the String is empty
        if (str_editText.isEmpty() || str_editText.equals("") || str_editText == null) {
            assertTrue(editTextUsername == null);
        } else {
            assertTrue(editTextUsername != null);
        }
    }
    @Test
    public void shouldChangeValue() {
        onView(withId(R.id.txt_username)).perform(typeText("Hallo"));
        //--Should change the value of an editText
        String str_result = "Hi";
        str_result= String.valueOf(editTextUsername.getText());
        assertTrue(str_result=="Hallo");
    }
    @Test
    public void shouldConfirmDialog() {
        //--When the AlertDialog is confirmed, it should confirm the users options
        if (mUsersetting.dialog.isShowing()) {
            try {
                dialog.getButton(DialogInterface.BUTTON_POSITIVE);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        assertNotNull(dialog);
    }
    @Test
    public void ConfirmButton_shouldChangeValue() {
        //--If the confirmButton gets clicked, the userValues should change
        String new_username = "New User";
        userProfile.setUsername(new_username);
        onView(withId(R.id.btn_confirm)).perform(click());
        assertTrue(userProfile.getUsername() == "New User");
        assertFalse(userProfile.getUsername() != "New User");
    }


    @After
    public void tearDown() throws Exception {
    }
}