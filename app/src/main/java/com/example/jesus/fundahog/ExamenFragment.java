package com.example.jesus.fundahog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jesus on 12/27/2015.
 * Aqui se extraeran todos los examenes en un ArrayList de tipo examen y se colocaran en la lista
 */
public class ExamenFragment extends Fragment {
    ListView listView_titulos;
    ArrayList<String> asuntos;
    ArrayList<Examen.Hematologia> hematologias;
    ArrayList<Examen> examenArrayList = new ArrayList<Examen>();
    DataBaseManager DB;
    ArrayList<String> tipoExamen;
    final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_note,container,false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Examenes");
        DB = new DataBaseManager(getActivity());
        hematologias = DB.consultarHematologia();
        examenArrayList.addAll(hematologias);

        asuntos = new ArrayList<String>();
        for(int i=0;i<hematologias.size();i++){
            asuntos.add("Hematologia   "+formatter.format(hematologias.get(i).getFechaExamen()));
        }

        listView_titulos = (ListView)v.findViewById(R.id.listView_notas);
        listView_titulos.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, asuntos));
        listView_titulos.setItemsCanFocus(true);
        listView_titulos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(examenArrayList.get(position) instanceof Examen.Hematologia){
                    Toast.makeText(getActivity(),"Es hematologia",Toast.LENGTH_SHORT);
                   System.out.println("AAAAAAAAAA"+((Examen.Hematologia)examenArrayList.get(position)).getIdExamen());
                    Bundle args = new Bundle();
                    args.putInt("id",((Examen.Hematologia)examenArrayList.get(position)).getIdExamen());
                    FragmentManager fm = getFragmentManager();
                    ExamenHematologiaFragment examenHematologico = new ExamenHematologiaFragment();
                    examenHematologico.setArguments(args);
                    fm.beginTransaction().replace(R.id.contain_frame,examenHematologico).addToBackStack( "tag" ).commit();
                }

                System.out.println("AAAAAAAAAA NO"+((Examen.Hematologia)examenArrayList.get(position)).getIdExamen());





            }
        });
        setHasOptionsMenu(true);

        return v;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {

            FragmentManager fm = getFragmentManager();
            ExamenTypeFragment examenTipos = new ExamenTypeFragment();
            fm.beginTransaction().replace(R.id.contain_frame,examenTipos).addToBackStack( "tag" ).commit();




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
