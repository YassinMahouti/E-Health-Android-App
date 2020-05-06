package be.ehb.LoginMockup.ui.drinkwater;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.ehb.Ehealth.R;

public class DrinkWater extends Fragment {

    private DrinkWaterViewModel mViewModel;

    public static DrinkWater newInstance() {
        return new DrinkWater();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.drink_water_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DrinkWaterViewModel.class);
        // TODO: Use the ViewModel
    }

}
