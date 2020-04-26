package com.uagrm.auxiliaturasya_php;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;

public class GrupoAuxiliarAdapter extends RecyclerView.Adapter<GrupoAuxiliarAdapter.MyViewHolder> implements  Response.Listener<JSONObject>,Response.ErrorListener{
    private ArrayList<Grupo> mDataset;
    Activity activity;
    RequestQueue requestQueue;
    JsonRequest jsonRequest;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView textViewNombreMateriaG;
        TextView textViewNombreGrupoG;
        TextView textViewDiaG;
        TextView textViewHoraG;
        CardView cardViewGrupos;



        MyViewHolder(View v) {
            super(v);

            textViewNombreMateriaG=(TextView) v.findViewById(R.id.textViewNombreAuxiliarA);
            textViewNombreGrupoG=(TextView) v.findViewById(R.id.textViewNombreGrupoG);
            textViewDiaG=(TextView) v.findViewById(R.id.textViewDiaG);
            textViewHoraG=(TextView) v.findViewById(R.id.textViewHoraG);
            cardViewGrupos=(CardView) v.findViewById(R.id.CardViewMateriaG);



        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    GrupoAuxiliarAdapter(ArrayList<Grupo> myDataset,Activity activity) {
        mDataset = myDataset;
        this.activity=activity;
    }

    void setDataset(ArrayList<Grupo> myDataset){
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public GrupoAuxiliarAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_header_grupo, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textViewNombreMateriaG.setText(mDataset.get(position).getNombreMateria());
        holder.textViewNombreGrupoG.setText(mDataset.get(position).getNombreGrupo());
        holder.textViewDiaG.setText(mDataset.get(position).getDia());
        holder.textViewHoraG.setText(mDataset.get(position).getHora());




    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }





    //////////////////////////////////////////////////////////

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(JSONObject response) {

    }

    private void cancelarGrupo(String idGrupo){
        String url =activity.getApplicationContext().getString(R.string.host)+"/";
        jsonRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        requestQueue.add(jsonRequest);
    }


}
