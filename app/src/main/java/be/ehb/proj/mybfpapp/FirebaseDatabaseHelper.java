package be.ehb.proj.mybfpapp;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Able to have a connection with the Realtime database from Firebase I created a class FirebaseHelper.
 * Inside I used an interface able to have the status of the data-transfer.
 * Data is Loaded will pass 2 Lists, one for the user info and one for the corresponding keys
 */
public class FirebaseDatabaseHelper {
    /**
     * Instantiate the FirebaseDatabse(once), the de References and an array list that store the user informations.
     */
    private FirebaseDatabase mDatabase;
    //-----DatabaseReference-----------------------------
    private DatabaseReference mRootUserCorona;
    private DatabaseReference mReferenceUserCoronaResult1;
    private List<User> userCorona = new ArrayList<>();
    /**
     *  Cause data on change method is a asynchronous method, need an interface.
     *  Interface DataStatus: For load, insert,update and delete.
     */

    public interface DataStatus{
        void DataIsLoaded(List<User> userCorona, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    /**
     * Constructor of the helper class I created, get the connection with the realtime database and associate the references(with "table names": structure of our database)
     */
    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mRootUserCorona = mDatabase.getReference("UserCorona");
        mReferenceUserCoronaResult1 = mRootUserCorona.child("userResult");
    }

    /**
     * Able to read the infromation stored in the database I created a method that use the created interface DataStatus.
     * @param dataStatus : pass the interface created before
     */
    public void readUserCorona(final DataStatus dataStatus){
        /**
         * I place a value event listener on "the table" userResult, each time it changes it will be updated.
         */
        mReferenceUserCoronaResult1.addValueEventListener(new ValueEventListener() {
            /**
             * Able to reed the data from the Realtime databse I call DataSnapshot that literally take an "image of the database", a Snapshot.
             * @param dataSnapshot
             */
            @Override
            //asynchronous method!! -> need interface**
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // when value changes -> Load data and set into list using keyNodes
                userCorona.clear();
                //list for the keys
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    //dataSnapshot has key and value of userCorona node
                    keys.add(keyNode.getKey());
                    User user = keyNode.getValue(User.class);
                    userCorona.add(user);
                }
                //--when data is loaded call DataStatus: Loaded and pass the arraylist with the user informations and the arraylist with the keys
                dataStatus.DataIsLoaded(userCorona,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /**
     * When the user view his results he can delete his data from the Realtime database.
     * Able to do this I created a method "deleteItem".
     * This mehtod delete all data on the selected(current) item where the user is.
     * @param key : able to know witch data the user is deleting, need a key of type String.
     * @param dataStatus : able to read the data from realtime db, need an image of the "current" db, a snapSHot
     */
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
