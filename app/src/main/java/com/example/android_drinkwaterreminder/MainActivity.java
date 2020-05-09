package com.example.android_drinkwaterreminder;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "1";
    Switch switch_state;
    EditText txt_weight;
    Button confirm_btn;
    TextView amountToDrink;
    boolean save = false;

    public void intitialize() {      //intitialisation of android screen vraiables
        switch_state = findViewById(R.id.switch_status);
        txt_weight = findViewById(R.id.Txt_weight);
        confirm_btn = findViewById(R.id.btn_confirm);
    }

    public double calcToDrinkWater(int weight) {     //methode to calculate amount of to drink water
        double toDrink = weight * 0.033;
        return toDrink;
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "DrinkWaterReminder";
            String description = "Reminds to Drink water every 2 hours";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intitialize();
        switch_state.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    txt_weight.setVisibility(View.VISIBLE);
                    confirm_btn.setVisibility(View.VISIBLE);
                } else {
                    txt_weight.setVisibility(View.INVISIBLE);
                    confirm_btn.setVisibility(View.INVISIBLE);
                }
                createNotificationChannel();
            }
        });


        //set notification
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Typeconvertion to get the userweight edit_text -> Integer
                String value = txt_weight.getText().toString();
                int user_weight = Integer.parseInt(value);


                //set time to notif
                Date date = new Date();   // given date
                Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
                calendar.setTime(date);   // assigns calendar to given date
                int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                int nextNotif = currentHour + 2;
                Calendar notif = calendar.getInstance();


                if (switch_state.isChecked()) {
                    if (!(currentHour >= 1 && currentHour <= 7)) {
                        //notification
                        String message = "Drink water now";
                        NotificationCompat.Builder builder;
                        builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID);
                        builder.setSmallIcon(R.drawable.ic_drinkwater);
                        builder.setContentTitle("Reminder");
                        builder.setContentText(message);
                        builder.setAutoCancel(true);

                        Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("message", message);
                        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                        PendingIntent contentIntent = PendingIntent.getService(MainActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                        builder.setContentIntent(pendingIntent);




                        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        notificationManager.notify(0, builder.build());

                        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                        //alarmManager.setExact(AlarmManager.RTC_WAKEUP, notif.getTimeInMillis(), pendingIntent);
                        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, System.currentTimeMillis(), 100, contentIntent);


                    }
                }

            }
        });
    }
}

