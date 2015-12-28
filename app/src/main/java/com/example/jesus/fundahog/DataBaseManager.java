package com.example.jesus.fundahog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentManager;

import java.lang.reflect.Array;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Jesus on 12/3/2015.
 */
public class DataBaseManager {
    final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

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

    public static final  String TABLA_TRATAMIENTO = " create table tratamiento ( "
            + "_id " + "  integer primary key autoincrement,  "
            + "fecha " + " date not null, "
            + "tipoTratamiento " + " varchar (25)  );" ;

    public static final  String TABLA_NOTAS = " create table nota ( "
            + "_id " + "  integer primary key autoincrement,  "
            + "fecha " + " date not null, "
            + "titulo " + " varchar (25) not null, "
            + "nota " + " text  );" ;

    /*Tabla de examenes*/
    public static final  String TABLA_HEMATOLOGIA = " create table hematologia ( "
            + "_id " + "  integer primary key autoincrement,  "
            + "fecha " + " date not null, "
            + "fibrinogeno " + " real , "
            + "leucocitos " + " real , "
            + "hemoglobina " + " real , "
            + "hematocrito " + " real , "
            + "plaqueta " + " real , "
            + "vsg " + " real , "
            + "hcm " + " real  );" ;


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
                                String lugarNacimiento, String email, String contrasena, String numeroHistoriaMedica, String telefonoContacto1, String telefonoContacto2)
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
        valores.put("numeroHistoriaMedica",numeroHistoriaMedica);
        valores.put("telefonoContacto1",telefonoContacto1);
        valores.put("telefonoContacto2",telefonoContacto2);
        db.insert("usuario",null,valores);

    }





    //Probando esta manera
    public void insertarUsuario2(Pacient paciente)
    {
        ContentValues valores = new ContentValues();
        valores.put("nombre",paciente.getNombre());
        valores.put("apellido",paciente.getApellido());
        valores.put("cedula",paciente.getCedula());
        valores.put("sexo",paciente.getSexo());
        valores.put("fechaNacimiento",formatter.format(paciente.getFechaNacimiento()));
        valores.put("lugarNacimiento",paciente.getLugarNacimiento());
        valores.put("email",paciente.getEmail());
        valores.put("contrasena",paciente.getContrasena());
        valores.put("numeroHistoriaMedica",paciente.getNroHistoria());
        valores.put("telefonoContacto1",paciente.getTlfContacto1());
        valores.put("telefonoContacto2",paciente.getTlfContacto2());
        db.insert("usuario",null,valores);

    }


    /*Consultar datos paciente*/
    public Pacient consultarDatosPaciente(){
        Pacient paciente;
        String[] campos = {"nombre","apellido","cedula","fechaNacimiento","lugarNacimiento","sexo","email","telefonoContacto1","telefonoContacto2","numeroHistoriaMedica","contrasena"};
        Cursor c = db.query("usuario",campos,"_id=1",null,null,null,null);
        Calendar calendar = Calendar.getInstance();
        if(c.moveToFirst()){

            do{
                try {
                    calendar.setTime(formatter.parse(c.getString(3)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                paciente = new Pacient(c.getString(0),c.getString(1),c.getString(2),calendar.getTime(),c.getString(4),c.getString(5),c.getString(6),c.getString(7),c.getString(8),c.getString(9),c.getString(10));
            }while(c.moveToNext());
            return paciente;
        }

        return null;

    }

    /*Modificar datos paciente*/
    public void actualizarPaciente(Pacient paciente){
        ContentValues valores = new ContentValues();
        valores.put("nombre",paciente.getNombre());
        valores.put("apellido",paciente.getApellido());
        valores.put("cedula",paciente.getCedula());
        valores.put("sexo",paciente.getSexo());
        valores.put("fechaNacimiento",formatter.format(paciente.getFechaNacimiento()));
        valores.put("lugarNacimiento",paciente.getLugarNacimiento());
        valores.put("email",paciente.getEmail());
        valores.put("contrasena",paciente.getContrasena());
        valores.put("numeroHistoriaMedica",paciente.getNroHistoria());
        valores.put("telefonoContacto1",paciente.getTlfContacto1());
        valores.put("telefonoContacto2",paciente.getTlfContacto2());
        String[] campo = {"1"};
        db.update("usuario",valores,"_id=?",campo);

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

    /*Insertar trtamientoen la BD utilizando los metodos de android*/
    public void insertarTratamiento(String fecha, String tratamiento)
    {
        ContentValues valores = new ContentValues();
        valores.put("fecha",fecha);
        valores.put("tipoTratamiento",tratamiento);
        db.insert("tratamiento",null,valores);

    }

    /*Consultar fecha tratamiento*/
    public boolean consultarFechaTratamiento(String fecha){
        String[] campos = {"_id","fecha"};
        String[] args = {fecha};
        Cursor c = db.query("tratamiento",campos,"fecha=?",args,null,null,null);
        if(c.moveToFirst()){
            return true;
        }
        return false;
    }

    /*Consultar tipo tratamiento devuelve 0 si es radioterapia y  1 si es quimioterapia */
    public int consultarTipoTratamiento(String fecha){
        String[] campos = {"_id","fecha","tipoTratamiento"};
        String[] args = {fecha};
        Cursor c = db.query("tratamiento",campos,"fecha=?",args,null,null,null);
        if(c.moveToFirst()){

                if (c.getString(2).equalsIgnoreCase("Radioterapia")) {
                    return 0;
                }

        }
        return 1;
    }

    /*Consultar tratamiento completo */
    public ArrayList<Treatment> pedirTratamientosCompleto(){
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        String[] campos = {"_id","fecha","tipoTratamiento"};
        Cursor c = db.query("tratamiento",campos,null,null,null,null,null);
        ArrayList<Treatment> tratamientos = new ArrayList<Treatment>();

        Treatment tratamientoInd = new Treatment();
        if(c.moveToFirst()){

            do{
                tratamientoInd = new Treatment(c.getInt(0),c.getString(1),c.getString(2));
                tratamientos.add(tratamientoInd);
            }while(c.moveToNext());

        }

        return tratamientos;
    }

    /*Consultar fecha tratamiento*/
    public ArrayList<String> pedirFechaTratamiento(){
        ArrayList<String> fechas_tratamientos = new ArrayList<String>();
        String[] campos = {"_id","fecha"};
        Cursor c = db.query("tratamiento",campos,null,null,null,null,null);
        if(c.moveToFirst()){

            do{
                fechas_tratamientos.add(c.getString(1));
            }while(c.moveToNext());
        }
        return fechas_tratamientos;
    }

    /*Eliminar fecha de tratamiento*/
    public void eliminarFechaTratamiento(String fecha){
        String[] fechaAborrar = {fecha};
        db.delete("tratamiento","fecha=?",fechaAborrar);
    }

    /*Postergar fecha tratamiento*/
    public void porstergarFechaTratamiento(String fechaVieja, String fechaNueva){
        ContentValues args = new ContentValues();
        args.put("fecha",fechaNueva);
        String[] campo = {fechaVieja};
        db.update("tratamiento",args,"fecha=?",campo);

    }


    /*Insertar trtamientoen la BD utilizando los metodos de android*/
    public void insertarNota(String fecha, String asunto,String nota)
    {
        ContentValues valores = new ContentValues();
        valores.put("fecha",fecha);
        valores.put("titulo",asunto);
        valores.put("nota",nota);
        db.insert("nota",null,valores);

    }

    /*Consultar titulo notas*/
    public ArrayList<String> pedirTituloNota(){
        ArrayList<String> tituloTratamiento = new ArrayList<String>();
        String[] campos = {"_id","titulo"};
        Cursor c = db.query("nota",campos,null,null,null,null,null);
        if(c.moveToFirst()){

            do{
                tituloTratamiento.add(c.getString(1));
            }while(c.moveToNext());
            return tituloTratamiento;
        }
        tituloTratamiento.add("vacio");
        return tituloTratamiento;

    }

    /*Consultar titulo botas*/
    public HashMap<Integer,String> pedirTituloNotaConClave(){
        HashMap<Integer,String> misNotas = new HashMap<Integer,String>();
        ArrayList<String> tituloTratamiento = new ArrayList<String>();
        String[] campos = {"_id","titulo"};
        Cursor c = db.query("nota",campos,null,null,null,null,null);
        if(c.moveToFirst()){

            do{
                misNotas.put(c.getInt(0),c.getString(1));
                tituloTratamiento.add(c.getString(1));

            }while(c.moveToNext());
            return misNotas;
        }
        misNotas.put(-1,"vacio");
        return misNotas;

    }

    /*Consultar notas*/
    public ArrayList<String> pedirNotas(int id){
        HashMap<Integer,String> misNotas = new HashMap<Integer,String>();
        ArrayList<String> tituloTratamiento = new ArrayList<String>();
        String[] campos = {"_id","titulo","nota"};
        String[] campoAbuscar = {Integer.toString(id)};
        Cursor c = db.query("nota",campos,"_id=?",campoAbuscar,null,null,null);
        if(c.moveToFirst()){

            do{
                tituloTratamiento.add(Integer.toString(c.getInt(0)));
                tituloTratamiento.add(c.getString(1));
                tituloTratamiento.add(c.getString(2));

            }while(c.moveToNext());
            return tituloTratamiento;
        }
        misNotas.put(0,"vacio");
        return tituloTratamiento;

    }


    /*Actualizar nota*/
    public void actualizarNota(Integer id, String tituloNuevo, String cuerpoNuevo, String fechaNueva){
        ContentValues args = new ContentValues();
        args.put("fecha",fechaNueva);
        args.put("titulo",tituloNuevo);
        args.put("nota",cuerpoNuevo);

        String[] campo = {Integer.toString(id)};
        db.update("nota",args,"_id=?",campo);

    }


    /* Insertar nueva Hematologia
     */
    public void insertarHematologia(Examen.Hematologia hematologia)
    {
        ContentValues valores = new ContentValues();
        valores.put("fecha",formatter.format(hematologia.getFechaExamen()));
        valores.put("fibrinogeno",hematologia.getFibrinogeno());
        valores.put("leucocitos",hematologia.getFibrinogeno());
        valores.put("hemoglobina",hematologia.getHemoglobina());
        valores.put("hematocrito",hematologia.getHematocrito());
        valores.put("plaqueta",hematologia.getPlaquetas());
        valores.put("vsg",hematologia.getVsg());
        valores.put("hcm",hematologia.getVsg());

        db.insert("hematologia",null,valores);

    }

    /*
    * */
    public ArrayList<Examen.Hematologia> consultarHematologia(){
        ArrayList<Examen.Hematologia> hematologia = new ArrayList<Examen.Hematologia>();
        String[] campos = {"_id","fecha","fibrinogeno","leucocitos","hemoglobina","hematocrito","plaqueta","vsg","hcm"};
        Cursor c = db.query("hematologia",campos,null,null,null,null,null);
        Calendar calendar = Calendar.getInstance();
        if(c.moveToFirst()){

            do{
                try {
                    calendar.setTime(formatter.parse(c.getString(1)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Examen.Hematologia hematologiaInd = null;
                try {
                    hematologiaInd = new Examen().new Hematologia(formatter.parse(c.getString(1)),c.getFloat(2),c.getFloat(3),c.getFloat(4),c.getFloat(5),c.getFloat(6),c.getFloat(7),c.getFloat(8));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                hematologiaInd.setIdExamen(c.getInt(0));
                hematologia.add(hematologiaInd);
            }while(c.moveToNext());
            return hematologia;
        }

        Examen.Hematologia  hematologiaInd = new Examen().new Hematologia(calendar.getTime(),2,3,4,5,6,7,8);

        hematologia.add(hematologiaInd);
        return hematologia;

    }

    public Examen.Hematologia consultarHematologiaEspecifica(int id){
        ArrayList<Examen.Hematologia> hematologia = new ArrayList<Examen.Hematologia>();
        Examen.Hematologia hematologiaInd;
        String[] campos = {"_id","fecha","fibrinogeno","leucocitos","hemoglobina","hematocrito","plaqueta","vsg","hcm"};
        String[] args = {Integer.toString(id)};
        Cursor c = db.query("hematologia",campos,"_id=?",args,null,null,null);
        Calendar calendar = Calendar.getInstance();
        if(c.moveToFirst()){

            do{
                try {
                    calendar.setTime(formatter.parse(c.getString(1)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                hematologiaInd = new Examen().new Hematologia(calendar.getTime(),c.getFloat(2),c.getFloat(3),c.getFloat(4),c.getFloat(5),c.getFloat(6),c.getFloat(7),c.getFloat(8));
                hematologiaInd.setIdExamen(c.getInt(0));
            }while(c.moveToNext());
            return hematologiaInd ;
        }



        return null;

    }

    public void actualizarHematologia(Examen.Hematologia hematologia,int id)
    {
        ContentValues valores = new ContentValues();
        String[] args = {Integer.toString(id)};
        valores.put("fecha",formatter.format(hematologia.getFechaExamen()));
        valores.put("fibrinogeno",hematologia.getFibrinogeno());
        valores.put("leucocitos",hematologia.getFibrinogeno());
        valores.put("hemoglobina",hematologia.getHemoglobina());
        valores.put("hematocrito",hematologia.getHematocrito());
        valores.put("plaqueta",hematologia.getPlaquetas());
        valores.put("vsg",hematologia.getVsg());
        valores.put("hcm",hematologia.getVsg());

        db.update("hematologia",valores,"_id=?",args);

    }
    public void eliminarHematologia(int id)
    {
        String[] args = {Integer.toString(id)};
        db.delete("hematologia","_id=?",args);

    }


}
