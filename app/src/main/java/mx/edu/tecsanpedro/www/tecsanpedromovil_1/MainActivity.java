package mx.edu.tecsanpedro.www.tecsanpedromovil_1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    //estado del radio
    private boolean isActivadeRb;
    private  RadioButton rbSesion ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //obtener estado de sessioon
        if(Preferences1.obtenerBoolen(MainActivity.this,Preferences1.PREFERENCE_ESTADo))
        {
            Intent miintent = new Intent(this,home.class);
            startActivity(miintent);
            finish();
        }

        /*instanciar controladores*/
        rbSesion = (RadioButton) findViewById(R.id.rbSesion);
        isActivadeRb = rbSesion.isChecked();//false

        rbSesion.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(isActivadeRb)
                {
                    rbSesion.setChecked(false);
                }
                isActivadeRb = rbSesion.isChecked();
            }
        });

    }

    //evento del boton
    public void onClick(View view)
    {
        /*forma de comprar campos vacios*/
        final EditText mat = (EditText) findViewById(R.id.txtMatricula);
        final EditText curp = (EditText) findViewById(R.id.txtCurp);

        if(TextUtils.isEmpty(mat.getText())||TextUtils.isEmpty(curp.getText()))
        {
            mat.setError( "Matrícula Requerida!" );
            curp.setError( "Curp Requerida!" );
        }
        else
        {
            //crear hilo
            Thread tr = new Thread()
            {
                @Override
                public void run()
                {
                    //le envio los parametros
                    final String resEn = enviarPost(mat.getText().toString().trim(),curp.getText().toString().trim());

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //contar la cantidad de elementos
                            int r = objJson(resEn);

                            //validar si hay elementos
                            if(r>0)
                            {
                                //guardamos estado de session
                                Preferences1.guardarBoolean(MainActivity.this,rbSesion.isChecked(),Preferences1.PREFERENCE_ESTADo);
                                //guardamos matricula para proximos usos
                                Preferences1.guardarString(MainActivity.this,mat.getText().toString().trim(),Preferences1.PREFERENCE_Al);

                                Toast.makeText(getApplicationContext(),"Entro!!",Toast.LENGTH_SHORT).show();
                                Intent miintent = new Intent(MainActivity.this,home.class);
                                startActivity(miintent);
                                finish();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Matricula o curp incorrecto",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            };

            //inicar hilo
            tr.start();

        }

    }
   /*metodo para enviar datos y hacer login*/
    public String enviarPost(String mat,String curp)
    {
        //parametros que se enviaran
        String parametros="matricula="+mat+"&curp="+curp;

        //concxion webS
        HttpURLConnection conec = null;

        //almacena la respuesta
        String res="";
        try
        {
            //conexion al servidor
            URL url = new URL("http://192.168.0.13/webS/webServiceTecLg.php");
            conec=(HttpURLConnection)url.openConnection();
            //metodo de envio
            conec.setRequestMethod("POST");
            conec.setRequestProperty("Content-Length",""+Integer.toString(parametros.getBytes().length));

            //salida de datos
            conec.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(conec.getOutputStream());
            wr.writeBytes(parametros);
            wr.close();

            ///consumir json
            Scanner inT = new Scanner(conec.getInputStream());
            //recorriendo los valores
            while(inT.hasNextLine())
            {
                res+=(inT.nextLine());
            }

        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),""+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return res.toString();
    }
    //contar los registros recibidos
    public int objJson(String rapta)
    {
        int res1 =0;
        try
        {
            //convertir la cadena nuevamente en json
            JSONArray json = new JSONArray(rapta);

            if(json.length()>0)
            {
                res1=1;
            }

        }catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),""+ex.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return res1;
    }
}
