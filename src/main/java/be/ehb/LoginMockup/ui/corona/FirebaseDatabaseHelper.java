package be.ehb.LoginMockup.ui.corona;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRootUserCorona;
    private DatabaseReference mReferenceUserCoronaResult;
    private DatabaseReference mReferenceUserCoronaResult1;
    private List<User> userCorona = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<User> userCorona, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }
    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        //mRootUserCorona = mDatabase.getReference("UserCorona");
        mReferenceUserCoronaResult = mDatabase.getReference("UserCorona");
        mReferenceUserCoronaResult1 = mReferenceUserCoronaResult.child("userResult");


    }
    public void readUserCorona(final DataStatus dataStatus){
        mReferenceUserCoronaResult1.addValueEventListener(new ValueEventListener() {
            @Override
            //asyncrous methode !! -> need interface**
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // if value change

                userCorona.clear();
                //list for the keys
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    //datasnapshot has key and value of usercorona node
                    keys.add(keyNode.getKey());
                    User user = keyNode.getValue(User.class);
                    userCorona.add(user);
                }
                //loaded
                dataStatus.DataIsLoaded(userCorona,keys);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void deleteItem(String key, final DataStatus dataStatus){
        //doc: if value is null -> it will be deleted automatically
        mReferenceUserCoronaResult1.child(key).setValue(null)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        dataStatus.DataIsDeleted();
                    }
                });
    }


}
