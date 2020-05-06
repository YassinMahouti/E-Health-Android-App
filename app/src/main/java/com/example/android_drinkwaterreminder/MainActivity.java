package com.example.android_drinkwaterreminder;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.time.LocalTime;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID ="Notification" ;
    Switch switch_state;
    EditText txt_weight;
    Button confirm_btn;
    boolean save=false;

    public void intitialize(){      //intitialisation of android screen vraiables
        switch_state = findViewById(R.id.switch_status);
        txt_weight = findViewById(R.id.Txt_weight);
        confirm_btn=findViewById(R.id.btn_confirm);
        if (switch_state.isChecked()) {
            save = true;
        }
    }

    public double calcToDrinkWater(int weight){     //methode to calculate amount of to drink water
        double toDrink = weight*0.033;
        return toDrink;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intitialize();
        if (save == true) {
            txt_weight.setVisibility(View.VISIBLE);
        }


        //set notification
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Typeconvertion to get the userweight edit_text -> Integer
                String value = txt_weight.getText().toString();
                int user_weight = Integer.parseInt(value);

                //set time to notif
                LocalTime currentTime = LocalTime.now();
                LocalTime currentTimeAdvanced = LocalTime.now();

                while (save == true) {
                    while (currentTime.getHour() >= 1 && currentTime.getHour() <= 7) {
                        if (currentTime == currentTimeAdvanced) {
                            //notification
                            String message = "Drink water now";
                            NotificationCompat.Builder builder = new NotificationCompat.Builder(
                                    MainActivity.this
                            )
                                    .setSmallIcon(R.drawable.ic_drinkwater)
                                    .setContentTitle("Reminder")
                                    .setContentText(message)
                                    .setAutoCancel(true);

                            Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("message", message);

                            PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                            builder.setContentIntent(pendingIntent);

                            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                            notificationManager.notify(0, builder.build());

                            //move next notification until 2 hours from current notif
                            currentTimeAdvanced = currentTime.plusHours(2);
                        }
                    }
                }

            }
        });
    }
}
