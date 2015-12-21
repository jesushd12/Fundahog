package com.example.jesus.fundahog;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

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
    Calendar calendar = Calendar.getInstance();
    DataBaseManager DB;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_login,container,false);
        DB = new DataBaseManager(getActivity());
        paciente = DB.consultarDatosPaciente();

        nombre = (EditText)v.findViewById(R.id.edt_nombre);
        nombre.setText(paciente.getNombre());
        apellidoUsuario = (EditText)v.findViewById(R.id.edt_apellido);
        apellidoUsuario.setText(paciente.getApellido());
        cedulaUsuario = (EditText)v.findViewById(R.id.edt_cedula);
        cedulaUsuario.setText(paciente.getCedula());
        sexoUsuario = (EditText)v.findViewById(R.id.edt_sexo);
        sexoUsuario.setText(paciente.getSexo());
        fechaNacimientoUsuario = (EditText)v.findViewById(R.id.edt_fechaNacimiento);
        fechaNacimientoUsuario.setText(formatter.format(paciente.getFechaNacimiento()));
        fechaNacimientoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        lugarNacimientoUsuario = (EditText)v.findViewById(R.id.edt_lugarNacimiento);
        lugarNacimientoUsuario.setText(paciente.getLugarNacimiento());
        //emailUsuario = (AutoCompleteTextView)findViewById(R.id.emailUsuario);
        //contrasenaUsuario = (EditText)findViewById(R.id.edt_password);
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
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pacienteNuevo = new Pacient(nombre.getText().toString(),apellidoUsuario.getText().toString(),cedulaUsuario.getText().toString(),calendar.getTime(),lugarNacimientoUsuario.getText().toString(),sexoUsuario.getText().toString(),nombreUsuario.getText().toString(),tlfContactoUsuario.getText().toString(),tlfContactoUsuario2.getText().toString(),nroHistoriaUsuario.getText().toString(),mPasswordView.getText().toString());
                DB.actualizarPaciente(pacienteNuevo);
                getActivity().onBackPressed();
            }
        });

        return v;
    }
}
