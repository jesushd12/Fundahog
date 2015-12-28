package com.example.jesus.fundahog;

import java.util.Date;

/**
 * Created by Jesus on 12/27/2015.
 * Clase Examen, esta clase sera la clase padre de todos los tipos de examenes que se agregen.
 */
public class Examen {
    private int idExamen;
    private Date fechaExamen;

    public Examen(Date fechaExamen) {
        this.fechaExamen = fechaExamen;
    }

    public Examen() {
    }

    public Date getFechaExamen() {
        return fechaExamen;
    }

    public void setFechaExamen(Date fechaExamen) {
        this.fechaExamen = fechaExamen;
    }

    public int getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(int idExamen) {
        this.idExamen = idExamen;
    }

    /*Tipo de examen Hematologia*/
    public class Hematologia extends Examen{
        private float fibrinogeno;
        private float leucocitos;
        private float hemoglobina;
        private float hematocrito;
        private float plaquetas;
        private float vsg;
        private float hcm;


        public Hematologia(Date fechaExamen, float fibrinogeno, float leucocitos, float hemoglobina, float hematocrito, float plaquetas, float vsg, float hcm) {
            super(fechaExamen);
            this.fibrinogeno = fibrinogeno;
            this.leucocitos = leucocitos;
            this.hemoglobina = hemoglobina;
            this.hematocrito = hematocrito;
            this.plaquetas = plaquetas;
            this.vsg = vsg;
            this.hcm = hcm;
        }


        public float getFibrinogeno() {
            return fibrinogeno;
        }

        public void setFibrinogeno(float fibrinogeno) {
            this.fibrinogeno = fibrinogeno;
        }

        public float getLeucocitos() {
            return leucocitos;
        }

        public void setLeucocitos(float leucocitos) {
            this.leucocitos = leucocitos;
        }

        public float getHemoglobina() {
            return hemoglobina;
        }

        public void setHemoglobina(float hemoglobina) {
            this.hemoglobina = hemoglobina;
        }

        public float getHematocrito() {
            return hematocrito;
        }

        public void setHematocrito(float hematocrito) {
            this.hematocrito = hematocrito;
        }

        public float getPlaquetas() {
            return plaquetas;
        }

        public void setPlaquetas(float plaquetas) {
            this.plaquetas = plaquetas;
        }

        public float getVsg() {
            return vsg;
        }

        public void setVsg(float vsg) {
            this.vsg = vsg;
        }

        public float getHcm() {
            return hcm;
        }

        public void setHcm(float hcm) {
            this.hcm = hcm;
        }

        @Override
        public Date getFechaExamen() {
            return super.getFechaExamen();
        }

        @Override
        public void setFechaExamen(Date fechaExamen) {
            super.setFechaExamen(fechaExamen);
        }

        @Override
        public int getIdExamen() {
            return super.getIdExamen();
        }

        @Override
        public void setIdExamen(int idExamen) {
            super.setIdExamen(idExamen);
        }
    }
    /*Fin tipo examen Hematologia*/

}
