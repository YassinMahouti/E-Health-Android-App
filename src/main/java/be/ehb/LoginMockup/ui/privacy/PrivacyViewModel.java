package be.ehb.LoginMockup.ui.privacy;

import android.widget.Button;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PrivacyViewModel<mFireBaseBtn> extends ViewModel {

    private MutableLiveData<String> mText;


    public PrivacyViewModel() {
        mText = new MutableLiveData<>();
        //Hier komt de code voor de privacy pagina.
        mText.setValue("Dit is de privacy pagina ");
    }



    public LiveData<String> getText() {
        return mText;
    }


}