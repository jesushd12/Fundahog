package com.example.jesus.fundahog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Jesus on 1/19/2016.
 */
public class TreatmentInformation extends Fragment {
    EditText nombreTratamiento;
    EditText fechaTratamiento;
    EditText horaTratamiento;
    DataBaseManager DB;
    int idTratamiento;
    Button botonNotas;
    Button botonExamen;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.treatment_information,container,false);
        DB = new DataBaseManager(getContext());
        nombreTratamiento = (EditText)v.findViewById(R.id.edt_tratamientoEspecifico);
        nombreTratamiento.setEnabled(false);
        fechaTratamiento = (EditText)v.findViewById(R.id.edt_fechaTratamientoEspecifico);
        fechaTratamiento.setEnabled(false);
        horaTratamiento = (EditText)v.findViewById(R.id.edt_horaTratamientoEspecifico);
        horaTratamiento.setEnabled(false);

        idTratamiento = getArguments().getInt("id");
        Treatment tratamiento = DB.consultarTratamiento(idTratamiento);

        nombreTratamiento.setText(tratamiento.getTipo());
        fechaTratamiento.setText(tratamiento.getFecha());
        horaTratamiento.setText(tratamiento.getHora());

        botonNotas = (Button)v.findViewById(R.id.btn_notasTratamientoEspecifico);
        botonExamen = (Button)v.findViewById(R.id.btn_examenestratamientoespecifico);


        botonNotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putInt("idTratamiento",idTratamiento);
                FragmentManager fm = getFragmentManager();
                NoteFragment nota = new NoteFragment();
                nota.setArguments(args);
                fm.beginTransaction().replace(R.id.contain_frame,nota).addToBackStack( "tag" ).commit();
            }
        });







        return v;
    }
}
