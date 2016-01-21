package com.example.jesus.fundahog;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Jesus on 12/19/2015.
 */
public class NoteFragment2 extends Fragment {
    Button btn_guardar;
    DataBaseManager DB;
    EditText edt_titulo;
    EditText edt_cuerpoNota;
    private Integer idNota =-1;


    public NoteFragment2() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_note_2,container,false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Notas");
        setHasOptionsMenu(true);
        Note nota;
        DB = new DataBaseManager(getActivity());
        idNota = getArguments().getInt("id");
        idNota++;
        edt_titulo = (EditText) v.findViewById( R.id.edt_asunto);
        edt_cuerpoNota=(EditText) v.findViewById(R.id.edt_cuerpoNota);
        if(idNota != -1){
            nota = DB.pedirNotas(idNota);
            edt_titulo.setText(nota.getTitulo());
            edt_cuerpoNota.setText(nota.getNota());
        }
        //btn_guardar = (Button)v.findViewById(R.id.guardarNota);
       /* btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                if(!validarCampos()) {
                    Toast.makeText(getContext(),"Por favor completa todos los datos",Toast.LENGTH_SHORT).show();
                    return;
                }
                Note nota;
                nota = new Note();
                nota.setFecha(c.getTime());
                nota.setTitulo(edt_titulo.getText().toString());
                nota.setNota(edt_cuerpoNota.getText().toString());

                if(idNota != -1){
                    nota.setId(idNota);
                    DB.actualizarNota(nota);
                 }else{
                    DB.insertarNota(nota);
                }
               getActivity().onBackPressed();

            }
        });
        */
        return v;
    }

    public boolean validarCampos(){
        if(edt_titulo.getText().toString().equals("") || edt_cuerpoNota.getText().toString().equals(""))
            return false;
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save) {
            Calendar c = Calendar.getInstance();
            if(!validarCampos()) {
                Toast.makeText(getContext(),"Por favor completa todos los datos",Toast.LENGTH_SHORT).show();
                return false;
            }
            Note nota;
            nota = new Note();
            nota.setFecha(c.getTime());
            nota.setTitulo(edt_titulo.getText().toString());
            nota.setNota(edt_cuerpoNota.getText().toString());
            Bundle bundle = getArguments();
            if(bundle!=null){
                if(bundle.containsKey("idTratamiento")){
                    nota.setIdExamenAsociado(bundle.getInt("idTratamiento"));
                }
            }

            if(idNota != -1){
                nota.setId(idNota);
                DB.actualizarNota(nota);
            }else{
                DB.insertarNota(nota);
            }
            getActivity().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.removeItem(R.id.action_settings);
        inflater.inflate(R.menu.examen_main, menu);
        //menu.removeItem(R.id.action_edit);
        menu.removeItem(R.id.action_delete);

    }
}
