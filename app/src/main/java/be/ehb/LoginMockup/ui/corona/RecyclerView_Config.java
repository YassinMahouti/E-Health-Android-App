package be.ehb.LoginMockup.ui.corona;

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

import java.util.List;import be.ehb.Ehealth.R;


public class RecyclerView_Config {
    //need a context for set activities
    private Context mContext;

    private UserAdapter mUserAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<User> users , List<String> keys){
        mContext = context;
        mUserAdapter = new UserAdapter(users,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mUserAdapter);
    }


    //implementing th e layout
    class UserCoronaItemView extends RecyclerView.ViewHolder {
        private TextView mCountSymptoms;
        private TextView mUserRisk;
        private TextView mDate;


        private String key;
        public UserCoronaItemView(ViewGroup parent)
        {
            super(LayoutInflater.from(mContext)
                    .inflate(R.layout.activity_user_corona_list_item, parent , false));
            mCountSymptoms = (TextView) itemView.findViewById(R.id.txt_countSymptoms);
            mDate =(TextView) itemView.findViewById(R.id.txt_date);
            mUserRisk =(TextView) itemView.findViewById(R.id.txt_Risk);
            //when user click on result
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(mContext,UserCoronaDetails.class);
                    intent.putExtra("key",key);
                    intent.putExtra("date",mDate.getText().toString());
                    intent.putExtra("risk",mUserRisk.getText().toString());
                    intent.putExtra("countSymptoms",mCountSymptoms.getText().toString());
                    mContext.startActivity(intent);
                }
            });


        }
        public void bind(User userCorona, String key)
        {
            mUserRisk.setText(Integer.toString((int) userCorona.getUser_risk()));
            mDate.setText(userCorona.getDate());
            mCountSymptoms.setText(Integer.toString(userCorona.getCountSymptoms()));
            this.key =key;

        }
    }
    class UserAdapter extends RecyclerView.Adapter<UserCoronaItemView>{
        private List<User> mUserList;
        private List<String> mkeys;

        public UserAdapter(List<User> mUserList, List<String> mkeys) {
            this.mUserList = mUserList;
            this.mkeys = mkeys;
        }

        @NonNull
        @Override
        public UserCoronaItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new UserCoronaItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull UserCoronaItemView holder, final int position) {
            holder.bind(mUserList.get(position), mkeys.get(position));

        /*
        //locaal items uit  de lijst verwijderen
        holder.imgbtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                removeAt(position);

                }
            });*/

        }
        /*
        private void removeAt(int position) {
            mUserList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, mUserList.size());
        }*/

        @Override
        public int getItemCount() {
            return mUserList.size();
        }
    }

}
