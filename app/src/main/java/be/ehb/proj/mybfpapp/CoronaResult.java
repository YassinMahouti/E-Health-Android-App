package be.ehb.proj.mybfpapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ViewGroup;

import java.util.List;

public class CoronaResult extends AppCompatActivity {
private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona_result);
        mRecyclerView = (RecyclerView) findViewById(R.id.recylerview_userCorona);
        new FirebaseDatabaseHelper().readUserCorona(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<User> userCorona, List<String> keys) {
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
}
