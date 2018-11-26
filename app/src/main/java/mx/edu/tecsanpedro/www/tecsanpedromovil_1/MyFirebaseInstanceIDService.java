package mx.edu.tecsanpedro.www.tecsanpedromovil_1;

import android.app.Service;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;



public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService{
    public MyFirebaseInstanceIDService() {
    }
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        avisos.registerToke(token);

    }
}
