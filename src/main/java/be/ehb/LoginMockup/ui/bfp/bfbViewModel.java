package be.ehb.LoginMockup.ui.bfp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class bfbViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public bfbViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}