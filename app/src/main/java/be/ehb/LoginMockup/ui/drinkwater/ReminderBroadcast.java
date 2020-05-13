package be.ehb.LoginMockup.ui.drinkwater;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import be.ehb.Ehealth.R;


import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ReminderBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "notifyDrink")
                .setSmallIcon(R.drawable.ic_drink_water)
                .setContentTitle("REMINDER !")
                .setContentText("Dont forget to drink water and move if you are sitting still for more than 2 hours !")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(200, builder.build());
    }
}
