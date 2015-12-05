package com.example.jesus.fundahog;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Jesus on 12/3/2015.
 */
public class DataBaseManager {
    public static final  String TABLA_USUARIO = " create table usuario ( "
            + "_id " + "  integer primary key autoincrement,  "
            + "nombre " + " varchar (25) not null, "
            + "apellido " + " varchar (25) not null, "
            + "cedula " + " varchar (25) not null, "
            + "sexo " + " varchar (25) not null, "
            + "fechaNacimiento " + " date not null, "
            + "lugarNacimiento " + " varchar (25) not null, "
            + "email " + " varchar (25) not null, "
            + "contrasena " + " varchar (25) not null, "
            + "preguntaSeguridad " + " varchar (25) not null, "
            + "respuestaSeguridad " + " varchar (50) not null, "
            + "numeroHistoriaMedica " + " varchar (25) not null, "
            + "telefonoContacto1 " + " varchar (25) not null, "
            + "telefonoContacto2 " + " varchar (25) not null );" ;

    public static final  String TABLA_MEDICO = " create table medico ( "
            + "_id " + "  integer primary key autoincrement,  "
            + "prefijo " + " varchar (2) not null, "
            + "nombre " + " varchar (25) not null, "
            + "apellido " + " varchar (25) not null, "
            + "ubicacion " + " varchar (25) not null, "
            + "telefonoContacto1 " + " varchar (25) not null, "
            + "telefonoContacto2 " + " varchar (25)  );" ;


    private  DataBaseHelper helper;
    private SQLiteDatabase db;
    /* Si la base de datos no existe se crea y se devuelve en modo escritura, y si
     * existe se devuelve en modo lectura */
    public DataBaseManager(Context context) {
        helper = new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }

    /*Insertar usuario en la BD utilizando los metodos de android*/
    public void insertarUsuario(String nombre, String apellido, String cedula,String sexo, String fechaNacimiento,
                                String lugarNacimiento, String email, String contrasena, String preguntaSeguridad,
                                String respuestaSeguridad, String numeroHistoriaMedica, String telefonoContacto1, String telefonoContacto2)
    {
        ContentValues valores = new ContentValues();
        valores.put("nombre",nombre);
        valores.put("apellido",apellido);
        valores.put("cedula",cedula);
        valores.put("sexo",sexo);
        valores.put("fechaNacimiento",fechaNacimiento);
        valores.put("lugarNacimiento",lugarNacimiento);
        valores.put("email",email);
        valores.put("contrasena",contrasena);
        valores.put("preguntaSeguridad",preguntaSeguridad);
        valores.put("respuestaSeguridad",respuestaSeguridad);
        valores.put("numeroHistoriaMedica",numeroHistoriaMedica);
        valores.put("telefonoContacto1",telefonoContacto1);
        valores.put("telefonoContacto2",telefonoContacto2);
        db.insert("usuarios",null,valores);

    }

    /*Insertar medico en la BD utilizando los metodos de android*/
    public void insertarMedico(String prefijo, String nombre, String apellido,String ubicacion,String telefonoContacto1,
                               String telefonoContacto2)
    {
        ContentValues valores = new ContentValues();
        valores.put("prefijo",prefijo);
        valores.put("nombre",nombre);
        valores.put("apellido",apellido);
        valores.put("ubicacion",ubicacion);
        valores.put("telefonoContacto1",telefonoContacto1);
        valores.put("telefonoContacto2",telefonoContacto2);
        db.insert("medico",null,valores);

    }

}
