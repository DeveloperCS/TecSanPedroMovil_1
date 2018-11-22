package mx.edu.tecsanpedro.www.tecsanpedromovil_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         /*aqui implemento el toolbar (menu)*/
      /*  Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tec San Pedro Movil");*/
    }

    public void onClick(View view)
    {
        /*forma de comprar campos vacios*/
        EditText mat = (EditText) findViewById(R.id.txtMatricula);
        EditText curp = (EditText) findViewById(R.id.txtCurp);

        if(TextUtils.isEmpty(mat.getText())||TextUtils.isEmpty(curp.getText()))
        {
            mat.setError( "Matrícula Requerida!" );
            curp.setError( "Curp Requerida!" );
        }
        else
        {
            Intent miintent = new Intent(MainActivity.this,home.class);
            startActivity(miintent);
        }

    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mimenu, menu);
        return true;
    }*/
}
