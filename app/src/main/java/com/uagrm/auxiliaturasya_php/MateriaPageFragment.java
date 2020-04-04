package com.uagrm.auxiliaturasya_php;

import android.os.Bundle;

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


public class MateriaPageFragment extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener{

    JsonRequest jsonRequest;
    RequestQueue requestQueue;

    ArrayList<Materia> myDataset;
    MateriaAdapter mAdapter;
    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_materia_page, container, false);

        requestQueue = Volley.newRequestQueue(getContext());

        recyclerView=(RecyclerView) vista.findViewById(R.id.RecyclerViewItemsMateria);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        myDataset=new ArrayList<>();

        mAdapter=new MateriaAdapter(myDataset,getActivity());
        recyclerView.setAdapter(mAdapter);

        obtenerMaterias();



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

                Materia materia=new Materia(jsonObject.optString("nombreMateria"),jsonObject.optString("nombreFacultad") );

                myDataset.add(materia);

                mAdapter.setDataset(myDataset);
                recyclerView.setAdapter(mAdapter);

                /////////////////////////////////////////////////////////////


                Toast.makeText(getContext(),"datos"+jsonObject.optString("nombreMateria"),Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
                hayDatos=false;
                Toast.makeText(getContext(),i+" error al obtener el dato",Toast.LENGTH_SHORT).show();
            }
            i++;
        }
    }

    private void obtenerMaterias(){
        String url ="http://auxiliaturasya.000webhostapp.com/materias.php";
        jsonRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        requestQueue.add(jsonRequest);
    }

}
