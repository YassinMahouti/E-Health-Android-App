package be.ehb.proj.mybfpapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listSymptoms;
    private ArrayList<Integer> arraySymptoms;
    private Button btn_saveSymptoms;
    private Button btn_calculateRisk;
    private RadioGroup rd_groupFirst;
    private RadioButton rd_answerFirst_yes;
    private RadioButton rd_answerFirst_no;
    private RadioGroup rd_groupSecond;
    private RadioButton rd_answerSec_yes;
    private RadioButton rd_answerSec_no;
    private RadioGroup rd_groupThird;
    private RadioButton rd_answerThird_yes;
    private RadioButton rd_answerThird_no;
    private RadioGroup rd_groupFo;
    private RadioButton rd_answerFo_yes;
    private RadioButton rd_answerFo_no;
    private RadioGroup rd_groupFi;
    private RadioButton rd_answerFi_yes;
    private RadioButton rd_answerFi_no;
    DatabaseReference mRoot;
   // DatabaseReference myRef;
    DatabaseReference addSymp;
    DatabaseReference disease;
    DatabaseReference diseaseSymptom;
    private float userRisk =0;
    DatabaseReference t_userRisk;
    DatabaseReference addUserRisk;

    public void init()
    {

        listSymptoms =(ListView) findViewById(R.id.listSymptoms);
        btn_saveSymptoms =(Button) findViewById(R.id.btn_saveSymptoms);
        btn_calculateRisk =(Button) findViewById(R.id.btn_calculateRisk);
        rd_groupFirst = (RadioGroup) findViewById(R.id.rd_group);
        rd_answerFirst_yes = (RadioButton) findViewById(R.id.rd_First_yes);
        rd_answerFirst_no = (RadioButton) findViewById(R.id.rd_first_no);
        rd_groupSecond =(RadioGroup) findViewById(R.id.rd_group2);
        rd_answerSec_yes = (RadioButton) findViewById(R.id.rd_Sec_yes);
        rd_answerSec_no = (RadioButton) findViewById(R.id.rd_Sec_no);
        rd_groupThird =(RadioGroup) findViewById(R.id.rd_group3);
        rd_answerThird_yes = (RadioButton) findViewById(R.id.rd_Third_yes);
        rd_answerThird_no = (RadioButton) findViewById(R.id.rd_Third_no);
        rd_groupFo =(RadioGroup) findViewById(R.id.rd_group4);
        rd_answerFo_yes = (RadioButton) findViewById(R.id.rd_Fo_yes);
        rd_answerFo_no = (RadioButton) findViewById(R.id.rd_Fo_no);
        rd_groupFi =(RadioGroup) findViewById(R.id.rd_group5);
        rd_answerFi_yes = (RadioButton) findViewById(R.id.rd_Fif_yes);
        rd_answerFi_no = (RadioButton) findViewById(R.id.rd_Fif_no);
    }
    public void createTableDiseases(FirebaseDatabase db)
    {
        disease = db.getReference("diseases");
        DatabaseReference disease_3 ;
        disease_3 = disease.child("Corona");
        disease_3 = disease.child("Symptoms");
        disease_3 = disease.child("koorts");
        disease_3.setValue("1");
        disease_3 = disease.child("diseaseTwo");
        disease_3.setValue("Griep");
        disease_3 = disease.child("diseaseTree");
        disease_3.setValue("Verkoudheid");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //To write a message to db
        // instantiate db -> getInstance()
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        mRoot = db.getReference("answerSymptom");
        // table diseases with 3 diseases: Corona , Griep , Verkoudheid
        createTableDiseases(db);
        t_userRisk = db.getReference("userRisk");

        init();
        arraySymptoms  = new ArrayList<>();

        btn_saveSymptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userRisk =0;
                rd_answerFirst_no.isChecked();
                addSymp =  mRoot.child("koorts");
                if( rd_answerFirst_yes.isChecked())
                {
                    addSymp.setValue("1");
                    userRisk += 32.0685f;
                }
                else
                addSymp.setValue("0");

                rd_answerSec_no.isChecked();
                addSymp =  mRoot.child("symptom2");
                if(  rd_answerSec_yes.isChecked())
                {
                    addSymp.setValue("1");
                    userRisk += 24.6990f;
                }
                else
                    addSymp.setValue("0");

                rd_answerThird_no.isChecked();
                addSymp =  mRoot.child("symptom3");
                if(  rd_answerThird_yes.isChecked())
                {
                    addSymp.setValue("1");
                    userRisk += 13.9000f;
                }
                else
                    addSymp.setValue("0");

                rd_answerFo_no.isChecked();
                addSymp =  mRoot.child("symptom4");
                if(  rd_answerFo_yes.isChecked()) {
                    addSymp.setValue("1");
                    userRisk += 12.1853f;
                }
                else
                    addSymp.setValue("0");


                rd_answerFi_no.isChecked();
                addSymp =  mRoot.child("symptom5");
                if(  rd_answerFi_yes.isChecked()) {
                    addSymp.setValue("1");
                    userRisk += 6.7858f;
                }
                else
                    addSymp.setValue("0");

                addUserRisk = t_userRisk.child("userRisk1");
                addUserRisk.push().setValue(String.valueOf(userRisk));

            }
        });
        final ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, arraySymptoms);
        listSymptoms.setAdapter(arrayAdapter);
        btn_calculateRisk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mRoot.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        //local instance of value (integer) we wanna load in
                        String symptom = dataSnapshot.getValue(String.class);
                        int symptom_int = Integer.parseInt(symptom);
                        arraySymptoms.add(symptom_int);
                        // array adapter to view in the listview;
                        arrayAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
