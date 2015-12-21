package com.example.jesus.fundahog;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jesus on 12/21/2015.
 */
public class Pacient {
    private String nombre;
    private String apellido;
    private String cedula;
    private Date fechaNacimiento;
    private String lugarNacimiento;
    private String sexo;
    private String email;
    private String tlfContacto1;
    private String tlfContacto2;
    private String nroHistoria;
    private String contrasena;

    public Pacient(String nombre, String apellido, String cedula, Date fechaNacimiento, String lugarNacimiento, String sexo, String email, String tlfContacto1, String tlfContacto2, String nroHistoria, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.fechaNacimiento = fechaNacimiento;
        this.lugarNacimiento = lugarNacimiento;
        this.sexo = sexo;
        this.email = email;
        this.tlfContacto1 = tlfContacto1;
        this.tlfContacto2 = tlfContacto2;
        this.nroHistoria = nroHistoria;
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getLugarNacimiento() {
        return lugarNacimiento;
    }

    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTlfContacto1() {
        return tlfContacto1;
    }

    public void setTlfContacto1(String tlfContacto1) {
        this.tlfContacto1 = tlfContacto1;
    }

    public String getTlfContacto2() {
        return tlfContacto2;
    }

    public void setTlfContacto2(String tlfContacto2) {
        this.tlfContacto2 = tlfContacto2;
    }

    public String getNroHistoria() {
        return nroHistoria;
    }

    public void setNroHistoria(String nroHistoria) {
        this.nroHistoria = nroHistoria;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int obtenerEdad(){
        int edad;
        Calendar c = Calendar.getInstance();
        Calendar aux = Calendar.getInstance();
        aux.setTime(getFechaNacimiento());
        edad = c.YEAR - aux.YEAR;

        return edad;
    }
}
