package com.example.vkr.Calendar;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.os.IResultReceiver;

import androidx.annotation.DrawableRes;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.vkr.Activity.CalendarActivity;
import com.example.vkr.Activity.ClientActivity;
import com.example.vkr.Fragments.ClientPlanTreningFragment;
import com.example.vkr.Fragments.LookAllTrensFragment;
import com.example.vkr.R;
import com.example.vkr.user_interface.Storage;

public class AlarmReceiver extends BroadcastReceiver {
    long[] vibrate = new long[] {1000,1000,1000,1000,1000,1000,1000,1000};

    @Override
    public void onReceive(Context context, Intent intent) {
        String event = intent.getStringExtra("event");
        String time = intent.getStringExtra("time");
        int notId = intent.getIntExtra("id",0);
        Intent activityIntent1 = new Intent(context, ClientActivity.class);
        Intent activityIntent2 = new Intent(context, CalendarActivity.class);


        PendingIntent done = PendingIntent.getActivity(context, 10, activityIntent1,0);
        PendingIntent potom = PendingIntent.getActivity(context, 0, activityIntent2,0);


        String channelId = "channel_id";
        CharSequence name = "channel_name";
        String descruption = "descruption";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(channelId, name, NotificationManager.IMPORTANCE_HIGH);
            AudioAttributes aud = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION).setContentType(AudioAttributes.CONTENT_TYPE_SPEECH).build();
            channel.setVibrationPattern(vibrate);
            channel.setSound((RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALL)),aud);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }


        Notification notification = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.zastavkaxxx)
                .setContentTitle(event)
                .setContentText(time)
                .setAutoCancel(true)
                .setGroup("Group_calendar_view")
                .setSound((RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALL)))
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .addAction(R.drawable.done,"Выполнить",done).addAction(R.drawable.potom,"Позже",potom)
                .build();



        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(notId, notification);


    }
}
