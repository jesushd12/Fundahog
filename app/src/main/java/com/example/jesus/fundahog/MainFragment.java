package com.example.jesus.fundahog;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by Jesus on 12/17/2015.
 */
public class MainFragment extends Fragment{
    ImageButton botonTratamiento ;
    ImageButton botonNotas;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main,container,false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Principal");


        botonTratamiento = (ImageButton)v.findViewById(R.id.treatment_button);
        botonTratamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.contain_frame,new TreatmentFragment()).addToBackStack( "tag" ).commit();
            }
        });
        botonNotas = (ImageButton)v.findViewById(R.id.boton_notas);
        botonNotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().replace(R.id.contain_frame,new NoteFragment()).addToBackStack( "tag" ).commit();
            }
        });

        return v;
    }

}
