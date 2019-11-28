package com.example.alarmapp;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;

public class MyBroadcast extends BroadcastReceiver {

    private  final String CHANNEL_ID = "personal_notification";
    public static final int NOTIFICATION_ID = 1;

    public void onReceive(Context context, Intent intent){

        Vibrator vibrator = (Vibrator)context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(60000);

        Notification noti = new Notification.Builder(context)
                .setContentTitle("Alarm is on")
                .setContentText("you set up the alarm")
                .setSmallIcon(R.drawable.alarm_notification).build();
        NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        noti.flags = Notification.FLAG_AUTO_CANCEL;
        manager.notify(0,noti);
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        Ringtone r = RingtoneManager.getRingtone(context,notification);
        r.play();

        Intent i = new  Intent();
        i.setClassName("com.example.alarmapp","com.example.alarmapp.StopAlarm");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }


}
