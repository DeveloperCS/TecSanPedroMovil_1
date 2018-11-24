package mx.edu.tecsanpedro.www.tecsanpedromovil_1;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
/*
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
*/
import java.io.PipedInputStream;

/**
 * Created by Dell on 22/11/2018.
 */

public class MyFirebaseMessagingService /*extends FirebaseMessagingService*/ {

   /* @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String msj = remoteMessage.getData().get("mensaje");
        showNotification(msj);
    }

    private void showNotification(String mensaje)
    {
        Intent i = new Intent(this,contacto.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent prePendingIntent = PendingIntent.getActivity(this,0,1,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this).setAutoCancel(true).setContentTitle("ds").setContentText(mensaje).setSmallIcon(R.mipmap.ic_launcher).setContentIntent(prePendingIntent);

        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());
    }*/
}
