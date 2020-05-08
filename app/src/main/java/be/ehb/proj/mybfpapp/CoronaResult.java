package be.ehb.proj.mybfpapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class CoronaResult extends AppCompatActivity {
private RecyclerView mRecyclerView;
private ImageButton newTest;
//private ImageButton btn_testDelete;


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
            @Override
            public void DataIsLoaded(final List<User> userCorona, List<String> keys) {
                //when data is loaded from cloud into the recyclerView -> show "waiting" with a progressbar -> setVisibility : GONE
                findViewById(R.id.progressbarWaiting).setVisibility(ViewGroup.GONE);
                new RecylerView_Config().setConfig(mRecyclerView, CoronaResult.this , userCorona, keys);
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
    /*
    private  int getIndex_SpinnerItem(Spinner spinner, String item){
        int index=0;
        for(int i=0;i<spinner.getCount();i++)
        {
            if(spinner.getItemAtPosition(i).equals(item))
            {
                index=i;
                //stop loop
                break;
            }
        }
        return index;

    }*/
}
