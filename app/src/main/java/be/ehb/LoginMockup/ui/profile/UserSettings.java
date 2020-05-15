package be.ehb.LoginMockup.ui.profile;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import be.ehb.Ehealth.R;
import be.ehb.LoginMockup.LoginAct;
import be.ehb.LoginMockup.ui.registratie.RegAct;

public class UserSettings extends AppCompatActivity {
    //---Creation of local variables---//
    TextView tv_Username;
    TextView tv_Ageinput;
    TextView tv_Weightinput;
    TextView tv_Heightinput;
    TextView tv_phoneinput;
    TextView tv_Emailinput;

    AlertDialog dialog;
    AlertDialog dialog3;
    AlertDialog dialog4;
    AlertDialog dialog5;
    AlertDialog dialog6;
    AlertDialog dialog7;

    EditText editTextUsername;
    EditText editTextAge;
    EditText editTextWeight;
    EditText editTextHeight;
    EditText editTextPhone;
    EditText editTextMail;

    RadioGroup radioGroup;
    RadioButton rBtn;
    Button btn_confirm;
    Button btn_signOut;

    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    private UserProfile user;

    //---Function that initializes buttons to variables---//
    public void initialize() {
        tv_Username = findViewById(R.id.txt_username);
        tv_Ageinput = findViewById(R.id.txt_ageinput);
        tv_Weightinput = findViewById(R.id.txt_weightinput);
        tv_Heightinput = findViewById(R.id.txt_heightinput);
        tv_phoneinput = findViewById(R.id.txt_phoneinput);
        tv_Emailinput = findViewById(R.id.txt_mailinput);

        radioGroup = (RadioGroup) findViewById(R.id.Gender);
        rBtn = (RadioButton) findViewById(R.id.Male);
        rBtn = (RadioButton) findViewById(R.id.Female);


        btn_confirm = (Button) findViewById(R.id.btn_confirm);
        btn_signOut = (Button) findViewById(R.id.btn_signOut);



    }

    public void dialog_initialization() {
        dialog = new AlertDialog.Builder(this).create();
        editTextUsername = new EditText(this);
        dialog.setTitle("Insert your new value");
        dialog.setView(editTextUsername);


        dialog3 = new AlertDialog.Builder(this).create();
        editTextAge = new EditText(this);
        dialog3.setTitle("Insert your new value");
        editTextAge.setInputType(InputType.TYPE_CLASS_NUMBER);
        editTextAge.setRawInputType(Configuration.KEYBOARD_12KEY);
        dialog3.setView(editTextAge);

        dialog4 = new AlertDialog.Builder(this).create();
        editTextWeight = new EditText(this);
        dialog4.setTitle("Insert your new value");
        editTextWeight.setInputType(InputType.TYPE_CLASS_NUMBER);
        editTextWeight.setRawInputType(Configuration.KEYBOARD_12KEY);
        dialog4.setView(editTextWeight);

        dialog5 = new AlertDialog.Builder(this).create();
        editTextHeight = new EditText(this);
        dialog5.setTitle("Insert your new value");
        editTextHeight.setInputType(InputType.TYPE_CLASS_NUMBER);
        editTextHeight.setRawInputType(Configuration.KEYBOARD_12KEY);
        dialog5.setView(editTextHeight);

        dialog6 = new AlertDialog.Builder(this).create();
        editTextPhone = new EditText(this);
        dialog6.setTitle("Insert your new value");
        editTextPhone.setInputType(InputType.TYPE_CLASS_PHONE);
        editTextPhone.setRawInputType(Configuration.KEYBOARD_12KEY);
        dialog6.setView(editTextPhone);

        dialog7 = new AlertDialog.Builder(this).create();
        editTextMail = new EditText(this);
        dialog7.setTitle("Insert your new value");
        dialog7.setView(editTextMail);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        initialize();       //--!initializes buttons to the on create--//


        //onStart();
        String uid;

        final FirebaseDatabase db_Root = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        uid="GRHcHyil5WRnb6H8032fBJAeiA73";

        databaseReference = db_Root.getReference("Users");
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
           databaseReference.child(uid);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String username = dataSnapshot.child(uid).child("name").getValue(String.class);
                tv_Username.setText(username);

                String gender = dataSnapshot.child(uid).child("gender").getValue(String.class);
                if (gender == "male") {
                    rBtn = (RadioButton) findViewById(R.id.Male);
                } else {
                    rBtn = (RadioButton) findViewById(R.id.Female);
                }
                String age = dataSnapshot.child(uid).child("age").getValue(String.class);
                tv_Ageinput.setText(age);

                String weight = dataSnapshot.child(uid).child("weight").getValue(String.class);
                tv_Weightinput.setText(weight);
                String height = dataSnapshot.child(uid).child("height").getValue(String.class);
                tv_Heightinput.setText(height);


                String phone = dataSnapshot.child(uid).child("phone").getValue(String.class);
                tv_phoneinput.setText(phone);

                String mail = dataSnapshot.child(uid).child("email").getValue(String.class);
                tv_Emailinput.setText(mail);

                user = new UserProfile(username, mail, age, phone, Float.parseFloat(String.valueOf(weight)), Float.parseFloat(String.valueOf(height)), gender);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        ///---------------------------All AlertDialogs for every inputtype---------------------------///

        dialog_initialization();

//--Dialog for Usernameinput--//
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Save changes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tv_Username.setText(editTextUsername.getText());
            }
        });

        tv_Username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // editTextEmail.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS| InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                editTextUsername.setText(tv_Username.getText());
                dialog.show();
            }
        });

//---Dialog for AgeInput---//
        dialog3.setButton(DialogInterface.BUTTON_POSITIVE, "Save changes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int age = Integer.parseInt(editTextAge.getText().toString());
                if (age >= 5 && age <= 100) {
                    tv_Ageinput.setText(editTextAge.getText());
                } else {
                    Toast.makeText(UserSettings.this, "Invalid input", Toast.LENGTH_SHORT).show();
                    dialog3.show();
                }
            }
        });

        tv_Ageinput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextAge.setText(tv_Ageinput.getText());
                dialog3.show();
            }
        });

//---Dialog for WeightInput---//
        dialog4.setButton(DialogInterface.BUTTON_POSITIVE, "Save changes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tv_Weightinput.setText(editTextWeight.getText());
            }
        });

        tv_Weightinput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextWeight.setText(tv_Ageinput.getText());
                dialog4.show();
            }
        });

//---Dialog for HeightInput---//
        dialog5.setButton(DialogInterface.BUTTON_POSITIVE, "Save changes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tv_Heightinput.setText(editTextHeight.getText());
            }
        });

        tv_Heightinput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextHeight.setText(tv_Heightinput.getText());
                dialog5.show();
            }
        });

//---Dialog for Phoneinput---//
        dialog6.setButton(DialogInterface.BUTTON_POSITIVE, "Save changes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tv_phoneinput.setText(editTextPhone.getText());
            }
        });

        tv_phoneinput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextPhone.setText(tv_phoneinput.getText());
                dialog6.show();
            }
        });

//---Dialog for Emailinput---//
        dialog7.setButton(DialogInterface.BUTTON_POSITIVE, "Save changes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tv_Emailinput.setText(editTextMail.getText());
            }
        });

        tv_Emailinput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextMail.setText(tv_Emailinput.getText());
                dialog7.show();
            }
        });


        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_name = String.valueOf(tv_Username.getText());
                user.setUsername(str_name);
                databaseReference.child(uid).child("name").setValue(str_name);
                String age = (String) tv_Ageinput.getText();
                user.setAge(age);
                databaseReference.child(uid).child("age").setValue(age);
                String gender = rBtn.getText().toString();
                user.setGender(gender);
                databaseReference.child(uid).child("gender").setValue(gender);
                String weight = String.valueOf(tv_Weightinput.getText());
                user.setUser_weight(Float.parseFloat(String.valueOf(weight)));
                databaseReference.child(uid).child("weight").setValue(weight);
                String height = String.valueOf(tv_Heightinput.getText());
                user.setUser_height(Float.parseFloat(String.valueOf(height)));
                databaseReference.child(uid).child("height").setValue(height);
                String email = String.valueOf(tv_Emailinput.getText());
                user.setUser_mail(email);
                databaseReference.child(uid).child("email").setValue(email);
                String phone = String.valueOf(tv_phoneinput.getText());
                user.setPhone(phone);
                databaseReference.child(uid).child("phone").setValue(phone);

            }

        });

       /* btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                if (v.getId() == R.id.btn_signOut){
                mAuth.getInstance()
                        .signOut()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                // user is now signed out
                                startActivity(new Intent(UserSettings.this, RegAct.class));
                                finish();
                            }
                        });

            }
            }
        });*/
    }


    public void onRadioButtonClicked(View v) {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        rBtn = (RadioButton) findViewById(selectedId);
    }

    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginAct.class));
        }
    }

}
