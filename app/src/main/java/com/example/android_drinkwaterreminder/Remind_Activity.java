package com.example.android_drinkwaterreminder;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.nio.channels.Channel;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

public class Remind_Activity extends AppCompatActivity {

    public void startThread(View view) {
        Notifier notifier=new Notifier(20);
        new Thread(notifier).start();
    }


    static final String CHANNEL_ID = "1";

class myThread extends Thread{
    int seconds;
    myThread(int seconds){
        this.seconds=seconds;
    }

    @Override
    public void run(){
        for(int i=0;i<seconds;i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

    class Notifier implements Runnable {
        int seconds;
        Notifier(int seconds){
            seconds=this.seconds;
        }


        }
        public void notifier(Switch switch_state) {
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

            //set time to notif
            Date date = new Date();   // given date
            Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
            calendar.setTime(date);   // assigns calendar to given date
            int currentHour = calendar.get(Calendar.HOUR_OF_DAY);

            while (switch_state.isChecked()) {
                while (!(currentHour >= 1 && currentHour <= 7)) {
                    //notification
                    String message = "Drink water now";
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(
                            this, CHANNEL_ID
                    );
                    builder.setSmallIcon(R.drawable.ic_drinkwater);
                    builder.setContentTitle("Reminder");
                    builder.setContentText(message);
                    builder.setAutoCancel(true);

                    Intent intent = new Intent(Remind_Activity.this, NotificationActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("message", message);

                    PendingIntent pendingIntent = PendingIntent.getActivity(Remind_Activity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.setContentIntent(pendingIntent);

                    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(0, builder.build());


                }
            }
        }
    }
}

