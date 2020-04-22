package com.uagrm.auxiliaturasya_php;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class RegistroAuxiliarFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener{

    RequestQueue requestQueue;
    JsonRequest jsonRequest;

    EditText editTextCodigoRA,editTextContrasenaRA,editTextNombreRA,editTextApellidoRA,editTextTelefonoRA;
    Button btnRegistrarse;
    TextView textViewIniciarSesion;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vistaRegistro=inflater.inflate(R.layout.fragment_registro_auxiliar,container,false);

        editTextCodigoRA=(EditText) vistaRegistro.findViewById(R.id.editTextCodigoRA);
        editTextContrasenaRA=(EditText) vistaRegistro.findViewById(R.id.editTextContrasenaRA);
        editTextNombreRA=(EditText) vistaRegistro.findViewById(R.id.editTextNombreRA);
        editTextApellidoRA=(EditText) vistaRegistro.findViewById(R.id.editTextApellidoRA);
        editTextTelefonoRA=(EditText) vistaRegistro.findViewById(R.id.editTextTelefonoRA);
        btnRegistrarse=(Button) vistaRegistro.findViewById(R.id.buttonRegistrarseA);

        requestQueue = Volley.newRequestQueue(getContext());
        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarse();
            }
        });

        textViewIniciarSesion=(TextView) vistaRegistro.findViewById(R.id.textViewIniciarSesionRA);

        textViewIniciarSesion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                iniciarSesion();
            }
        });

        return vistaRegistro;
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"Error al registrar usuario. El codigo ya esta en uso o datos sin llenar."+error.toString(),Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(),"Se registro el usuario "+editTextNombreRA.getText().toString()+" con éxito!",Toast.LENGTH_SHORT).show();
        iniciarSesion();
    }

    void iniciarSesion(){
        SesionAuxiliarFragment sesionFragment=new SesionAuxiliarFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.escenario,sesionFragment).addToBackStack(null).commit();
    }

    void registrarse(){
        String url =getString(R.string.host)+"/signupAux.php?id_auxiliar="+editTextCodigoRA.getText().toString()+"&contraseña="+
                editTextContrasenaRA.getText().toString()+"&nombre="+editTextNombreRA.getText().toString()+"&apellido="
                +editTextApellidoRA.getText().toString()+"&celular="+editTextTelefonoRA.getText().toString();
        jsonRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        requestQueue.add(jsonRequest);
    }



}
