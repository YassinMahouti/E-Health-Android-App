package be.ehb.LoginMockup.ui.whereToWorkout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import be.ehb.Ehealth.R;

public class FindPlaceFragment extends Fragment {
    private FindPlaceViewModel findPlaceViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        findPlaceViewModel =
                ViewModelProviders.of(this).get(FindPlaceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_find_place, container, false);
        final TextView textView = root.findViewById(R.id.text_find_place);
        findPlaceViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
