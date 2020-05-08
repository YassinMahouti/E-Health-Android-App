package be.ehb.proj.basicbfpapplication.tools;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
;
import be.ehb.proj.basicbfpapplication.model.User;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceUserBMI;
    private DatabaseReference mReferenceUserBMIResult;
    private List<User> userBMI = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<User> userBMI, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }
    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();

        mReferenceUserBMI = mDatabase.getReference("UserBB");
        mReferenceUserBMIResult = mReferenceUserBMI.child("resultsBB");


    }
    public void readUser(final DataStatus dataStatus){
        mReferenceUserBMIResult.addValueEventListener(new ValueEventListener() {
            @Override
            //asyncrous methode !! -> need interface**
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // if value change

                userBMI.clear();
                //list for the keys
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    //datasnapshot has key and value of user node
                    keys.add(keyNode.getKey());
                    User user = keyNode.getValue(User.class);
                    userBMI.add(user);
                }
                //loaded
                dataStatus.DataIsLoaded(userBMI,keys);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void deleteItem(String key, final DataStatus dataStatus){
        //doc: if value is null -> it will be deleted automatically
        mReferenceUserBMIResult.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDeleted();
                    }
                });
    }


}
