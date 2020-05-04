package be.ehb.LoginMockup.ui.bmi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class bmiViewModel extends ViewModel {

    private MutableLiveData<String> mText;




    public bmiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is bmi fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}