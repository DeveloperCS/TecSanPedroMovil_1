package mx.edu.tecsanpedro.www.tecsanpedromovil_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class contacto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);


         /*aqui implemento el toolbar (menu)*/
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Contacto");
        /*barra atras*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                miintent=new Intent(contacto.this,home.class);
                startActivity(miintent);
                finish();
                break;
            case R.id.calif:
                Toast.makeText(this,"Calificacion",Toast.LENGTH_SHORT).show();
                miintent=new Intent(contacto.this,home.class);
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
                miintent= new Intent(contacto.this,contacto.class);
                startActivity(miintent);
                finish();
                break;
            case R.id.avisos:
                Toast.makeText(this,"avisos",Toast.LENGTH_SHORT).show();
                miintent = new Intent(contacto.this,avisos.class);
                startActivity(miintent);
                finish();
                break;
            case R.id.salir:
                Toast.makeText(this,"Cerro Sesión",Toast.LENGTH_SHORT).show();
                Preferences1.guardarBoolean(contacto.this,false,Preferences1.PREFERENCE_ESTADo);
                miintent = new Intent(contacto.this,MainActivity.class);
                startActivity(miintent);
                finish();
                break;
        }
        return false;
    }

}
