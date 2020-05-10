package be.ehb.proj.mybfpapp;

import android.content.Context;
import android.content.Intent;
import android.opengl.ETC1Util;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * To use recycler view in created a new class for Recycler Configuration.
 * I created a list_item layout.xml and to be able to set or read dat I will need an adapter
 */
public class RecyclerView_Config {
    //--need a context to set activities
    private Context mContext;
    //--need adapter to read/write info of the user
    private UserAdapter mUserAdapter;
    //--setup the config: Recycler, context, List, key
    public void setConfig(RecyclerView recyclerView, Context context, List<User> users , List<String> keys){
        mContext = context;
        mUserAdapter = new UserAdapter(users,keys);
        //--I used a RecyclerView, not a List view so need a Layout Manager
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mUserAdapter);
    }
    //implementing the layout, set values into TextViews
    class UserCoronaItemView extends RecyclerView.ViewHolder {
        //------TextViews----------------
        private TextView mCountSymptoms;
        private TextView mUserRisk;
        private TextView mDate;
        //-----key for list--------------
        private String key;
        public UserCoronaItemView(ViewGroup parent)
        {
            //--need inflater to  instantiate a layout from a XML file into its corresponding view objects
            super(LayoutInflater.from(mContext)
                    .inflate(R.layout.usercorona_list_item, parent , false));
            //---associate Texviews with id in layout
            mCountSymptoms = (TextView) itemView.findViewById(R.id.txt_countSymptoms);
            mDate =(TextView) itemView.findViewById(R.id.txt_date);
            mUserRisk =(TextView) itemView.findViewById(R.id.txt_Risk);
            //when user click on result Button Onclicklistener on item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //--Able to pass info to the next activity: intent.putExtra()
                    //--There the user will have a detailed view of his result
                    Intent intent =new Intent(mContext,UserCoronaDetailsActivity.class);
                    intent.putExtra("key",key);
                    intent.putExtra("date",mDate.getText().toString());
                    intent.putExtra("risk",mUserRisk.getText().toString());
                    intent.putExtra("countSymptoms",mCountSymptoms.getText().toString());
                    mContext.startActivity(intent);
                }
            });
        }
        //--Able to bind the info of the user, I created a methode that needs a User and a Key
        public void bind(User userCorona, String key)
        {
            mUserRisk.setText(Integer.toString((int) userCorona.getUser_risk()));
            mDate.setText(userCorona.getDate());
            mCountSymptoms.setText(Integer.toString(userCorona.getCountSymptoms()));
            this.key =key;

        }
    }
    //--Using the Adapter from RecyclerView in android studio, set a itemView
    class UserAdapter extends RecyclerView.Adapter<UserCoronaItemView>{
        private List<User> mUserList;
        private List<String> mkeys;

        /**
         * Constructor for the Useradapter extending from RecuclerView.Adapter from Android Studio.
         * @param mUserList : Need a List with the values of the user.
         * @param mkeys : Need a key to associate the good information
         */
        public UserAdapter(List<User> mUserList, List<String> mkeys) {
            this.mUserList = mUserList;
            this.mkeys = mkeys;
        }
        //--Generate @override onCreate and onBindHodlder(cause I used a layout item view) and a Count to know where we are in the list
        @NonNull
        @Override
        public UserCoronaItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new UserCoronaItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull UserCoronaItemView holder, final int position) {
            holder.bind(mUserList.get(position), mkeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mUserList.size();
        }
    }

}
