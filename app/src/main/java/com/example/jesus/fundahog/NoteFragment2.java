package com.example.jesus.fundahog;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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
    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
    private Integer idNota =-1;



    public NoteFragment2() {




    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_note_2,container,false);
        ArrayList<String> notaCompleta = new ArrayList<String>();
        DB = new DataBaseManager(getActivity());
        idNota = getArguments().getInt("id");
        idNota++;
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Notas");
        edt_titulo = (EditText) v.findViewById( R.id.edt_asunto);
        edt_cuerpoNota=(EditText) v.findViewById(R.id.edt_cuerpoNota);
        if(idNota != -1){
            notaCompleta=DB.pedirNotas(idNota);
            edt_titulo.setText(notaCompleta.get(1));
            edt_cuerpoNota.setText(notaCompleta.get(2));
        }
        btn_guardar = (Button)v.findViewById(R.id.guardarNota);
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                if(idNota != -1){
                    DB.actualizarNota(idNota,edt_titulo.getText().toString(),edt_cuerpoNota.getText().toString(),formato.format(c.getTime()));
                }else{
                    DB.insertarNota(formato.format(c.getTime()),edt_titulo.getText().toString(),edt_cuerpoNota.getText().toString());
                }
               getActivity().onBackPressed();

            }
        });
        return v;
    }




}
