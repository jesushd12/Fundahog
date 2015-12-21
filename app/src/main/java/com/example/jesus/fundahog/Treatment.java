package com.example.jesus.fundahog;

/**
 * Created by Jesus on 12/19/2015.
 */
public class Treatment {
    private int id;
    private String fecha;
    private String tipo;

    public Treatment(int id, String fecha, String tipo) {
        this.id = id;
        this.fecha = fecha;
        this.tipo = tipo;
    }

    public Treatment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
