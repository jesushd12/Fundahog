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
            + "email " + " varchar (25) not null, "
            + "ubicacion " + " varchar (25) not null, "
            + "telefonoContacto1 " + " varchar (25) not null, "
            + "telefonoContacto2 " + " varchar (25)  );" ;

    public static final  String TABLA_TRATAMIENTO = " create table tratamiento ( "
            + "_id " + "  integer primary key autoincrement,  "
            + "fecha " + " date not null, "
            + "hora " + " varchar (25) , "
            + "tipoTratamiento " + " varchar (25)  );" ;

    public static final  String TABLA_NOTAS = " create table nota ( "
            + "_id " + "  integer primary key autoincrement,  "
            + "fecha " + " date not null, "
            + "titulo " + " varchar (25) not null, "
            + "examenAsociado " + " integer, "
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
            + "examenAsociado " + " integer, "
            + "hcm " + " real  );" ;

    /*Tabla de historia de usuario*/
    public static final  String TABLA_HISTORIA = " create table historia ( "
            + "_id " + "  integer primary key autoincrement,  "
            + "condicion " + " varchar (2) not null, "
            + "tiposangre " + " varchar (25) not null, "
            + "peso " + " varchar (25) , "
            + "estatura " + " varchar (25)  );" ;

    /*Tabla de alergias*/
    public static final  String TABLA_ALERGIA = " create table alergia ( "
            + "_id " + "  integer primary key autoincrement,  "
            + "alergia " + " varchar (25) not null  );" ;

    /*Tabla de Medicamentos*/
    public static final  String TABLA_MEDICAMENTO = " create table alergias ( "
            + "_id " + "  integer primary key autoincrement,  "
            + "medicamento " + " varchar (25) not null  );" ;



    private  DataBaseHelper helper;
    private SQLiteDatabase db;
    /* Si la base de datos no existe se crea y se devuelve en modo escritura, y si
     * existe se devuelve en modo lectura */
    public DataBaseManager(Context context) {
        helper = new DataBaseHelper(context);
        db = helper.getWritableDatabase();
    }

    /*- - - - - - - - - - - - - - - - - -PACIENTE - - - - - - - - - - - - - - - - - - - - - - - - - */
    /*- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    /*Insertar usuario en la BD */

    public void insertarUsuario(Pacient paciente)
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
    /*- - - - - - - - - - - - - - - - - -FIN PACIENTE - - - - - - - - - - - - - - - - - - - - - - - - - */


    /*- - - - - - - - - - - - - - - - - -MEDICO - - - - - - - - - - - - - - - - - - - - - - - - - */
    /*- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    /*Insertar medico en la BD utilizando los metodos de android*/
    public void insertarMedico(Medico medico)
    {
        ContentValues valores = new ContentValues();
        valores.put("prefijo",medico.getPrefijo());
        valores.put("nombre",medico.getNombre());
        valores.put("apellido",medico.getApellido());
        valores.put("email",medico.getEmail());
        valores.put("ubicacion",medico.getUbicacion());
        valores.put("telefonoContacto1",medico.getNumeroTelefonoOpcion1());
        valores.put("telefonoContacto2",medico.getNumeroTelefonoOpcion2());
        db.insert("medico",null,valores);

    }

    /*Modificar datos Medico*/
    public void actualizarMedico(Medico medico){
        ContentValues valores = new ContentValues();
        valores.put("nombre",medico.getNombre());
        valores.put("apellido",medico.getApellido());
        valores.put("email",medico.getEmail());
        valores.put("telefonoContacto1",medico.getNumeroTelefonoOpcion1());
        valores.put("telefonoContacto2",medico.getNumeroTelefonoOpcion2());
        String[] campo = {"1"};
        System.out.println(medico.toString());
        db.update("medico",valores,"_id=?",campo);

    }

    /*Consultar datos medico*/
    public Medico consultarDatosMedico(){
        Medico medico;
        String[] campos = {"prefijo","nombre","apellido","email","ubicacion","telefonoContacto1","telefonoContacto2"};
        Cursor c = db.query("medico",campos,"_id=1",null,null,null,null);
        if(c.moveToFirst()){

            do{

                medico = new Medico(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(5),c.getString(6),c.getString(4));
            }while(c.moveToNext());
            return medico;
        }

        return null;

    }

    /*- - - - - - - - - - - - - - - - - - - - - -  -FIN MEDICO- - - - - - - - - - - - - - - - - - */


    /*- - - - - - - - - - - - - - - - - -TRATAMIENTOS - - - - - - - - - - - - - - - - - - - - - - - - - */
    /*- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

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


    /*Consultar tipo tratamiento devuelve 0 si es radioterapia y  1 si es quimioterapia */
    public Treatment consultarTratamiento(int id){
        Treatment tratamiento = null;
        String[] campos = {"_id","fecha","tipoTratamiento","hora"};
        String[] args = {Integer.toString(id)};
        Cursor c = db.query("tratamiento",campos,"_id=?",args,null,null,null);
        if(c.moveToFirst()){

            do{
                tratamiento = new Treatment(c.getInt(0),c.getString(1),c.getString(2));
                tratamiento.setHora(c.getString(3));
            }while(c.moveToNext());

        }
        return tratamiento;
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

    /*actualizarHora tratamiento*/
    public void actualizarHora(String fecha,String hora){
        ContentValues args = new ContentValues();
        args.put("hora",hora);
        String[] campo = {fecha};
        db.update("tratamiento",args,"fecha=?",campo);

    }


    /*- - - - - - - - - - - - - - - - - -FIN TRATAMIENTO - - - - - - - - - - - - - - - - - - - - - - - - - */


    /*- - - - - - - - - - - - - - - - - -NOTAS - - - - - - - - - - - - - - - - - - - - - - - - - */
    /*- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    /*Insertar nota*/

    public void insertarNota(Note nota){
        ContentValues valores = new ContentValues();
        valores.put("fecha",nota.obtenerFechaFormateada());
        valores.put("titulo",nota.getTitulo());
        valores.put("nota",nota.getNota());
        if(nota.getIdExamenAsociado()!=-1){
            valores.put("examenAsociado",nota.getIdExamenAsociado());
        }
        db.insert("nota",null,valores);
    }


    /*Obtener todas las notas*/
    public ArrayList<Note> pedirTodasLasNotas(){
        ArrayList<Note> todasLasNotas = new ArrayList<>();
        Note nota;
        HashMap<Integer,String> misNotas = new HashMap<Integer,String>();
        ArrayList<String> tituloTratamiento = new ArrayList<String>();
        String[] campos = {"_id","titulo","fecha"};
        Cursor c = db.query("nota",campos,null,null,null,null,null);
        if(c.moveToFirst()){

            do{
                nota = new Note();
                nota.setId(c.getInt(0));
                nota.setTitulo(c.getString(1));
                nota.setFechaFormateada(c.getString(2));
                todasLasNotas.add(nota);
            }while(c.moveToNext());
            return todasLasNotas;
        }

        return todasLasNotas;

    }


    /*Obtener todas las notas*/
    public ArrayList<Note> pedirTodasLasNotasPorTratamiento(int id){
        ArrayList<Note> todasLasNotas = new ArrayList<>();
        Note nota;
        HashMap<Integer,String> misNotas = new HashMap<Integer,String>();
        ArrayList<String> tituloTratamiento = new ArrayList<String>();
        String[] campos = {"_id","titulo","fecha"};
        String[] argumentos = {Integer.toString(id)};
        Cursor c = db.query("nota",campos,"examenAsociado=?",argumentos,null,null,null);
        if(c.moveToFirst()){
            do{
                nota = new Note();
                nota.setId(c.getInt(0));
                nota.setTitulo(c.getString(1));
                nota.setFechaFormateada(c.getString(2));
                todasLasNotas.add(nota);
            }while(c.moveToNext());
            return todasLasNotas;
        }
        return null;
    }

    /*Consultar notas individual*/
    public Note pedirNotas(int id){
        Note nota = new Note();
        String[] campos = {"_id","titulo","nota"};
        String[] campoAbuscar = {Integer.toString(id)};
        Cursor c = db.query("nota",campos,"_id=?",campoAbuscar,null,null,null);
        if(c.moveToFirst()){
            do{
                nota.setId(c.getInt(0));
                nota.setTitulo(c.getString(1));
                nota.setNota(c.getString(2));
            }while(c.moveToNext());
            return nota;
        }
        nota = null;
        return nota;
    }


    /*Actualizar nota*/

    public void actualizarNota(Note nota){
        ContentValues args = new ContentValues();
        args.put("fecha",nota.obtenerFechaFormateada());
        args.put("titulo",nota.getTitulo());
        args.put("nota",nota.getNota());
        String[] campo = {Integer.toString(nota.getId())};
        db.update("nota",args,"_id=?",campo);
    }



    /*Eliminar fecha de tratamiento*/
    public void eliminarNotas(int id ){
        String[] campos = {Integer.toString(id)};
        db.delete("nota","_id=?",campos);
    }
    /*- - - - - - - - - - - - - - - - - -FIN NOTAS - - - - - - - - - - - - - - - - - - - - - - - - - */



    /*- - - - - - - - - - - - - - - - - -EXAMENES - - - - - - - - - - - - - - - - - - - - - - - - - */
    /*- - - - - - - - - - - - - - - - -HEMATOLOGIA- - - - - - - - - - - - - - - - - - - - - - - - */

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
        ArrayList<Examen.Hematologia> hematologia = new ArrayList<>();
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

    /*- - - - - - - - - - - - - - - - - -FIN HEMATOLOGIA - - - - - - - - - - - - - - - - - - - - - - - - - */


    /*- - - - - - - - - - - - - - - - - - FIN EXAMEN - - - - - - - - - - - - - - - - - - - */


    /*- - - - - - - - - - - - - - - - - -HISTORIA - - - - - - - - - - - - - - - - - - - - - - - - - */
    /*- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    /*Insertar nota*/

    public void insertarHistoria(HistoriaPaciente historiaPaciente){
        ContentValues valores = new ContentValues();
        valores.put("condicion",historiaPaciente.getCondicion());
        valores.put("tiposangre",historiaPaciente.getTipoSangre());
        valores.put("peso",historiaPaciente.getPeso());
        valores.put("estatura",historiaPaciente.getEstatura());
        db.insert("historia",null,valores);
    }

    public HistoriaPaciente consultarHistoria(){
        HistoriaPaciente historia;
        String[] campos = {"condicion","tiposangre","estatura","peso"};
        Cursor c = db.query("historia",campos,"_id=1",null,null,null,null);
        if(c.moveToFirst()){
            do{
                historia = new HistoriaPaciente();
                historia.setCondicion(c.getString(0));
                historia.setTipoSangre(c.getString(1));
                historia.setEstatura(c.getString(2));
                historia.setPeso(c.getString(3));

            }while(c.moveToNext());
            return historia;
        }

        return null;
    }

    /*Modificar historia*/
    public void actualizarHistoria(HistoriaPaciente historia){
        ContentValues valores = new ContentValues();
        valores.put("condicion",historia.getCondicion());
        valores.put("tiposangre",historia.getTipoSangre());
        valores.put("estatura",historia.getEstatura());
        valores.put("peso",historia.getPeso());
        String[] campo = {"1"};
        db.update("historia",valores,"_id=?",campo);

    }


    /*- - - - - - - - - - - - - - - - - -ALERGIA - - - - - - - - - - - - - - - - - - - - - - - - - */
    /*- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
    /*Insertar nota*/

    public void insertarAlergia(Alergia alergia){
        ContentValues valores = new ContentValues();
        valores.put("alergia",alergia.getNombrealergia());
        db.insert("alergia",null,valores);
    }

    public ArrayList<Alergia> consultarAlergias(){
        ArrayList<Alergia> alergias = new ArrayList<>();

        String[] campos = {"_id","alergia"};
        Cursor c = db.query("alergia",campos,null,null,null,null,null);
        if(c.moveToFirst()){
            do{
                Alergia alergia;
                alergia = new Alergia(c.getString(1),c.getInt(0));
                alergias.add(alergia);

            }while(c.moveToNext());
            return alergias;
        }
        return alergias;
    }

    public boolean consultarAlergiaEspecifica(String alergia){

        String[] campos = {alergia};
        Cursor c = db.query("alergia",null,"alergia=?",campos,null,null,null);
        if(c.moveToFirst()){
            do{
               return true;

            }while(c.moveToNext());
        }
        return false;
    }

    /*Eliminar fecha de tratamiento*/
    public void eliminarAlergia(String alergia){
        String[] alergiaBorrar = {alergia};
        db.delete("alergia","alergia=?",alergiaBorrar);
    }
}
