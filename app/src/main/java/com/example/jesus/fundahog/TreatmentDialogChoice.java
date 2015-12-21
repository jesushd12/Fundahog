package com.example.jesus.fundahog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Jesus on 12/17/2015.
 */
public class TreatmentDialogChoice extends DialogFragment{

    final CharSequence[] tratamientos = {"Radioterapia","Quimioterapia"};
    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
    String selection;
    DataBaseManager db;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final String fechaSeleccionada = getArguments().getString("fecha");
        db = new DataBaseManager(getActivity());




        //Si existe un tratamiento para la fecha
        if(db.consultarFechaTratamiento(fechaSeleccionada)){

            builder.setTitle("Su tratamiento para el dia");
            builder.setSingleChoiceItems(tratamientos, db.consultarTipoTratamiento(fechaSeleccionada), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case 0:
                            selection = (String)tratamientos[which];
                            break;
                        case 1:
                            selection = (String)tratamientos[which];
                            break;
                    }

                }
            }).setPositiveButton("Posrtergar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Calendar c = Calendar.getInstance();
                    try {
                        c.setTime(formato.parse(fechaSeleccionada));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            Calendar nuevaFecha = Calendar.getInstance();
                            nuevaFecha.set(year,monthOfYear,dayOfMonth);
                            db.porstergarFechaTratamiento(fechaSeleccionada,formato.format(nuevaFecha.getTime()));


                        }
                    }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                    datePickerDialog.setTitle("Seleccione la nueva fecha");

                    datePickerDialog.show();

                }
            }).setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    db.eliminarFechaTratamiento(fechaSeleccionada);
                    FragmentManager fm = getFragmentManager();
                    fm.beginTransaction().replace(R.id.contain_frame,new TreatmentFragment()).commit();
                }
            });

        }else {
            builder.setTitle("Seleccione un tratamiento: ");
            builder.setSingleChoiceItems(tratamientos, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case 0:
                            selection = (String)tratamientos[which];
                            break;
                        case 1:
                            selection = (String)tratamientos[which];
                            break;
                    }

                }
            }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    if(selection.isEmpty()){
                        Toast.makeText(getActivity(),"Por favor selecciona un tratamiento", Toast.LENGTH_SHORT).show();
                        return;

                    }
                    if(!db.consultarFechaTratamiento(selection)){
                        db.insertarTratamiento(fechaSeleccionada,selection);
                        FragmentManager fm = getFragmentManager();
                        fm.beginTransaction().replace(R.id.contain_frame,new TreatmentFragment()).commit();
                    }else{
                        Toast.makeText(getActivity(),"Ya existe un tratamiento para este dia", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dismiss();
                }
            });
        }


        return builder.create();
    }


}
