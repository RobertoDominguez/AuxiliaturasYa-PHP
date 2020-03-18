package com.uagrm.auxiliaturasya_php;

import android.content.Intent;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class SesionFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener{
    RequestQueue requestQueue;
    JsonRequest jsonRequest;

    EditText editTextCodigo,editTextContrasena;
    Button btnIniciarSesion;
    TextView textViewRegistrar,textViewRegistrarAux;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sesion, container, false);

        View vistaSesion=inflater.inflate(R.layout.fragment_sesion,container,false);
        editTextCodigo=(EditText)vistaSesion.findViewById(R.id.editTextCodigo);
        editTextContrasena=(EditText)vistaSesion.findViewById(R.id.editTextContrasena);
        btnIniciarSesion=(Button) vistaSesion.findViewById(R.id.buttonIniciarSesion);

        requestQueue = Volley.newRequestQueue(getContext());
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });

        textViewRegistrar=(TextView) vistaSesion.findViewById(R.id.textViewCrearCuenta);

        textViewRegistrar.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               registrarse();
           }
        });



        return vistaSesion;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"No se ha encontrado el usuario. "+error.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Estudiante estudiante=new Estudiante();

        JSONArray jsonArray=response.optJSONArray("datos");
        JSONObject jsonObject=null;

        try {
            jsonObject=jsonArray.getJSONObject(0);
            estudiante.setCodigo(jsonObject.optString("codigo"));
            estudiante.setNombre(jsonObject.optString("nombre"));
            estudiante.setApellido(jsonObject.optString("apellido"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Toast.makeText(getContext(),"Se ha encontrado el usuario "+estudiante.getNombre().toString()+".",Toast.LENGTH_SHORT).show();

        irAMenu();

    }

    public void irAMenu(){
        Intent intent=new Intent(getActivity(),MenuActivity.class);
        startActivity(intent);
    }

    private void iniciarSesion(){
        String url ="http://auxiliaturasya.000webhostapp.com/login.php?codigo="
                +editTextCodigo.getText().toString()+"&ci="+editTextContrasena.getText().toString();
        jsonRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        requestQueue.add(jsonRequest);
    }


    void registrarse(){
        RegistroFragment registroFragment=new RegistroFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.escenario,registroFragment).addToBackStack(null).commit();
    }



}
