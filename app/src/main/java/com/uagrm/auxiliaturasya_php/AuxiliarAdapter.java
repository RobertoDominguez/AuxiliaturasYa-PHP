package com.uagrm.auxiliaturasya_php;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AuxiliarAdapter extends RecyclerView.Adapter<AuxiliarAdapter.MyViewHolder> {
    private ArrayList<Auxiliar> mDataset;

    Activity activity;
    String idEstudiante;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView textViewNombreAuxiliarA;
        TextView textViewApellidoA;
        TextView textViewCelularA;

        CardView cardViewAuxiliarA;

        MyViewHolder(View v) {
            super(v);

            textViewNombreAuxiliarA=(TextView) v.findViewById(R.id.textViewNombreAuxiliarA);
            textViewApellidoA=(TextView) v.findViewById(R.id.textViewApellidoA);
            textViewCelularA=(TextView) v.findViewById(R.id.textViewCelularA);
            cardViewAuxiliarA=(CardView) v.findViewById(R.id.CardViewAuxiliarA);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    AuxiliarAdapter(ArrayList<Auxiliar> myDataset, Activity _activity,String idEstudiante) {
        mDataset = myDataset;
        activity=_activity;
        this.idEstudiante=idEstudiante;
    }

    void setDataset(ArrayList<Auxiliar> myDataset){
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AuxiliarAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_header_auxiliar, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textViewNombreAuxiliarA.setText(mDataset.get(position).getNombre());
        holder.textViewApellidoA.setText(mDataset.get(position).getApellido());
        holder.textViewCelularA.setText(mDataset.get(position).getCelular());


        holder.cardViewAuxiliarA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity,EstudianteAuxiliarActivity.class);

                String idAuxiliar=mDataset.get(position).getId_auxiliar();
                String nombreCompletoAuxiliar=mDataset.get(position).getNombre()+" "+mDataset.get(position).getApellido();

                intent.putExtra("nombreCompletoAuxiliar",nombreCompletoAuxiliar);
                intent.putExtra("idAuxiliar",idAuxiliar);
                intent.putExtra("idEstudiante",idEstudiante);
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
