package com.uagrm.auxiliaturasya_php;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AuxiliarMateriaAuxiliarAdapter extends RecyclerView.Adapter<AuxiliarMateriaAuxiliarAdapter.MyViewHolder> {
    private ArrayList<MateriaAuxiliar> mDataset;
    Activity activity;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView textViewNombreMateriaMA,textViewFacultadMateriaMA;
        CardView cardViewAuxiliarMateriaAuxiliarMA;

        MyViewHolder(View v) {
            super(v);
            textViewNombreMateriaMA=(TextView) v.findViewById(R.id.textViewNombreMateriaAuxiliarMA);
            cardViewAuxiliarMateriaAuxiliarMA=(CardView) v.findViewById(R.id.CardViewMateriaAuxiliarMA);
            textViewFacultadMateriaMA=(TextView) v.findViewById(R.id.textViewFacultadMateriaAuxiliarMA);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    AuxiliarMateriaAuxiliarAdapter(ArrayList<MateriaAuxiliar> myDataset, Activity activity) {
        mDataset = myDataset;
        this.activity=activity;
    }

    void setDataset(ArrayList<MateriaAuxiliar> myDataset){
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AuxiliarMateriaAuxiliarAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                  int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_header_materia_auxiliar, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textViewNombreMateriaMA.setText(mDataset.get(position).getNombreMateria());
        holder.textViewFacultadMateriaMA.setText(mDataset.get(position).getNombreFacultad());
        holder.cardViewAuxiliarMateriaAuxiliarMA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idAuxiliar=mDataset.get(position).getIdAuxiliar();
                String idMateria=mDataset.get(position).getIdMateria();


                Intent intent=new Intent(activity,NuevoGrupoActivity.class);
                intent.putExtra("codigo",idAuxiliar);
                intent.putExtra("idMateria",idMateria);
                activity.startActivity(intent);
            }
        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
