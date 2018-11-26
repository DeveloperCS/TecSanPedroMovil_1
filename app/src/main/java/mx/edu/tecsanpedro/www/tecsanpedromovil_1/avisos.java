package mx.edu.tecsanpedro.www.tecsanpedromovil_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

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


         /*aqui implemento el toolbar (menu)*/
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Avisos");

        /*flecha atras*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public static void registerToke(String token)
    {

        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("token",token)
                .build();
        //Request request = new Request.Builder().url("http://192.168.0.13/webS/registroToken.php?XDEBUG_SESSION_START=netbeans")//10.10.20.90
        Request request = new Request.Builder().url("http://10.10.20.90/webS/registroToken.php?XDEBUG_SESSION_START=netbeans")
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

    /*las opciones del menu*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mimenu, menu);
        return true;
    }

    /*opciones de seleccion de items del menu*/
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent miintent =null;
        switch (item.getItemId())
        {
            /*Flecha atras */
            case android.R.id.home:
                Toast.makeText(this,"Calificacion",Toast.LENGTH_SHORT).show();
                miintent=new Intent(avisos.this,home.class);
                startActivity(miintent);
                finish();
                break;
            case R.id.calif:
                Toast.makeText(this,"Calificacion",Toast.LENGTH_SHORT).show();
                miintent=new Intent(avisos.this,home.class);
                startActivity(miintent);
                finish();
                break;
            case R.id.carga:
                Toast.makeText(this,"Carga",Toast.LENGTH_SHORT).show();
                break;
            case R.id.kardex:
                Toast.makeText(this,"kardex",Toast.LENGTH_SHORT).show();
                break;
            case R.id.contacto:
                Toast.makeText(this,"Contacto",Toast.LENGTH_SHORT).show();
                miintent= new Intent(avisos.this,contacto.class);
                startActivity(miintent);
                finish();
                break;
            case R.id.avisos:
                Toast.makeText(this,"avisos",Toast.LENGTH_SHORT).show();
                miintent = new Intent(avisos.this,avisos.class);
                startActivity(miintent);
                finish();
                break;
            case R.id.salir:
                Toast.makeText(this,"Cerro Sesión",Toast.LENGTH_SHORT).show();
                Preferences1.guardarBoolean(avisos.this,false,Preferences1.PREFERENCE_ESTADo);
                miintent = new Intent(avisos.this,MainActivity.class);
                startActivity(miintent);
                finish();
                break;
        }
        return false;
    }
}
