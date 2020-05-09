package be.ehb.LoginMockup.ui.bmi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import be.ehb.Ehealth.R;

public class bmiFragment extends Fragment {

    private bmiViewModel bmiViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        bmiViewModel =
                ViewModelProviders.of(this).get(bmiViewModel.class);
        View root = inflater.inflate(R.layout.bmi, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        bmiViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
