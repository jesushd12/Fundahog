package com.example.jesus.fundahog;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by Jesus on 12/17/2015.
 */
public class TreatmentFragment extends Fragment {
    private CaldroidFragment caldroidFragment;
    private DataBaseManager DB;
    ArrayList<Treatment> tratamientos;
    ListView lista_tratamiento;
    SimpleDateFormat formatoAno = new SimpleDateFormat("yyyy");
    SimpleDateFormat formatoMes = new SimpleDateFormat("MM");


    public void setCustomResourceForDate(){
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");

        Calendar cal = Calendar.getInstance();

        try {
            cal.setTime(formato.parse("19-12-2015"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date blueDate = cal.getTime();


        cal = Calendar.getInstance();
        try {
            cal.setTime(formato.parse("20-12-2015"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date blueDate2 = cal.getTime();

        cal = Calendar.getInstance();
        try {
            cal.setTime(formato.parse("18-12-2015"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date blueDate3 = cal.getTime();



        ArrayList<String> fechasTratamientos = DB.pedirFechaTratamiento();
        if (caldroidFragment!=null){
            int i;
            for(i = 0;i<fechasTratamientos.size();i++){
                try {
                    if(DB.consultarTipoTratamiento(fechasTratamientos.get(i))==0)

                    caldroidFragment.setBackgroundResourceForDate(R.color.caldroid_holo_blue_light,formato.parse(fechasTratamientos.get(i)));
                    else
                        caldroidFragment.setBackgroundResourceForDate(R.color.md_green_500_50,formato.parse(fechasTratamientos.get(i)));
                    caldroidFragment.setTextColorForDate(R.color.caldroid_white,formato.parse(fechasTratamientos.get(i)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            };




        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_treatment,container,false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Tratamientos");
        //setHasOptionsMenu(true);
        DB = new DataBaseManager(getActivity());
        lista_tratamiento = (ListView)v.findViewById(R.id.listview_tratamientos);

        tratamientos = DB.pedirTratamientosCompleto();
        Collections.sort(tratamientos,new CustomComparator());
        final TreatmentAdapter treatmentAdapter = new TreatmentAdapter(getActivity(),tratamientos);
        lista_tratamiento.setAdapter(treatmentAdapter);

        lista_tratamiento.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle args = new Bundle();
                args.putInt("id",tratamientos.get(position).getId());
                FragmentManager fm = getFragmentManager();
                TreatmentInformation treatmentInformation = new TreatmentInformation();
                treatmentInformation.setArguments(args);
                fm.beginTransaction().replace(R.id.contain_frame,treatmentInformation).addToBackStack( "tag" ).commit();
            }
        });






        final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        caldroidFragment = new CaldroidFragment();
        if(savedInstanceState !=null){
            caldroidFragment.restoreStatesFromKey(savedInstanceState,"CALDROID_SAVED_STATE");

        }else {
            Bundle args = new Bundle();
            Calendar cal = Calendar.getInstance();
            args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
            args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
            args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
            args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, false);
            caldroidFragment.setArguments(args);
        }
        setCustomResourceForDate();

        FragmentTransaction t = this.getFragmentManager().beginTransaction();
        t.replace(R.id.caldroide,caldroidFragment);
        t.commit();

        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onChangeMonth(int month, int year) {
                super.onChangeMonth(month, year);


                /*
                System.out.println("mes: "+month+" year: "+year);
                tratamientos = DB.pedirTratamientosCompleto();
                ArrayList<Integer> posicionesABorrar = new ArrayList<>();
                for(int i = 0; i<tratamientos.size();i++){
                    if(( (month==Integer.parseInt(formatoMes.format(tratamientos.get(i).getDate()))) && year == Integer.parseInt(formatoAno.format(tratamientos.get(i).getDate())))  ) {
                        System.out.println("igualito");
                        System.out.println("mes: " + Integer.parseInt(formatoMes.format(tratamientos.get(i).getDate())) + " ano: " + Integer.parseInt(formatoAno.format(tratamientos.get(i).getDate())));
                    }

                     else{
                     posicionesABorrar.add(i);
                        //tratamientos.remove(i);
                    }
                }

                for(int aux = 0; aux<posicionesABorrar.size();aux++){
                    tratamientos.remove(posicionesABorrar.get(aux));
                }
                Collections.sort(tratamientos,new CustomComparator());
                final TreatmentAdapter treatmentAdapter = new TreatmentAdapter(getActivity(),tratamientos);
                lista_tratamiento.setAdapter(treatmentAdapter);
                */



            }

            @Override
            public void onSelectDate(Date date, View view) {
                TreatmentDialogChoice my_dialog = new TreatmentDialogChoice();
                Bundle args = new Bundle();
                args.putString("fecha",formatter.format(date));
                my_dialog.setArguments(args);

                my_dialog.show(getFragmentManager(),"my_dialog");

            }



            @Override
            public void onLongClickDate(Date date, View view) {
                super.onLongClickDate(date, view);
                TreatmentDialogChoice my_dialog = new TreatmentDialogChoice();
                Bundle args = new Bundle();
                args.putString("fecha",formatter.format(date));
                my_dialog.setArguments(args);

                my_dialog.show(getFragmentManager(),"my_dialog");


            }
        };
        caldroidFragment.setCaldroidListener(listener);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.treatment_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public class CustomComparator implements Comparator<Treatment> {
        @Override
        public int compare(Treatment o1, Treatment o2) {
            return o1.getFecha().compareTo(o2.getFecha());
        }
    }
}
