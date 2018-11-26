package mx.edu.tecsanpedro.www.tecsanpedromovil_1;

import android.app.NotificationManager;
import android.app.PendingIntent;

import android.content.Intent;
import android.support.v4.app.NotificationCompat;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


import java.util.Map;


public class MyFirebaseMessagingService extends FirebaseMessagingService{
    public MyFirebaseMessagingService() {
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        String result = "From : " + remoteMessage.getFrom() + "\nMessageId = " + remoteMessage.getMessageId() + "\nMessageType =  " + remoteMessage.getMessageType()
                + "\nCollapeseKey = " + remoteMessage.getCollapseKey() + "\nTo: " + remoteMessage.getTo() + "\nTtl = " + remoteMessage.getTtl()
                /*+"\nTitle = " + remoteMessage.getNotification().getTitle()
                + "\nBody = " + remoteMessage.getNotification().getBody()*/ + "\nSent Time = " + remoteMessage.getSentTime();
        Map<String, String> map = remoteMessage.getData();
        for (String key : map.keySet())
            result += "\n(" + key + "," + map.get(key) + ")";

        Intent intent = new Intent(this, avisos.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("result", result);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentTitle("Firebase Cloud Messaging Demo")
                .setSmallIcon(android.R.drawable.stat_notify_chat)
                .setContentIntent(pi);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
