package com.uagrm.auxiliaturasya_php;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.util.ArrayList;
import java.util.List;

public class NuevaMateriaAuxiliarActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{




    public class FacultadContenedor{
        public String facultad;
        public List<String> listaDeMaterias;
        public List<String> listaDeIds;

        public void insertarMateria(String nombreMateria,String idMateria){
            listaDeMaterias.add(nombreMateria); listaDeIds.add(idMateria);
        }

        public FacultadContenedor(){ facultad=""; listaDeMaterias=new ArrayList<String>(); listaDeIds=new ArrayList<String>();}

        public List<String> getListaDeIds() { return listaDeIds; }

        public void setListaDeIds(List<String> listaDeIds) {this.listaDeIds = listaDeIds; }

        public String getFacultad() { return facultad; }

        public void setFacultad(String facultad) { this.facultad = facultad; }

        public List<String> getListaDeMaterias() { return listaDeMaterias; }

        public void setListaDeMaterias(List<String> listaDeMaterias) { this.listaDeMaterias = listaDeMaterias; }
    }

    JsonRequest<JSONObject> jsonRequest;
    RequestQueue requestQueue;

    Spinner spinnerFacultad,spinnerMateria;
    Button buttonAñadirMateriaAuxiliar;

    List<FacultadContenedor> listaDeFacultadContenedor=new ArrayList<FacultadContenedor>();
    List<String> listaDeFacultades=new ArrayList<String>();
    ArrayAdapter<String> arrayAdapterFacultad;
    ArrayAdapter<String> arrayAdapterMateria;

    EditText editTextFacultad,editTextMateria;

    boolean enviandoDatos=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_materia_auxiliar);

        buttonAñadirMateriaAuxiliar = (Button) findViewById(R.id.buttonAñadirMateriaAuxiliar);



        editTextFacultad = (EditText) findViewById(R.id.editTextNombreFacultad);
        editTextFacultad.setEnabled(false);
        editTextMateria = (EditText) findViewById(R.id.editTextNombreMateria);
        editTextMateria.setEnabled(false);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        obtenerMaterias();

        spinnerFacultad = (Spinner) findViewById(R.id.spinnerFacultad);
        spinnerMateria = (Spinner) findViewById(R.id.spinnerMateria);


        spinnerFacultad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cambiarMaterias(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerMateria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonAñadirMateriaAuxiliar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviandoDatos=true;
                nuevaMateriaAuxiliar();
            }
        });


    }



    public void cambiarMaterias(int i){
        arrayAdapterMateria=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,listaDeFacultadContenedor.get(i).getListaDeMaterias());
        spinnerMateria.setAdapter(arrayAdapterMateria);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this,"Ya tienes añadida la materia",Toast.LENGTH_SHORT).show();
        enviandoDatos=false;
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray jsonArray=response.optJSONArray("datos");
        JSONObject jsonObject=null;

       if (!enviandoDatos) {
           int i = 0;
           int j = 0;
           while (!jsonArray.isNull(i)) {
               try {
                   jsonObject = jsonArray.getJSONObject(i);

                   /////////////////////////////////////////////////////////////

                   String idMateria = jsonObject.optString("idMateria");
                   String nombreMateria = jsonObject.optString("nombreMateria");
                   String nombreFacultad = jsonObject.optString("nombreFacultad");

                   if (!listaDeFacultadContenedor.isEmpty()) {

                       if (listaDeFacultadContenedor.get(j).getFacultad().compareTo(nombreFacultad) == 0) {
                           listaDeFacultadContenedor.get(j).listaDeMaterias.add(nombreMateria);
                           listaDeFacultadContenedor.get(j).listaDeIds.add(idMateria);
                       } else {
                           FacultadContenedor facultadContenedor = new FacultadContenedor();
                           facultadContenedor.setFacultad(nombreFacultad);
                           facultadContenedor.insertarMateria(nombreMateria, idMateria);
                           listaDeFacultadContenedor.add(facultadContenedor);
                           listaDeFacultades.add(nombreFacultad);
                           j++;
                       }
                   } else {
                       FacultadContenedor facultadContenedor = new FacultadContenedor();
                       facultadContenedor.setFacultad(nombreFacultad);
                       facultadContenedor.insertarMateria(nombreMateria, idMateria);
                       listaDeFacultadContenedor.add(facultadContenedor);
                       listaDeFacultades.add(nombreFacultad);
                   }
                   /////////////////////////////////////////////////////////////

               } catch (JSONException e) {
                   e.printStackTrace();
                   Toast.makeText(this, i + " error al obtener el dato", Toast.LENGTH_SHORT).show();
               }
               i++;
           }


           arrayAdapterFacultad = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, listaDeFacultades);
           spinnerFacultad.setAdapter(arrayAdapterFacultad);

       }else{

           try {
               jsonObject = jsonArray.getJSONObject(0);

               Toast.makeText(this,"Se añadio la materia correctamente",Toast.LENGTH_LONG).show();
               Intent intent=new Intent(this,MenuAuxiliarActivity.class);
               intent.putExtra("codigo",getIntent().getStringExtra("codigo"));
               intent.putExtra("nombreCompletoAuxiliar",getIntent().getStringExtra("nombreCompletoAuxiliar"));
               startActivity(intent);

           } catch (JSONException e) {
               e.printStackTrace();
               enviandoDatos=false;
               Toast.makeText(this,"Ya tienes la materia añadida",Toast.LENGTH_LONG).show();
           }


       }
    }

    private void obtenerMaterias(){
        String url =getString(R.string.host)+"/materias.php";
        jsonRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        requestQueue.add(jsonRequest);
    }

    private void nuevaMateriaAuxiliar(){
        String idMateria=listaDeFacultadContenedor.get(spinnerFacultad.getSelectedItemPosition()).getListaDeIds().get(spinnerMateria.getSelectedItemPosition());
        String url =getString(R.string.host)+"/nuevaMateriaAuxiliar.php?idAuxiliar="+getIntent().getStringExtra("codigo").toString()+
                "&idMateria="+idMateria;
        jsonRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        requestQueue.add(jsonRequest);
    }

}
