package com.example.jesus.fundahog;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.zip.Inflater;

/**
 * Created by Jesus on 12/21/2015.
 */
public class PacientInformationFragment extends Fragment {
    private AutoCompleteTextView nombreUsuario;
    private EditText mPasswordView;
    Pacient paciente;
    Pacient pacienteNuevo;
    EditText nombre;
    EditText apellidoUsuario;
    EditText cedulaUsuario;
    EditText sexoUsuario;
    EditText fechaNacimientoUsuario;
    EditText lugarNacimientoUsuario;
    AutoCompleteTextView emailUsuario;
    EditText contrasenaUsuario;
    EditText tlfContactoUsuario;
    EditText tlfContactoUsuario2;
    EditText nroHistoriaUsuario;
    final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    DatePickerDialog.OnDateSetListener d;
    DatePickerDialog fechaExamen;
    Calendar c ;
    Calendar calendar = Calendar.getInstance();
    DataBaseManager DB;
    ViewGroup layout;
    Spinner sexo;
    boolean flag ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_login,container,false);


        DB = new DataBaseManager(getActivity());
        setHasOptionsMenu(true);
        paciente = DB.consultarDatosPaciente();
        flag = false;

        nombre = (EditText)v.findViewById(R.id.edt_nombre);

        nombre.setText(paciente.getNombre());
        apellidoUsuario = (EditText)v.findViewById(R.id.edt_apellido);
        apellidoUsuario.setText(paciente.getApellido());
        cedulaUsuario = (EditText)v.findViewById(R.id.edt_cedula);
        cedulaUsuario.setText(paciente.getCedula());
        sexo = (Spinner)v.findViewById(R.id.spin_sexo);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getContext(),R.array.sexo,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sexo.setAdapter(adapter);
        if(paciente.getSexo().equalsIgnoreCase("femenino") || paciente.getSexo().equalsIgnoreCase("f") )
            sexo.setSelection(0);
        if(paciente.getSexo().equalsIgnoreCase("masculino") || paciente.getSexo().equalsIgnoreCase("m") )
            sexo.setSelection(1);

        fechaNacimientoUsuario = (EditText)v.findViewById(R.id.edt_fechaNacimiento);
        fechaNacimientoUsuario.setText(formatter.format(paciente.getFechaNacimiento()));
        fechaNacimientoUsuario.setInputType(InputType.TYPE_NULL);
        fechaNacimientoUsuario.requestFocus();
        fechaExamen = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                c = Calendar.getInstance();
                c.set(year,monthOfYear,dayOfMonth);
                fechaNacimientoUsuario.setText(formatter.format(c.getTime()));
            }
        },Calendar.getInstance().get(Calendar.YEAR),Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        fechaNacimientoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fechaExamen.show();
            }
        });

        lugarNacimientoUsuario = (EditText)v.findViewById(R.id.edt_lugarNacimiento);
        lugarNacimientoUsuario.setText(paciente.getLugarNacimiento());
        tlfContactoUsuario = (EditText)v.findViewById(R.id.edt_tlfContacto);
        tlfContactoUsuario.setText(paciente.getTlfContacto1());
        tlfContactoUsuario2 = (EditText)v.findViewById(R.id.edt_tlfContacto2);
        tlfContactoUsuario2.setText(paciente.getTlfContacto2());
        nroHistoriaUsuario  = (EditText)v.findViewById(R.id.edt_nroHistoria);
        nroHistoriaUsuario.setText(paciente.getNroHistoria());

        nombreUsuario = (AutoCompleteTextView) v.findViewById(R.id.emailUsuario);
        nombreUsuario.setText(paciente.getEmail());
        mPasswordView = (EditText) v.findViewById(R.id.edt_password);
        mPasswordView.setText(paciente.getContrasena());
        Button mEmailSignInButton = (Button) v.findViewById(R.id.email_sign_in_button);
        layout = (ViewGroup)mEmailSignInButton.getParent();
        layout.removeView(mEmailSignInButton);

        nombre.setEnabled(false);
        apellidoUsuario.setEnabled(false);
        cedulaUsuario.setEnabled(false);
        sexo.setEnabled(false);
        fechaNacimientoUsuario.setEnabled(false);
        lugarNacimientoUsuario.setEnabled(false);
        tlfContactoUsuario.setEnabled(false);
        tlfContactoUsuario2.setEnabled(false);
        mPasswordView.setEnabled(false);
        nroHistoriaUsuario.setEnabled(false);
        nombreUsuario.setEnabled(false);


        //mEmailSignInButton.setText("Guardar");
        /*mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validarCampos()) {

                    Toast.makeText(getContext(),"Por favor completa todos los datos",Toast.LENGTH_SHORT).show();
                    return;
                }

                pacienteNuevo = new Pacient(nombre.getText().toString(),apellidoUsuario.getText().toString(),cedulaUsuario.getText().toString(),c.getTime(),lugarNacimientoUsuario.getText().toString(),sexo.getSelectedItem().toString(),nombreUsuario.getText().toString(),tlfContactoUsuario.getText().toString(),tlfContactoUsuario2.getText().toString(),nroHistoriaUsuario.getText().toString(),mPasswordView.getText().toString());
                DB.actualizarPaciente(pacienteNuevo);
                getActivity().onBackPressed();
            }
        });
        */


        return v;
    }

    public boolean validarCampos(){
        if(nombre.getText().toString().equals("") ||apellidoUsuario.getText().equals(""))
            return false;
        if(cedulaUsuario.getText().toString().equals(""))
            return false;
        if(fechaNacimientoUsuario.getText().toString().equals("") || lugarNacimientoUsuario.getText().equals(""))
            return false;
        if(tlfContactoUsuario.getText().toString().equals("") || tlfContactoUsuario2.getText().equals(""))
            return false;
        if(tlfContactoUsuario.getText().toString().equals("") || tlfContactoUsuario2.getText().equals(""))
            return false;
        if(nombreUsuario.getText().toString().equals("") || mPasswordView.getText().equals(""))
            return false;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save) {
            if(!flag){
                Toast.makeText(getContext(),"No ha hecho ningun cambio",Toast.LENGTH_SHORT).show();
                return false;
            }

            if(!validarCampos()) {
                Toast.makeText(getContext(),"Por favor completa todos los datos",Toast.LENGTH_SHORT).show();
                return false;
            }
            try {
                pacienteNuevo = new Pacient(nombre.getText().toString(),apellidoUsuario.getText().toString(),cedulaUsuario.getText().toString(),formatter.parse(fechaNacimientoUsuario.getText().toString()),lugarNacimientoUsuario.getText().toString(),sexo.getSelectedItem().toString(),nombreUsuario.getText().toString(),tlfContactoUsuario.getText().toString(),tlfContactoUsuario2.getText().toString(),nroHistoriaUsuario.getText().toString(),mPasswordView.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            DB.actualizarPaciente(pacienteNuevo);
            getActivity().onBackPressed();
        }
        if (id == R.id.action_edit) {
            flag = true;
            nombre.setEnabled(true);
            apellidoUsuario.setEnabled(true);
            cedulaUsuario.setEnabled(true);
            sexo.setEnabled(true);
            fechaNacimientoUsuario.setEnabled(true);
            lugarNacimientoUsuario.setEnabled(true);
            tlfContactoUsuario.setEnabled(true);
            tlfContactoUsuario2.setEnabled(true);
            mPasswordView.setEnabled(true);
            nroHistoriaUsuario.setEnabled(true);
            nombreUsuario.setEnabled(true);
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
