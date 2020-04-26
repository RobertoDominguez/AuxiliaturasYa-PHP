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


public class AuxiliarMateriaAuxiliarPageFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {

    JsonRequest jsonRequest;
    RequestQueue requestQueue;

    ArrayList<MateriaAuxiliar> myDataset;
    AuxiliarMateriaAuxiliarAdapter mAdapter;
    RecyclerView recyclerView;

    String idAuxiliar;

    public AuxiliarMateriaAuxiliarPageFragment(String id_aux){
        this.idAuxiliar=id_aux;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_auxiliar_materia_auxiliar_page, container, false);

        requestQueue = Volley.newRequestQueue(getContext());

        recyclerView=(RecyclerView) vista.findViewById(R.id.RecyclerViewItemsMateriaAuxiliar);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        myDataset=new ArrayList<>();

        mAdapter=new AuxiliarMateriaAuxiliarAdapter(myDataset,getActivity());
        recyclerView.setAdapter(mAdapter);

        obtenerMateriaAuxiliars();



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

                MateriaAuxiliar MateriaAuxiliar=new MateriaAuxiliar(jsonObject.optString("idMateria")
                        ,jsonObject.optString("nombreMateria"),jsonObject.optString("idAuxiliar")
                        ,jsonObject.optString("esAuxiliarOficial"),jsonObject.optString("nombreFacultad"));

                myDataset.add(MateriaAuxiliar);

                mAdapter.setDataset(myDataset);
                recyclerView.setAdapter(mAdapter);

                /////////////////////////////////////////////////////////////


                //  Toast.makeText(getContext(),"datos"+jsonObject.optString("nombreMateriaAuxiliar"),Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
                hayDatos=false;
                Toast.makeText(getContext(),i+" error al obtener el dato",Toast.LENGTH_SHORT).show();
            }
            i++;
        }
    }

    private void obtenerMateriaAuxiliars(){
        String url =getString(R.string.host)+"/materiaAuxiliar.php?id_auxiliar="+idAuxiliar;
        jsonRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        requestQueue.add(jsonRequest);
    }

}
