package be.ehb.LoginMockup.ui.movementMonitor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import be.ehb.Ehealth.R;

public class MovementMonitor extends Fragment {
    private MovementMonitorViewModel mViewModel;

    public static MovementMonitor newInstance() {
        return new MovementMonitor();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movement_monitor_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MovementMonitorViewModel.class);
        // TODO: Use the ViewModel
    }
}
