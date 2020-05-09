package be.ehb.proj.basicbfpapplication.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import be.ehb.proj.basicbfpapplication.R;
import be.ehb.proj.basicbfpapplication.model.User;

public class RecylerView_Config {
    private Context mContext;

    private UserAdapter mUserAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<User> users , List<String> keys){
        mContext = context;
        mUserAdapter = new UserAdapter(users,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mUserAdapter);
    }


    //implementing th e layout
    class UserBBItemView extends RecyclerView.ViewHolder {
        private TextView mHeight;
        private TextView mWeight;
        private TextView mAge;
        private TextView mGender;
        private TextView mUserBMI;
        private TextView mUserBFP;
        private TextView mDate;


        private String key;
        public UserBBItemView(ViewGroup parent)
        {
            super(LayoutInflater.from(mContext)
                    .inflate(R.layout.user_list_item_for_bmi_bfp, parent , false));
            mAge =(TextView) itemView.findViewById(R.id.txt_age);
            mHeight = (TextView) itemView.findViewById(R.id.txt_height);
            mWeight = (TextView) itemView.findViewById(R.id.txt_weight);
            mDate =(TextView) itemView.findViewById(R.id.txt_Date);
            mUserBMI =(TextView) itemView.findViewById(R.id.txt_BMI);
            mUserBFP =(TextView) itemView.findViewById(R.id.txt_BFP);
            mGender =(TextView)  itemView.findViewById(R.id.txt_gender);
            //when user click on result
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(mContext,UserDetails.class);
                    intent.putExtra("key",key);
                    intent.putExtra("date",mDate.getText().toString());
                    intent.putExtra("user_height",mHeight.getText().toString());
                    intent.putExtra("user_weight",mWeight.getText().toString());
                    intent.putExtra("user_age",mAge.getText().toString());
                    intent.putExtra("user_sex",mGender.getText().toString());
                    intent.putExtra("user_bmi",mUserBMI.getText().toString());
                    intent.putExtra("user_bfp",mUserBFP.getText().toString());
                    mContext.startActivity(intent);
                }
            });


        }
        public void bind(User userBB, String key)
        {
            mDate.setText(userBB.getDate());
            mUserBFP.setText(Float.toString(userBB.getUser_bfp()));
            mUserBMI.setText(Float.toString(userBB.getUser_bmi()));
            if( userBB.getUser_bmi() >=30.0f || userBB.getUser_bmi() <18.5f)
            {
                mUserBMI.setTextColor(Color.RED);
            }
            else   mUserBMI.setTextColor(Color.GREEN);
            if(userBB.getUser_sex()== "woman" )
            {
                if(userBB.getUser_bfp() >=32.0f || userBB.getUser_bfp() <10.0f)
                {
                    mUserBFP.setTextColor(Color.RED);
                }
                else   mUserBFP.setTextColor(Color.GREEN);
            }
            else
            {
                if( userBB.getUser_bfp() >=25.0f || userBB.getUser_bfp() <3.0f)
                {
                    mUserBFP.setTextColor(Color.RED);
                }
                else   mUserBFP.setTextColor(Color.GREEN);
            }


            mGender.setText(userBB.getUser_sex());
            mHeight.setText(Float.toString(userBB.getUser_height())+"cm");
            mWeight.setText(Float.toString(userBB.getUser_weight())+"kg");
            mAge.setText(Integer.toString(userBB.getUser_age()));



            this.key =key;

        }
    }
    class UserAdapter extends RecyclerView.Adapter<UserBBItemView>{
        private List<User> mUserList;
        private List<String> mkeys;

        public UserAdapter(List<User> mUserList, List<String> mkeys) {
            this.mUserList = mUserList;
            this.mkeys = mkeys;
        }

        @NonNull
        @Override
        public UserBBItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new UserBBItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull UserBBItemView holder, final int position) {
            holder.bind(mUserList.get(position), mkeys.get(position));

        }

        @Override
        public int getItemCount() {
            return mUserList.size();
        }
    }

}

