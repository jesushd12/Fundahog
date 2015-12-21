package com.example.jesus.fundahog;

import android.os.Bundle;
import android.provider.ContactsContract;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.Inflater;

/**
 * Created by Jesus on 12/18/2015.
 */
public class NoteFragment extends Fragment {
    ListView listView_titulos;
    String[] data = {"Tengo hambre","Tengo ganas de dormir"};
    ArrayList<String> asuntos;
    DataBaseManager DB;
    HashMap<Integer,String> misNotas;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_note,container,false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Notas");
        DB = new DataBaseManager(getActivity());
        misNotas = new HashMap<Integer, String>();
        misNotas = DB.pedirTituloNotaConClave();
        asuntos = new ArrayList<String>();
        asuntos = DB.pedirTituloNota();

        listView_titulos = (ListView)v.findViewById(R.id.listView_notas);
        listView_titulos.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, asuntos));
        listView_titulos.setItemsCanFocus(true);
        listView_titulos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle args = new Bundle();
                args.putInt("id",position);
                FragmentManager fm = getFragmentManager();
                NoteFragment2 nota = new NoteFragment2();
                nota.setArguments(args);

                fm.beginTransaction().replace(R.id.contain_frame,nota).addToBackStack( "tag" ).commit();

            }
        });
        setHasOptionsMenu(true);
        return v;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {
            Bundle args = new Bundle();
            args.putInt("id",-2);
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
