package com.example.jesus.fundahog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesus on 12/28/2015.
 * Para agregar otro tipo de examen, se agrega un nuevo elemento al arrayList de tipoExamen.
 * Luego se modifica las acciones pertinentes al hacer click sobre este
 * en la lista,segun la posicion de este en el arrayList
 */
public class ExamenTypeFragment extends Fragment {
    ArrayList<String> tipoExamen;
    ListView listaTipo;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_note,container,false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Seleccionar tipo de examen");
        tipoExamen = new ArrayList<String>();
        tipoExamen.add("Hematologia Completa");
        listaTipo = (ListView)v.findViewById(R.id.listView_notas);
        listaTipo.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, tipoExamen));
        listaTipo.setItemsCanFocus(true);
        listaTipo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Bundle args = new Bundle();
                        args.putInt("id",-1);
                        FragmentManager fm = getFragmentManager();
                        ExamenHematologiaFragment examenHematologico = new ExamenHematologiaFragment();
                        examenHematologico.setArguments(args);
                        fm.beginTransaction().replace(R.id.contain_frame,examenHematologico).addToBackStack( "tag" ).commit();

                        break;
                }


            }
        });

        return v;
    }
}
