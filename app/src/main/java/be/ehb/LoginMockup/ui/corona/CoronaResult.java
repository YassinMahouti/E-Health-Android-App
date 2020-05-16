package be.ehb.LoginMockup.ui.corona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

import java.util.Collection;
import java.util.Collections;
import java.util.List;import be.ehb.Ehealth.R;


public class CoronaResult extends AppCompatActivity {
    //-- Img button to let the user created a new test(a new result)
    //--Able to later show the values, instantiate a RecyclerView
    private RecyclerView mRecyclerView;
    private ImageButton newTest;
    TextView filter_up;
    TextView filter_down;
    String filter;



    //----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona_result);

        mRecyclerView = (RecyclerView) findViewById(R.id.recylerview_userCorona);
        newTest = (ImageButton) findViewById(R.id.imgBtn_newTest);
        filter_up = (TextView) findViewById(R.id.txt_filter_newest);
        filter_down = (TextView) findViewById(R.id.txt_filter_oldest);
        loadDataIntoRecyclerViewer();
        filter_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDataIntoRecyclerViewer();
            }
        });
        filter_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CoronaResult.this, "From oldest to newest", Toast.LENGTH_SHORT).show();
                new FirebaseDatabaseHelper().readUserCorona(new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(final List<User> userCorona, List<String> keys) {
                        //--When data is loaded from cloud into the recyclerView -> show "waiting" with a progressbar -> setVisibility : GONE
                        findViewById(R.id.progressbarWaiting).setVisibility(ViewGroup.GONE);
                        //--Create the recycler view and pass the configuration

                        new RecyclerView_Config().setConfig(mRecyclerView,CoronaResult.this , userCorona, keys);
                    }

                    @Override
                    public void DataIsInserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });
        newTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoronaResult.this , CoronaMain.class);
                startActivity(intent);
                finish();
            }
        });
        //----------------------------------------------------------------------------------------------------------------------------




    }
    private void loadDataIntoRecyclerViewer(){
        Toast.makeText(CoronaResult.this, "From newest to oldest", Toast.LENGTH_SHORT).show();
        new FirebaseDatabaseHelper().readUserCorona(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(final List<User> userCorona, List<String> keys) {
                //--When data is loaded from cloud into the recyclerView -> show "waiting" with a progressbar -> setVisibility : GONE
                findViewById(R.id.progressbarWaiting).setVisibility(ViewGroup.GONE);
                //--Create the recycler view and pass the configuration
                Collections.sort(userCorona, User.myDate);
                new RecyclerView_Config().setConfig(mRecyclerView,CoronaResult.this , userCorona, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });

    }


}
