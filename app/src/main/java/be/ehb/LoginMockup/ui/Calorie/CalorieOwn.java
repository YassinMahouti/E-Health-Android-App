package be.ehb.LoginMockup.ui.Calorie;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import be.ehb.Ehealth.R;

public class CalorieOwn extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    CalorieCustom calorieCustom;
    private DatabaseReference mRootKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_own);

        FirebaseDatabase db_Root = FirebaseDatabase.getInstance();
        mDatabase = db_Root.getReference("Users");
        mAuth = FirebaseAuth.getInstance();
        mRootKey = mDatabase.child(mAuth.getCurrentUser().getUid());


        mRootKey.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //Get data from the user that is currently loggin in.
                Toast.makeText(CalorieOwn.this, "fdsfdsqfsdq", Toast.LENGTH_SHORT).show();
                int weight = Integer.parseInt(dataSnapshot.child("weight").getValue(String.class));
                int height = Integer.parseInt(dataSnapshot.child("height").getValue(String.class));
                int age = Integer.parseInt(dataSnapshot.child("age").getValue(String.class));
                String gender = String.valueOf((dataSnapshot.child("gender").getValue(String.class)));


                int result = bereken(gender, weight, height, age);

                Intent intent = new Intent(CalorieOwn.this, CalResult.class);
                intent.putExtra("Result", result);
                startActivity(intent);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(CalorieOwn.this, "Er is iets fout gegaan ", Toast.LENGTH_SHORT).show();

            }
        });

    }


    public int bereken(String mOrFResult, int weight_int, int height_int, int age) {

        float uitkomst;

        if (mOrFResult.equals("Male")) {
            uitkomst = (float) ((10 * weight_int) + (6.25 * height_int) - (5 * age) + 5);
        } else {
            uitkomst = (float) ((10 * weight_int) + (6.25 * height_int) - (5 * age) - 161);
        }


        return (int) uitkomst;
    }
}









               /* String username = dataSnapshot.child(uid).child("name").getValue(String.class);
                tv_Username.setText(username);


                String age = dataSnapshot.child(uid).child("age").getValue(String.class);
                tv_Ageinput.setText(age);






        });
    }
}

*/