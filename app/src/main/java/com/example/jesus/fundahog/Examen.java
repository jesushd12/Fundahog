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


    public class QuimicaSanguinea extends Examen{
        private float acidoUrico;
        private float amilasa;
        private float bilirrubina;
        private float calcio;
        private float ck;
        private float ckmb;
        private float colesterol;
        private float creatinina;
        private float electrolitos;
        private float fostasa;
        private float glicemia;
        private float hdlColesterol;
        private float ldlColesterol;
        private float ldh;
        private float hierro;
        private float fosforo;
        private float nitrogenoureico;
        private float proteinastotales;
        private float trigiliceridos;
        private float tgo;
        private float tgp;

        public QuimicaSanguinea() {
        }

        public QuimicaSanguinea(Date fechaExamen, float acidoUrico, float amilasa, float bilirrubina, float calcio, float ck, float ckmb, float colesterol, float creatinina, float electrolitos, float fostasa, float glicemia, float hdlColesterol, float ldlColesterol, float ldh, float hierro, float fosforo, float nitrogenoureico, float proteinastotales, float trigiliceridos, float tgo, float tgp) {
            super(fechaExamen);
            this.acidoUrico = acidoUrico;
            this.amilasa = amilasa;
            this.bilirrubina = bilirrubina;
            this.calcio = calcio;
            this.ck = ck;
            this.ckmb = ckmb;
            this.colesterol = colesterol;
            this.creatinina = creatinina;
            this.electrolitos = electrolitos;
            this.fostasa = fostasa;
            this.glicemia = glicemia;
            this.hdlColesterol = hdlColesterol;
            this.ldlColesterol = ldlColesterol;
            this.ldh = ldh;
            this.hierro = hierro;
            this.fosforo = fosforo;
            this.nitrogenoureico = nitrogenoureico;
            this.proteinastotales = proteinastotales;
            this.trigiliceridos = trigiliceridos;
            this.tgo = tgo;
            this.tgp = tgp;
        }
    }
}
