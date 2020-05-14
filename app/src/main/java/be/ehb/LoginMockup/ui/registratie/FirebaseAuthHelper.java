package be.ehb.LoginMockup.ui.registratie;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FirebaseAuthHelper {
    private FirebaseAuth firebaseAuth;
//singlton

    public FirebaseAuthHelper() {

        firebaseAuth = FirebaseAuth.getInstance();

    }


}
