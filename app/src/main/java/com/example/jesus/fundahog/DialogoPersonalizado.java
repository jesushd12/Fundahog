package com.example.jesus.fundahog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jesus on 1/18/2016.
 */

public class DialogoPersonalizado extends DialogFragment {

    EditText entradaUsuario;
    public Button agregar;
    Button cancelar;
    DataBaseManager DB;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.input_dialog, container);
        entradaUsuario = (EditText) v.findViewById(R.id.edt_Dinput_usuario);
        agregar = (Button)v.findViewById(R.id.boton_agregar);
        cancelar= (Button)v.findViewById(R.id.boton_cancelar);
        DB = new DataBaseManager(getContext());

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(entradaUsuario.getText().toString().equals("") ){
                    Toast.makeText(getContext(), "Por favor introduzca su alergia", Toast.LENGTH_SHORT);
                    return;
                }
                Alergia alergia;
                alergia = new Alergia();
                alergia.setNombrealergia(entradaUsuario.getText().toString());
                if (DB.consultarAlergiaEspecifica(alergia.getNombrealergia())) {
                    Toast.makeText(getContext(), "Ya esta registrada esta alergia", Toast.LENGTH_SHORT).show();
                    return;
                }
                DB.insertarAlergia(alergia);
                DialogoPersonalizado.this.getDialog().dismiss();
                //Actualizo la lista
                AlergyFragment.misAlergias = DB.consultarAlergias();
                ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, AlergyFragment.misAlergias) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);
                        TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                        text1.setText(AlergyFragment.misAlergias.get(position).getNombrealergia());
                        return view;
                    }
                };
                AlergyFragment.listaAlergia.setAdapter(adapter);

            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogoPersonalizado.this.getDialog().dismiss();
            }
        });
        getDialog().setCanceledOnTouchOutside(true);
        return v;
    }
}