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


public class SesionAuxiliarFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener{
    RequestQueue requestQueue;
    JsonRequest jsonRequest;

    EditText editTextCodigo,editTextContrasena;
    Button btnIniciarSesion;
    TextView textViewRegistrarAux;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista=inflater.inflate(R.layout.fragment_sesion_auxiliar, container, false);

        editTextCodigo=(EditText)vista.findViewById(R.id.editTextCodigoAuxiliar);
        editTextContrasena=(EditText)vista.findViewById(R.id.editTextContrasenaAuxiliar);
        btnIniciarSesion=(Button) vista.findViewById(R.id.buttonIniciarSesionAuxiliar);

        requestQueue = Volley.newRequestQueue(getContext());
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesionAuxiliar();
            }
        });

        textViewRegistrarAux=(TextView) vista.findViewById(R.id.textViewCrearCuentaAuxiliar);

        textViewRegistrarAux.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                registrarAux();
            }
        });
        return vista;
    }



    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"No se ha encontrado el usuario. "+error.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Auxiliar auxiliar=new Auxiliar();

        JSONArray jsonArray=response.optJSONArray("datos");
        JSONObject jsonObject=null;

        try {
            jsonObject=jsonArray.getJSONObject(0);
            auxiliar.setId_auxiliar(jsonObject.optString("id_auxiliar"));
            auxiliar.setNombre(jsonObject.optString("nombre"));
            auxiliar.setApellido(jsonObject.optString("apellido"));
            auxiliar.setCelular(jsonObject.optString("celular"));
            auxiliar.setHabilitado(jsonObject.optString("habilitado"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
      //  Toast.makeText(getContext(),"Se ha encontrado el usuario "+auxiliar.getNombre().toString()+".",Toast.LENGTH_SHORT).show();

        irAMenuAuxiliar();
    }

    void iniciarSesionAuxiliar(){
        String url =getString(R.string.host)+"/loginAux.php?codigo="
                +editTextCodigo.getText().toString()+"&ci="+editTextContrasena.getText().toString();
        jsonRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        requestQueue.add(jsonRequest);
    }

    void registrarAux(){
        RegistroAuxiliarFragment registroAuxiliarFragment=new RegistroAuxiliarFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.escenario,registroAuxiliarFragment).addToBackStack(null).commit();
    }

    void irAMenuAuxiliar(){
        Intent intent=new Intent(getActivity(),MenuAuxiliarActivity.class);
        intent.putExtra("codigo",editTextCodigo.getText().toString());
        startActivity(intent);
    }

}
