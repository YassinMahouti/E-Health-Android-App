package be.ehb.LoginMockup.movementMonitor;

import android.content.Context;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import be.ehb.LoginMockup.ui.movementMonitor.Activity;
import be.ehb.LoginMockup.ui.movementMonitor.ListActivity;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;

public class ListActivityTest {
    Activity activity;
    ListActivity listActivity;
    ArrayList<Activity> activitie;
    RecyclerView recyclerView;
    TextView textView;
    String testing;

    @Rule
    public ActivityTestRule<ListActivity> listActivityTestRule = new ActivityTestRule<>(ListActivity.class);

    @Before
    public void setListActivity() throws Exception{
        /*listActivity = listActivityTestRule.getActivity();
        activitie = new ArrayList<>();
        activity = new Activity("test", "endurance", 1000, 10, "hard", "url");

        recyclerView = listActivity.findViewById(R.id.recycler_view);
        //textView = listActivity.findViewById(R.id.textView_list_activity_variable_name_activity);
        //testing = String.valueOf(textView.getText());*/
    }

    @Test
    public void userAppContext(){
        Context appContext = getInstrumentation().getTargetContext();
        assertEquals("be.ehb.haseb",appContext.getPackageName());
    }

    /*@Test
    public void perform_Click_Button_To_StartActivity(){
        ArrayList<Activity> activities = new ArrayList<>();
        Activity activity = new Activity("test", "endurance", 1000, 10, "hard", "url");
        activities.add(activity);

        RecyclerView recyclerView = listActivity.findViewById(R.id.recycler_view);
        assertNotNull(recyclerView);

        ListAdapter listAdapter = new ListAdapter(activities);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(listAdapter);

        assertNotNull(listActivity.findViewById(R.id.button_start_activity_start_now));
        onView(withId(R.id.button_main_activity_choose_an_activity)).perform(click());
    }*/

   /* @Test
    public void recycleViewTest(){
        activitie.add(activity);
        listActivity.buildRecycleView(activitie);

        assertTrue(testing == "test");
    }*/

    @After
    public void tearDown() throws Exception {
        listActivity =null;
    }
}