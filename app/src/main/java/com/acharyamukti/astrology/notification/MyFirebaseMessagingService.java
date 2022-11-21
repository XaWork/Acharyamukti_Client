package com.acharyamukti.astrology.notification;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.acharyamukti.R;
import com.acharyamukti.astrology.activity.Launcher;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMessaging";

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);

        Log.e(TAG, token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        FirebaseMessaging messaging = FirebaseMessaging.getInstance();
        messaging.setAutoInitEnabled(true);

        Log.d(TAG, "From: " + remoteMessage.getFrom());
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            sendNotification(remoteMessage.getFrom(), remoteMessage.getNotification().getBody());
        }
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private void sendNotification(String from, String body) {
        Intent notificationIntent = new Intent(this, Launcher.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                    PendingIntent.FLAG_IMMUTABLE);
        } else {
            pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        }

        long[] vibrate = {1000L, 1000L, 1000L, 1000L};

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "0")
                .setSmallIcon(R.drawable.logo)
                .setContentTitle(from)
                .setVibrate(vibrate)
                .setOnlyAlertOnce(true)
                .setContentText(body)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        builder = builder.setContent(getRemoteView(from, body));

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("0", "channelName", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(notificationChannel);
        }
        manager.notify(0, builder.build());
    }

    private RemoteViews getRemoteView(String title, String desc) {
        @SuppressLint("RemoteViewLayout") RemoteViews remoteViews =
                new RemoteViews(getApplicationContext().getPackageName(),
                        R.layout.lyt_notification);

        remoteViews.setTextViewText(R.id.notificationTitle, title);
        remoteViews.setTextViewText(R.id.notificationMessage, desc);
        remoteViews.setImageViewResource(R.id.notificationImage, R.drawable.logo);

        return remoteViews;
    }
}
