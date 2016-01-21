package com.example.jesus.fundahog;

/**
 * Created by Jesus on 1/16/2016.
 */
public class HistoriaPaciente {
    private String condicion;
    private String tipoSangre;
    private String estatura;
    private String peso;

    public HistoriaPaciente() {
    }

    public HistoriaPaciente(String condicion, String tipoSangre, String estatura, String peso) {
        this.condicion = condicion;
        this.tipoSangre = tipoSangre;
        this.estatura = estatura;
        this.peso = peso;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getEstatura() {
        return estatura;
    }

    public void setEstatura(String estatura) {
        this.estatura = estatura;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }
}
