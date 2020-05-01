package be.ehb.LoginMockup.ui.whereToWorkout;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FindPlaceViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FindPlaceViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Find Place fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}