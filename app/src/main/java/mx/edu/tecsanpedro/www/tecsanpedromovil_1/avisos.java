package mx.edu.tecsanpedro.www.tecsanpedromovil_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

//import com.google.android.gms.common.api.Api;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class avisos extends AppCompatActivity {

    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avisos);

        txt =(TextView) findViewById(R.id.txtResultM);
        if(getIntent().hasExtra("result"))
        {
            txt.setText(getIntent().getStringExtra("result"));
        }
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        String token = FirebaseInstanceId.getInstance().getToken();

        Log.d("token",token);
        registerToke(token);
    }

    public static void registerToke(String token)
    {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("token",token)
                .build();
        Request request = new Request.Builder().url("http://192.168.0.13/webS/registroToken.php?XDEBUG_SESSION_START=netbeans")
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e)
            {
                Log.d("OkHttp",call.toString());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                if(response.isSuccessful())
                {
                    String responseStr = response.body().string();
                    Log.d("OkHttp",responseStr);
                }
            }
        });

    }
}
