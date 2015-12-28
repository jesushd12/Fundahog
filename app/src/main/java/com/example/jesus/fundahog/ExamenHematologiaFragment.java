package com.example.jesus.fundahog;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Jesus on 12/28/2015.
 * Se extraen los valores introducidos por el usuario y se guardan en la base de datos.
 */
public class ExamenHematologiaFragment extends Fragment {
    EditText fecha;
    EditText fibrinogeno;
    EditText leucocitos;
    EditText hemoglobina;
    EditText hematocrito;
    EditText plaquetas;
    EditText vsg;
    EditText hcm;
    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
    DatePickerDialog fechaExamen;
    Calendar c ;
    DataBaseManager DB;
    Examen.Hematologia examenHematologicoCompleto;
    int idExamen =-1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hematologia,container,false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Hematologia completa");
        DB = new DataBaseManager(getActivity());
        fecha = (EditText)v.findViewById(R.id.edt_fechaHematologia);
        fibrinogeno = (EditText)v.findViewById(R.id.edt_fibrinogeno);
        leucocitos = (EditText)v.findViewById(R.id.edt_leucocitos);
        hemoglobina = (EditText)v.findViewById(R.id.edt_hemoglobina);
        hematocrito = (EditText)v.findViewById(R.id.edt_hematocrito);
        plaquetas = (EditText)v.findViewById(R.id.edt_plaquetas);
        vsg = (EditText)v.findViewById(R.id.edt_vsg);
        hcm = (EditText)v.findViewById(R.id.edt_hcm);
        idExamen = getArguments().getInt("id");

        if(idExamen != -1){
            examenHematologicoCompleto=DB.consultarHematologiaEspecifica(getArguments().getInt("id"));
            fecha.setText(formato.format(examenHematologicoCompleto.getFechaExamen()));
            fecha.setEnabled(false);
            fibrinogeno.setText(String.valueOf(examenHematologicoCompleto.getFibrinogeno()));
            fibrinogeno.setEnabled(false);
            leucocitos.setText(String.valueOf(examenHematologicoCompleto.getLeucocitos()));
            leucocitos.setEnabled(false);
            hemoglobina.setText(String.valueOf(examenHematologicoCompleto.getHemoglobina()));
            hemoglobina.setEnabled(false);
            hematocrito.setText(String.valueOf(examenHematologicoCompleto.getHematocrito()));
            hematocrito.setEnabled(false);
            plaquetas.setText(String.valueOf(examenHematologicoCompleto.getPlaquetas()));
            plaquetas.setEnabled(false);
            vsg.setText(String.valueOf(examenHematologicoCompleto.getVsg()));
            vsg.setEnabled(false);
            hcm.setText(String.valueOf(examenHematologicoCompleto.getHcm()));
            hcm.setEnabled(false);
            Toast.makeText(getActivity(),"QUI ENTRE",Toast.LENGTH_SHORT);

        }
        fecha.setInputType(InputType.TYPE_NULL);
        fecha.requestFocus();
        fechaExamen = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                c = Calendar.getInstance();
                c.set(year,monthOfYear,dayOfMonth);
                fecha.setText(formato.format(c.getTime()));
            }
        },Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fechaExamen.show();
            }
        });
        setHasOptionsMenu(true);

        return v;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save) {
            Examen.Hematologia hematologia;
            if(idExamen!=-1){

                try {
                    hematologia = new Examen().new Hematologia(formato.parse(fecha.getText().toString()), Float.parseFloat(fibrinogeno.getText().toString()), Float.parseFloat(leucocitos.getText().toString()), Float.parseFloat(hemoglobina.getText().toString()), Float.parseFloat(hematocrito.getText().toString()), Float.parseFloat(plaquetas.getText().toString()), Float.parseFloat(vsg.getText().toString()), Float.parseFloat(hcm.getText().toString()));
                    DB.actualizarHematologia(hematologia,idExamen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                getActivity().onBackPressed();
                return true;

            }else {
                hematologia = new Examen().new Hematologia(c.getTime(), Float.parseFloat(fibrinogeno.getText().toString()), Float.parseFloat(leucocitos.getText().toString()), Float.parseFloat(hemoglobina.getText().toString()), Float.parseFloat(hematocrito.getText().toString()), Float.parseFloat(plaquetas.getText().toString()), Float.parseFloat(vsg.getText().toString()), Float.parseFloat(hcm.getText().toString()));
                DB.insertarHematologia(hematologia);
                getActivity().onBackPressed();
            }

            return true;
        }

            if (id == R.id.action_edit) {
                /*Examen.Hematologia hematologia;
                try {
                    hematologia = new Examen().new Hematologia(formato.parse(fecha.getText().toString()), Float.parseFloat(fibrinogeno.getText().toString()), Float.parseFloat(leucocitos.getText().toString()), Float.parseFloat(hemoglobina.getText().toString()), Float.parseFloat(hematocrito.getText().toString()), Float.parseFloat(plaquetas.getText().toString()), Float.parseFloat(vsg.getText().toString()), Float.parseFloat(hcm.getText().toString()));
                    DB.actualizarHematologia(hematologia,idExamen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                getActivity().onBackPressed();*/
                fecha.setEnabled(true);
                fibrinogeno.setEnabled(true);
                leucocitos.setEnabled(true);
                hematocrito.setEnabled(true);
                hemoglobina.setEnabled(true);
                plaquetas.setEnabled(true);
                vsg.setEnabled(true);
                hcm.setEnabled(true);
                return true;
            }
        if (id == R.id.action_delete) {
            DB.eliminarHematologia(idExamen);
            getActivity().onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.removeItem(R.id.action_settings);
        inflater.inflate(R.menu.examen_main, menu);
        if(idExamen==-1) {
            menu.removeItem(R.id.action_edit);
            menu.removeItem(R.id.action_delete);
        }

    }
}
