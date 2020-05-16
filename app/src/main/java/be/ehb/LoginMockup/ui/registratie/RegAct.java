

package be.ehb.LoginMockup.ui.registratie;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import be.ehb.Ehealth.R;
import be.ehb.LoginMockup.LoginAct;
import be.ehb.LoginMockup.MainActivity;
import be.ehb.LoginMockup.NavDrawerAct;
public class RegAct extends AppCompatActivity {
    FirebaseUser user;
    private static final String TAG ="Main" ;
    EditText myName, myEmail, myPassword, myPhone, myWeight, myHeight, myAge;
    Button myRegisterBtn;
    TextView myLoginBtn;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference databaseReference;
    DatabaseReference mRefValues;
    ProgressBar progressBar;
    ImageView backButton;
    RadioButton radioButtonFemale, radioButtonMale;
    String gender = "";
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null)
        {
            firebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        firebaseAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if(user != null)
                {
                    Log.d(TAG,"onAuthStateChanged:singed_in:" +user.getUid());
                }
                else {
                    Log.d(TAG,"onAuthStateChanged:singed_out:");
                }
            }
        };
//Hier komt de code voor de registratie
        myName = findViewById(R.id.name);
        myEmail = findViewById(R.id.email);
        myPassword = findViewById(R.id.password);
        myPhone = findViewById(R.id.phone);
        myRegisterBtn = findViewById(R.id.regBtn);
        myLoginBtn = findViewById(R.id.createText);
        myWeight = findViewById(R.id.weight);
        myHeight = findViewById(R.id.height);
        myAge = findViewById(R.id.age);
        radioButtonFemale = findViewById(R.id.radio_female);
        radioButtonMale = findViewById(R.id.radio_male);
        progressBar = findViewById(R.id.progressBar);
        final FirebaseDatabase mRoot = FirebaseDatabase.getInstance();
/*if (firebaseAuth.getCurrentUser() == null) {
            startActivity(new Intent(getApplicationContext(), RegAct.class));
        }*/
        myRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = myEmail.getText().toString().trim();
                String password = myPassword.getText().toString().trim();
                final String name = myName.getText().toString().trim();
                final String phone = myPhone.getText().toString().trim();
                final String weight = myWeight.getText().toString().trim();
                final String height = myHeight.getText().toString().trim();
                final String age = myAge.getText().toString().trim();
                if (radioButtonFemale.isChecked()) {
                    gender = "Female";
                }
                if (radioButtonMale.isChecked()) {
                    gender = "Male";
                }
                if (TextUtils.isEmpty(email)) {
                    myEmail.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    myPassword.setError("Password is required");
                    return;
                }
                if (password.length() < 6) {
                    myPassword.setError("Password must be greater than 6 characters");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(
                                    name,
                                    email,
                                    phone,
                                    weight,
                                    height,
                                    age,
                                    gender
                            );
                            databaseReference = mRoot.getReference("Users");
// mRefValues = mRoot.getReference("user");
                            mRefValues = databaseReference.child(firebaseAuth.getCurrentUser().getUid());
                            mRefValues.setValue(user);
                            System.out.println(user.getEmail() + user.getName() + user.getPhone());
/* FirebaseDatabase.getInstance().getReference("Members")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(member);*/
                            Toast.makeText(RegAct.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),LoginAct.class));
                        } else {
                            Toast.makeText(RegAct.this, "Something went wrong :/" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
        backButton = findViewById(R.id.backBtn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        myLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginAct.class));
            }
        });
    }

}



