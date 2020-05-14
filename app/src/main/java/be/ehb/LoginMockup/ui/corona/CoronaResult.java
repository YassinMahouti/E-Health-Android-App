package be.ehb.LoginMockup.ui.corona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;import be.ehb.Ehealth.R;


public class CoronaResult extends AppCompatActivity {
    //-- Img button to let the user created a new test(a new result)
    //--Able to later show the values, instantiate a RecyclerView
    private RecyclerView mRecyclerView;
    private ImageButton newTest;
    //----------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona_result);
        mRecyclerView = (RecyclerView) findViewById(R.id.recylerview_userCorona);
        newTest = (ImageButton) findViewById(R.id.imgBtn_newTest);
        newTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoronaResult.this , CoronaMain.class);
                startActivity(intent);
                finish();
            }
        });
        //----------------------------------------------------------------------------------------------------------------------------
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

}
