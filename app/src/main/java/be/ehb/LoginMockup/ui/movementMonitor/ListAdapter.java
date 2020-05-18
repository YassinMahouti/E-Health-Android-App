package be.ehb.LoginMockup.ui.movementMonitor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import be.ehb.Ehealth.R;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.listHolder> {
    private List<Activity> activities;

    public ListAdapter(List activities){
        this.activities = activities;
    }

    @NonNull
    @Override
    public ListAdapter.listHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_movement_monitor_list_cards, parent, false);
        return new listHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.listHolder holder, int position) {
        holder.display(activities.get(position), holder.itemView.getContext());
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    public Activity getactivityfromsize( int getal){
        return activities.get(getal);
    }

    public class listHolder extends RecyclerView.ViewHolder{

        private TextView name_activity, duration,kcal, intensity, type;
        private ImageView imageActivity;
        private String imageString;

        public listHolder(@NonNull View itemView) {
            super(itemView);

            Button button_to_start_activity;
            button_to_start_activity = itemView.findViewById(R.id.button_start_activity_start_now);
            button_to_start_activity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String name_activity_string = name_activity.getText().toString();
                    int duration_int = Integer.parseInt(duration.getText().toString());
                    int kcal_int = Integer.parseInt(kcal.getText().toString());

                    Intent intent = new Intent(v.getContext(), StartActivity.class);
                    intent.putExtra("name activity", name_activity_string);
                    intent.putExtra("kcal", kcal_int);
                    intent.putExtra("duration", duration_int);
                    intent.putExtra("image", imageString);
                    v.getContext().startActivity(intent);
                }
            });
            name_activity = itemView.findViewById(R.id.textView_list_activity_variable_name_activity);
            duration = itemView.findViewById(R.id.textView_list_activity_variable_duration);
            kcal = itemView.findViewById(R.id.textView_list_activity_variable_kcal);
            intensity = itemView.findViewById(R.id.textView_list_activity_variable_intensity);
            type = itemView.findViewById(R.id.textView_list_activity_variable_type);
            imageActivity = itemView.findViewById(R.id.imageView_list_activity_variable_background);
        }

        void display(Activity activity, Context context){
            name_activity.setText(activity.getActivityName());
            duration.setText(String.valueOf(activity.getDuration()));
            kcal.setText(String.valueOf(activity.getKcal()));
            intensity.setText("Intensity: " + String.valueOf(activity.getIntensity()));
            type.setText("type: " + String.valueOf(activity.getType()));
            imageString = activity.getActivityImage();
            Picasso.with(context).load(String.valueOf(imageString)).into(imageActivity);
        }
    }
}