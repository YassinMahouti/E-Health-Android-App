package be.ehb.proj.basicbfpapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import be.ehb.proj.basicbfpapplication.R;
import be.ehb.proj.basicbfpapplication.model.User;
import be.ehb.proj.basicbfpapplication.tools.FirebaseDatabaseHelper;

public class UserResults extends AppCompatActivity {
    private RecyclerView rv_resultsBB;
    private ImageButton imgBtn_newCalculation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bmi_results);
        rv_resultsBB =(RecyclerView) findViewById(R.id.recyclerView);
        imgBtn_newCalculation = (ImageButton) findViewById(R.id.imageButtonNewCalculation);
        imgBtn_newCalculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserResults.this , MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        new FirebaseDatabaseHelper().readUser(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(final List<User> userBMI, List<String> keys) {
                //when data is loaded from cloud into the recyclerView -> show "waiting" with a progressbar -> setVisibility : GONE
                findViewById(R.id.progressBarWaitingResults).setVisibility(ViewGroup.GONE);
                new RecylerView_Config().setConfig(rv_resultsBB, UserResults.this , userBMI, keys);
                Toast.makeText(UserResults.this, "Data is loaded"+ userBMI, Toast.LENGTH_SHORT).show();
                System.out.println(userBMI);
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
