package com.uagrm.auxiliaturasya_php;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class MateriaAdapter extends RecyclerView.Adapter<MateriaAdapter.MyViewHolder> {

    private ArrayList<Materia> mDataset;
    Activity activity;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        TextView textViewNombreMateriaM;
        TextView textViewNombreFacultadM;
        CardView cardViewMateriaM;


        MyViewHolder(View v) {
            super(v);

            textViewNombreMateriaM=(TextView) v.findViewById(R.id.textViewNombreMateriaM);
            textViewNombreFacultadM=(TextView) v.findViewById(R.id.textViewNombreFacultadM);
            cardViewMateriaM=(CardView) v.findViewById(R.id.CardViewMateriaM);

        }

    }



    // Provide a suitable constructor (depends on the kind of dataset)
    MateriaAdapter(ArrayList<Materia> myDataset,Activity _activity) {
        mDataset = myDataset;
        activity=_activity;
    }

    void setDataset(ArrayList<Materia> myDataset){
        mDataset = myDataset;
    }
    void setActivity(Activity _activity){ activity=_activity; }

    // Create new views (invoked by the layout manager)
    @Override
    public MateriaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_header_materia, parent, false);

        MateriaAdapter.MyViewHolder vh = new MateriaAdapter.MyViewHolder(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MateriaAdapter.MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textViewNombreMateriaM.setText(mDataset.get(position).getNombreMateria());
        holder.textViewNombreFacultadM.setText(mDataset.get(position).getNombreFacultad());

        //al hacer click sobre la cardview cambia a la vista de grupos del cardview seleccionado
        holder.cardViewMateriaM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String facultad= mDataset.get(position).getNombreFacultad();
              String materia=mDataset.get(position).getNombreMateria();

                Intent intent=new Intent(activity,MenuActivity.class);
                intent.putExtra("facultad",facultad);
                intent.putExtra("materia",materia);
                activity.startActivity(intent);

              Toast.makeText(activity.getApplicationContext(),"Facultad"+facultad,Toast.LENGTH_SHORT).show();
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
