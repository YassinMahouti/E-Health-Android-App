package com.example.foodchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class FoodDetails extends AppCompatActivity {
    private TextView mTextViewFoodRDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        mTextViewFoodRDetails = findViewById(R.id.textViewDetails);
    }
}
