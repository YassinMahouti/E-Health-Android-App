package be.ehb.LoginMockup.ui.drinkwater;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import be.ehb.Ehealth.R;


public class DrinkWaterReminderActivity extends AppCompatActivity {

    //intitialization of local variables
    private static final String CHANNEL_ID = "1";
    Switch switch_state;
    EditText txt_weight;
    Button confirm_btn;
    TextView tv_StringToDrink;
    DatabaseReference mDB_User;
    DatabaseReference mDB_User_Update;
    UserWaterReminder user;

    //Creates another Local Date instance and set time to notif
    Date date = java.util.Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String str_Date = dateFormat.format(date);

    //initialization of button and key variables
    public void intitialize() {
        switch_state = findViewById(R.id.switch_status);
        txt_weight = findViewById(R.id.Txt_weight);
        confirm_btn = findViewById(R.id.btn_confirm);
        tv_StringToDrink = findViewById(R.id.tv_StringToDrink);
    }


    //Methode to create android notification channel
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

    //Method to calculate amount of to drink water everytime the reminder goes off
    public double calcToDrinkWater(int weight) {
        double toDrink = weight * 0.033;
        return toDrink;
    }

    private void getValues(int userweight, String weight, String str_Date) {
        double amountwater = calcToDrinkWater(userweight);
        user.setUserId("1");
        user.setWeight(weight);
        user.setAmountOfDrunkWater(amountwater);
        user.setStr_Date(str_Date);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_water_reminder);
        intitialize();                                   //initializes local button and ket variables


        //Databaseconnetion with firebase
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        mDB_User = db.getReference().child("User_Drinkwater").push();
        mDB_User_Update = db.getReference().child("User_Drinkwater");


        switch_state.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {    //Listener when reminder gets toggld on
                if (isChecked == true) {
                    txt_weight.setVisibility(View.VISIBLE);
                    confirm_btn.setVisibility(View.VISIBLE);
                } else {
                    txt_weight.setVisibility(View.INVISIBLE);
                    confirm_btn.setVisibility(View.INVISIBLE);
                    tv_StringToDrink.setVisibility((View.INVISIBLE));
                }
                createNotificationChannel(); //Turns on notification channel once reminder is ON
            }
        });


        //set notification & sends data over to database
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotificationChannel(); //Turns on notification channel once clicked
                //Creates another Local Date instance and set time to notif
                Date date = new Date(); // given date
                Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
                calendar.setTime(date);   // assigns calendar to given date
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String str_Date = dateFormat.format(date);
                int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                String value;

                //Typeconvertion to get the userweight edit_text -> Integer
                try {
                    value = txt_weight.getText().toString();
                    int user_weight = Integer.parseInt(value);
                    user = new UserWaterReminder();                  //creates new user
                    getValues(user_weight, value, str_Date);
                    tv_StringToDrink.setVisibility((View.VISIBLE));
                    tv_StringToDrink.setText("You have to drink at least " + calcToDrinkWater(user_weight) + "l of water today");


                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(DrinkWaterReminderActivity.this, "Don't leave the field blank", Toast.LENGTH_SHORT).show();
                }


                //Show the amount of to drink water to the user

                compareDates(str_Date);
                if (compareDates(str_Date) == true) {
                    mDB_User.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            mDB_User.setValue(user);
                            Toast.makeText(DrinkWaterReminderActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }

                    });
                } else if (compareDates(str_Date) == false) {
                    mDB_User_Update.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            mDB_User.setValue(user);
                            Toast.makeText(DrinkWaterReminderActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                    //Loop creates notification
                    if (switch_state.isChecked()) {
                        if (!(currentHour >= 1 && currentHour <= 7)) {
                            //notification builder
                            String message = "Drink water now";
                            NotificationCompat.Builder builder = new NotificationCompat.Builder(DrinkWaterReminderActivity.this, CHANNEL_ID);
                            builder.setSmallIcon(R.drawable.ic_drink_water);
                            builder.setContentTitle("Reminder");
                            builder.setContentText(message);
                            builder.setAutoCancel(true);

                            Intent intent = new Intent(DrinkWaterReminderActivity.this, NotificationActivity.class);

                            //   start();        //start alarmmanager

                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.putExtra("message", message);
                            PendingIntent pendingIntent = PendingIntent.getActivity(DrinkWaterReminderActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                            builder.setContentIntent(pendingIntent);


                            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                            notificationManager.notify(1, builder.build());

                            if (!(switch_state.isChecked())) {
                                cancel();
                            }
                        }
                    }
                }

            }

            Intent intent = new Intent(DrinkWaterReminderActivity.this, NotificationActivity.class);
            PendingIntent contentIntent = PendingIntent.getService(DrinkWaterReminderActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

            public void start() {   //method to start manager
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                //alarmManager.setExact(AlarmManager.RTC_WAKEUP, notif.getTimeInMillis(), pendingIntent);
                alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, 200, contentIntent);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 100, contentIntent);
            }

            public void cancel() {  //method to stop manager
                AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                manager.cancel(contentIntent);
            }


        });
    }

    public boolean compareDates(String newDate) {        //compares the str_Date method
        if (newDate == str_Date) {
            str_Date = newDate;
            return true;
        } else {
            str_Date = newDate;
            return false;
        }
    }
}

