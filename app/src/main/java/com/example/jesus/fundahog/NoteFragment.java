package com.example.jesus.fundahog;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
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
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TwoLineListItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.Inflater;

/**
 * Created by Jesus on 12/18/2015.
 */
public class NoteFragment extends Fragment {
    ListView listView_titulos;
    DataBaseManager DB;
    ArrayList<Note> todasLasNotas;
    Bundle bundle = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_note,container,false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Notas");

        DB = new DataBaseManager(getActivity());
        todasLasNotas =  DB.pedirTodasLasNotas();
        int idTratamiento;

        bundle = getArguments();
        if(bundle!=null){
            idTratamiento = bundle.getInt("idTratamiento");
            todasLasNotas=DB.pedirTodasLasNotasPorTratamiento(idTratamiento);
            if(todasLasNotas==null){
                Toast.makeText(getContext(),"No tiene ninguna nota asociada a este ciclo: "+idTratamiento,Toast.LENGTH_LONG).show();
            }

        }

        listView_titulos = (ListView)v.findViewById(R.id.listView_notas);
        if(todasLasNotas!=null) {
            ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_2, android.R.id.text1, todasLasNotas) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView text1 = (TextView) view.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) view.findViewById(android.R.id.text2);

                    text1.setText(todasLasNotas.get(position).getTitulo());
                    text2.setText(todasLasNotas.get(position).obtenerFechaFormateada());
                    return view;
                }
            };
            listView_titulos.setAdapter(adapter);

            listView_titulos.setItemsCanFocus(true);

            listView_titulos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Bundle args = new Bundle();
                    args.putInt("id", position);
                    FragmentManager fm = getFragmentManager();
                    NoteFragment2 nota = new NoteFragment2();
                    nota.setArguments(args);
                    fm.beginTransaction().replace(R.id.contain_frame, nota).addToBackStack("tag").commit();

                }
            });
            listView_titulos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    final String tituloNota = todasLasNotas.get(position).getTitulo();
                    final int idNota = position;
                    builder.setTitle(tituloNota);
                    builder.setMessage("Desea eliminar esta nota?");
                    // Add the buttons
                    builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User clicked OK button
                            DB.eliminarNotas(idNota);
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                            dialog.dismiss();
                        }
                    });
                    // Create the AlertDialog
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return false;
                }
            });
        }

        setHasOptionsMenu(true);
        return v;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {
            Bundle args = new Bundle();
            args.putInt("id",-2);
            if(bundle!=null){
                args.putInt("idTratamiento",bundle.getInt("idTratamiento"));
            }
            FragmentManager fm = getFragmentManager();
            NoteFragment2 nota = new NoteFragment2();
            nota.setArguments(args);
            fm.beginTransaction().replace(R.id.contain_frame,nota).addToBackStack( "tag" ).commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.removeItem(R.id.action_settings);
        inflater.inflate(R.menu.treatment_main, menu);
    }


}
