package be.ehb.LoginMockup.ui.registratie;

import com.google.firebase.auth.FirebaseAuth;

public class FirebaseAuthHelper {
    FirebaseAuth gbTest;


    public FirebaseAuthHelper() {
        gbTest= FirebaseAuth.getInstance();
    }


}


