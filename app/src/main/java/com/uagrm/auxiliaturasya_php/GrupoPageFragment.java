package com.uagrm.auxiliaturasya_php;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class GrupoPageFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener{
    RequestQueue requestQueue;
    JsonRequest jsonRequest;

    ArrayList<Grupo> myDataset;
    GrupoAdapter mAdapter;
    RecyclerView recyclerView;

    String facultad,materia;

    public GrupoPageFragment(String _facultad,String _materia){
        facultad=_facultad;
        materia=_materia;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_grupo_page, container, false);

        requestQueue = Volley.newRequestQueue(getContext());

         recyclerView=(RecyclerView) vista.findViewById(R.id.RecyclerViewItemsGrupo);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        myDataset=new ArrayList<>();

        mAdapter=new GrupoAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);

        obtenerGrupos();
        return vista;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"No se pudo obtener los datos"+error.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray jsonArray=response.optJSONArray("datos");
        JSONObject jsonObject=null;

        boolean hayDatos=true;
        int i=0;
        while (!jsonArray.isNull(i)){
            try {
                jsonObject = jsonArray.getJSONObject(i);

                /////////////////////////////////////////////////////////////



                Grupo grupo=new Grupo(jsonObject.optString("nombreGrupo"),jsonObject.optString("nombreMateria"),
                        jsonObject.optString("dia"),jsonObject.optString("hora"),
                        jsonObject.optString("fechaIni"),jsonObject.optString("fechafin"));

                myDataset.add(grupo);

                mAdapter.setDataset(myDataset);
                recyclerView.setAdapter(mAdapter);

                /////////////////////////////////////////////////////////////


                Toast.makeText(getContext(),"datos"+jsonObject.optString("nombreGrupo"),Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
                hayDatos=false;
                Toast.makeText(getContext(),i+" error al obtener el dato",Toast.LENGTH_SHORT).show();
            }
            i++;
        }

    }

    private void obtenerGrupos(){
        String url ="http://auxiliaturasya.000webhostapp.com/grupos.php?"+"facultad="+facultad+"&materia="+materia;
        jsonRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        requestQueue.add(jsonRequest);
    }
}
