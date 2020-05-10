package be.ehb.proj.mybfpapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.List;

/**
 * Show data from Realtime db: load data. Let user make a new test when he click on "+" Button.
 */
public class CoronaResult extends AppCompatActivity {
    //-- Img button the let the user created a new test(new result)
    //--Able to later show the values, instantiate create a RecyclerView
    private RecyclerView mRecyclerView;
    private ImageButton newTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona_result);
       // btn_testDelete = (ImageButton) findViewById(R.id.imageButton);
        mRecyclerView = (RecyclerView) findViewById(R.id.recylerview_userCorona);
        newTest = (ImageButton) findViewById(R.id.imgBtn_newTest);
        newTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoronaResult.this , MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        new FirebaseDatabaseHelper().readUserCorona(new FirebaseDatabaseHelper.DataStatus() {
            //--Generate override methode From DataStatus: need data is loaded
            @Override
            public void DataIsLoaded(final List<User> userCorona, List<String> keys) {
                //--When data is loaded from cloud into the recyclerView the user need to wait
                //--To show the "waiting or data loading process" I use a CircleProgressbar.
                //--setVisibility to Gone, before it was on invisible, now it will "spin".
                findViewById(R.id.progressbarWaiting).setVisibility(ViewGroup.GONE);
                //--Create the recycler view. New each time its called, with the configuration setup(set in "setConfig")
                //--Pass the created recyclerView, a Context, List with user info and the keys stored in a List too
                new RecyclerView_Config().setConfig(mRecyclerView, CoronaResult.this , userCorona, keys);
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
