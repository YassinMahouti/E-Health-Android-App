package be.ehb.LoginMockup.ui.corona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    LinearLayoutManager mLayoutManager;
    SharedPreferences mSharedPref;
    Button btn_showDoctorsandHospitals;



    //----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona_result);

        mRecyclerView = (RecyclerView) findViewById(R.id.recylerview_userCorona);
        newTest = (ImageButton) findViewById(R.id.imgBtn_newTest);
        filter_up = (TextView) findViewById(R.id.txt_filter_newest);
        filter_down = (TextView) findViewById(R.id.txt_filter_oldest);
        btn_showDoctorsandHospitals =(Button) findViewById(R.id.bt_find_doctor);
        loadDataIntoRecyclerViewer();
        filter_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDataIntoRecyclerViewer();
                SharedPreferences.Editor editor = mSharedPref.edit();
                editor.putString("Sort", "newest");
                editor.apply();
                recreate();
            }
        });

        mSharedPref = getSharedPreferences("SortSettigns",MODE_PRIVATE);
        String mSorting = mSharedPref.getString("Sort","newest"); //def value: sort: newest
        if(mSorting.equals("newest")){
            mLayoutManager = new LinearLayoutManager(this);
            mLayoutManager.setReverseLayout(true);
            mLayoutManager.setStackFromEnd(true);

        }
        else if( mSorting.equals("oldest"))
        {
            mLayoutManager = new LinearLayoutManager(this);
            mLayoutManager.setReverseLayout(false);
            mLayoutManager.setStackFromEnd(false);
        }

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
                        new RecyclerView_Config().setConfig(mRecyclerView,CoronaResult.this , userCorona, keys, mLayoutManager);
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
                SharedPreferences.Editor editor = mSharedPref.edit();
                editor.putString("Sort", "oldest");
                editor.apply();
                recreate();
            }
        });
        btn_showDoctorsandHospitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoronaResult.this, FindAHospital.class);
                startActivity(intent);
                finish();
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

                new RecyclerView_Config().setConfig(mRecyclerView,CoronaResult.this , userCorona, keys , mLayoutManager);
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
