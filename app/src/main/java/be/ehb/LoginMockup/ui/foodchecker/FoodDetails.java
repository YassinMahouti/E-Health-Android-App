package be.ehb.LoginMockup.ui.foodchecker;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import be.ehb.Ehealth.R;

public class FoodDetails extends AppCompatActivity {
    private TextView mTextViewFoodRDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        mTextViewFoodRDetails = findViewById(R.id.textViewDetails);
    }
}
