package be.ehb.LoginMockup.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.security.PrivateKey;

import be.ehb.Ehealth.R;
import be.ehb.LoginMockup.ui.Calorie.CalorieCalc;
import be.ehb.LoginMockup.ui.bmiAndBfp.view.BmiBfpMain;
import be.ehb.LoginMockup.ui.corona.CoronaMain;
import be.ehb.LoginMockup.ui.foodlookup.Foodlookup;
import be.ehb.LoginMockup.ui.placefinder.MapsActivity;
import be.ehb.LoginMockup.ui.profile.UserSettings;
import be.ehb.LoginMockup.ui.steps.StepCounter;
import be.ehb.LoginMockup.ui.whereToWorkout.WhereToWorkout;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private CardView user;
    private CardView steps;
    private CardView direction;
    private CardView workout;
    private CardView sick;
    private CardView calorie;
    private CardView bmi;
    private CardView food;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        user = root.findViewById(R.id.nav_user_settings);
        steps = root.findViewById(R.id.nav_steps);
        direction = root.findViewById(R.id.nav_find_place);
        workout = root.findViewById(R.id.nav_workout);
        sick = root.findViewById(R.id.nav_corona);
        calorie = root.findViewById(R.id.calc);
        bmi = root.findViewById(R.id.nav_bmi_bfp);
        food = root.findViewById(R.id.nav_foodlookup);

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Foodlookup.class);
                startActivity(intent);

            }
        });


        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BmiBfpMain.class);
                startActivity(intent);

            }
        });


        calorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalorieCalc.class);
                startActivity(intent);

            }
        });


        sick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CoronaMain.class);
                startActivity(intent);

            }
        });


        workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WhereToWorkout.class);
                startActivity(intent);

            }
        });


        direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);

            }
        });


        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserSettings.class);
                startActivity(intent);

            }
        });

        steps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StepCounter.class);
                startActivity(intent);
            }
        });

        return root;
    }
}
