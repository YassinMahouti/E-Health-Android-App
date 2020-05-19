package be.ehb.LoginMockup.ui.bmiAndBfp.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import be.ehb.LoginMockup.ui.bmiAndBfp.model.User;
import be.ehb.LoginMockup.ui.bmiAndBfp.tools.FirebaseDatabaseHelper;
import be.ehb.Ehealth.R;

public class UserResults extends AppCompatActivity {
    private RecyclerView rv_resultsBB;
    private ImageButton imgBtn_newCalculation;
    LinearLayoutManager mLayoutManager;
    TextView filter_up;
    TextView filter_down;
    SharedPreferences mSharedPref;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bmi_results);
        rv_resultsBB =(RecyclerView) findViewById(R.id.recylerview_bmi);
        imgBtn_newCalculation = (ImageButton) findViewById(R.id.imgBtn_newTest);
         filter_up = (TextView) findViewById(R.id.txt_filter_newest);
         filter_down =(TextView) findViewById(R.id.txt_filter_oldest);

        imgBtn_newCalculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserResults.this , BmiBfpMain.class);
                startActivity(intent);
                finish();
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

        loadDataIntoRecyclerViewer();;
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
        filter_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new FirebaseDatabaseHelper().readUser(new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void DataIsLoaded(final List<User> userBMI, List<String> keys) {
                        //when data is loaded from cloud into the recyclerView -> show "waiting" with a progressbar -> setVisibility : GONE
                        findViewById(R.id.progressbarWaiting).setVisibility(ViewGroup.GONE);
                        new RecyclerView_Config().setConfig(rv_resultsBB, UserResults.this , userBMI, keys , mLayoutManager);

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
    }
    private void loadDataIntoRecyclerViewer(){
        new FirebaseDatabaseHelper().readUser(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(final List<User> userBMI, List<String> keys) {
                //when data is loaded from cloud into the recyclerView -> show "waiting" with a progressbar -> setVisibility : GONE
                findViewById(R.id.progressbarWaiting).setVisibility(ViewGroup.GONE);
                //Collections.sort(userBMI, User.myDate);
                new RecyclerView_Config().setConfig(rv_resultsBB, UserResults.this , userBMI, keys, mLayoutManager);

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
