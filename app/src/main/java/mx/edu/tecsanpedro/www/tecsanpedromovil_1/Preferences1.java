package mx.edu.tecsanpedro.www.tecsanpedromovil_1;

import android.content.Context;
import android.content.SharedPreferences;


public class Preferences1
{
    ///ids para guardar los estados de sesion en database local
    public static final String STRING_PREFERENCE ="www.tecsanpedromovil_1";///este es su id
    public static final String PREFERENCE_ESTADo ="estado.buton.sesion"; /// guara el estado de sesion
    public static final String PREFERENCE_Al ="matricula.id"; //guarda matricula

    //obtener usuario se sesrion

    public static boolean obtenerBoolen(Context x,String key)
    {
        SharedPreferences preferences = x.getSharedPreferences(STRING_PREFERENCE,x.MODE_PRIVATE);
        return preferences.getBoolean(key,false);

    }
    public static String obtenerString(Context x,String key)
    {
        SharedPreferences preferences = x.getSharedPreferences(STRING_PREFERENCE,x.MODE_PRIVATE);
        return preferences.getString(key,"");

    }
    public static void guardarBoolean(Context c, boolean b,String key)
    {
        SharedPreferences preferences = c.getSharedPreferences(STRING_PREFERENCE,c.MODE_PRIVATE);
        preferences.edit().putBoolean(key,b).apply();
    }
    public static void guardarString(Context c, String b,String key)
    {
        SharedPreferences preferences = c.getSharedPreferences(STRING_PREFERENCE,c.MODE_PRIVATE);
        preferences.edit().putString(key,b).apply();
    }
}
