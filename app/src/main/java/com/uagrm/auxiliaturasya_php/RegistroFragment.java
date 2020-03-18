package com.uagrm.auxiliaturasya_php;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
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


public class RegistroFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener{

    RequestQueue requestQueue;
    JsonRequest jsonRequest;

    EditText editTextCodigoR,editTextContrasenaR,editTextNombreR,editTextApellidoR;
    Button btnRegistrarse;
    TextView textViewIniciarSesion;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vistaRegistro=inflater.inflate(R.layout.fragment_registro,container,false);


        editTextCodigoR=(EditText) vistaRegistro.findViewById(R.id.editTextCodigoR);
        editTextContrasenaR=(EditText) vistaRegistro.findViewById(R.id.editTextContrasenaR);
        editTextNombreR=(EditText) vistaRegistro.findViewById(R.id.editTextNombreR);
        editTextApellidoR=(EditText) vistaRegistro.findViewById(R.id.editTextApellidoR);
        btnRegistrarse=(Button) vistaRegistro.findViewById(R.id.buttonRegistrarse);

        requestQueue = Volley.newRequestQueue(getContext());
        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarse();
            }
        });


        textViewIniciarSesion=(TextView) vistaRegistro.findViewById(R.id.textViewIniciarSesionR);

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
        Toast.makeText(getContext(),"Se registro el usuario "+editTextNombreR.getText().toString()+" con éxito!",Toast.LENGTH_SHORT).show();
        iniciarSesion();
    }


    public void registrarse(){
        String url ="http://auxiliaturasya.000webhostapp.com/signup.php?codigo="+editTextCodigoR.getText().toString()+"&ci="+
                editTextContrasenaR.getText().toString()+"&nombre="+editTextNombreR.getText().toString()+"&apellido="+editTextApellidoR.getText().toString();
        jsonRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        requestQueue.add(jsonRequest);
    }

    void iniciarSesion(){
        SesionFragment sesionFragment=new SesionFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.escenario,sesionFragment).addToBackStack(null).commit();
    }
}
