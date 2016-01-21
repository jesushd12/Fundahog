package com.example.jesus.fundahog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Jesus on 1/16/2016.
 */
public class fragmentMedicalHistory extends Fragment{
    TextView nombrePaciente;
    TextView fechaNacimiento;
    EditText condicion;
    EditText tipoDeSangre;
    EditText estatura;
    EditText peso;
    ArrayList<Alergia> alergias;
    DataBaseManager DB;
    boolean flag;
    Button misAlergias;


    SimpleDateFormat formato2 = new SimpleDateFormat("MMMM dd, yyyy",new Locale("es","ES"));

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_historia, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Historia Medica");
        setHasOptionsMenu(true);
        flag = false;
        DB = new DataBaseManager(getContext());
        alergias = DB.consultarAlergias();
        nombrePaciente = (TextView) v.findViewById(R.id.edt_nombrePacienteH);
        fechaNacimiento = (TextView) v.findViewById(R.id.edt_fechaNacimientoH);
        Pacient paciente = DB.consultarDatosPaciente();
        nombrePaciente.setText(paciente.getNombre());
        fechaNacimiento.setText(formato2.format(paciente.getFechaNacimiento()) + " (" + paciente.obtenerEdad() + ")");


        condicion = (EditText) v.findViewById(R.id.edt_condicionMedica);
        tipoDeSangre = (EditText) v.findViewById(R.id.edt_tipoDeSangre);
        estatura = (EditText) v.findViewById(R.id.edt_estatura);
        peso = (EditText) v.findViewById(R.id.edt_peso);
        //agregarAlergia = (ImageButton) v.findViewById(R.id.btn_agregarAlergia);

        //guardarHistoria = (Button) v.findViewById(R.id.guardarHistoria);
        /*nuevaAlergia = (EditText) v.findViewById(R.id.edt_nuevaAlergia);
        agregarAlergia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alergia alergia = new Alergia();
                alergia.setNombrealergia(nuevaAlergia.getText().toString());
                if (DB.consultarAlergiaEspecifica(alergia.getNombrealergia())) {
                    Toast.makeText(getContext(), "Ya esta registrada esta alergia", Toast.LENGTH_SHORT).show();
                    return;
                }
                DB.insertarAlergia(alergia);
                alergias = DB.consultarAlergias();
                nuevaAlergia.setText("");
            }
        });*/
        if (DB.consultarHistoria() != null) {
            HistoriaPaciente historia;
            historia = DB.consultarHistoria();
            condicion.setText(historia.getCondicion());
            tipoDeSangre.setText(historia.getTipoSangre());
            estatura.setText(historia.getEstatura());
            peso.setText(historia.getPeso());
        }
        /*listaAlergias = (ListView) v.findViewById(R.id.listaAlergias);
        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, alergias) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                text1.setText(alergias.get(position).getNombrealergia());
                return view;
            }
        };
        listaAlergias.setAdapter(adapter);
        listaAlergias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                final String nombreAlergia = alergias.get(position).getNombrealergia();
                builder.setTitle(alergias.get(position).getNombrealergia());
                builder.setMessage("Desea eliminar esta alergia?");
                // Add the buttons
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        DB.eliminarAlergia(nombreAlergia);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
                // Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        */

       /* guardarHistoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistoriaPaciente historia;
                historia = new HistoriaPaciente();
                if(condicion.getText().toString().equalsIgnoreCase("")){
                    return;
                }
                historia.setCondicion(condicion.getText().toString());
                historia.setTipoSangre(tipoDeSangre.getText().toString());
                historia.setEstatura(estatura.getText().toString());
                historia.setPeso(peso.getText().toString());
                if (DB.consultarHistoria() != null) {
                    DB.actualizarHistoria(historia);
                    Toast.makeText(getContext(),"Su informacion ha sido guardada exitosamente",Toast.LENGTH_SHORT);
                    return;
                }
                DB.insertarHistoria(historia);
            }
        });
        */
        condicion.setEnabled(false);
        tipoDeSangre.setEnabled(false);
        estatura.setEnabled(false);
        peso.setEnabled(false);
//        agregarAlergia.setEnabled(false);
       // nuevaAlergia.setEnabled(false);
        //listaAlergias.setEnabled(false);

        misAlergias = (Button)v.findViewById(R.id.boton_alergico);
        misAlergias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                AlergyFragment alergy = new AlergyFragment();
                fm.beginTransaction().replace(R.id.contain_frame,alergy).addToBackStack( "tag" ).commit();
            }
        });


        return v;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save) {
            if(!flag){
                Toast.makeText(getContext(),"No ha hecho ningun cambio",Toast.LENGTH_SHORT).show();
                return false;
            }
            HistoriaPaciente historia;
            historia = new HistoriaPaciente();
            if(condicion.getText().toString().equalsIgnoreCase("")){
                return true;
            }
            historia.setCondicion(condicion.getText().toString());
            historia.setTipoSangre(tipoDeSangre.getText().toString());
            historia.setEstatura(estatura.getText().toString());
            historia.setPeso(peso.getText().toString());
            if (DB.consultarHistoria() != null) {
                DB.actualizarHistoria(historia);
                Toast.makeText(getContext(),"Su informacion ha sido guardada exitosamente",Toast.LENGTH_SHORT);
                return true;
            }
            DB.insertarHistoria(historia);



        }
        if (id == R.id.action_edit) {
            flag = true;
            condicion.setEnabled(true);
            tipoDeSangre.setEnabled(true);
           //agregarAlergia.setEnabled(true);
            estatura.setEnabled(true);
            peso.setEnabled(true);
            //nuevaAlergia.setEnabled(true);
            //listaAlergias.setEnabled(true);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.removeItem(R.id.action_settings);
        inflater.inflate(R.menu.examen_main, menu);
        menu.removeItem(R.id.action_delete);

    }


}
