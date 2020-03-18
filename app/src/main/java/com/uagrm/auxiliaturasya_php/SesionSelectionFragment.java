package com.uagrm.auxiliaturasya_php;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class SesionSelectionFragment extends Fragment {

    ImageView imageViewEstudiante,imageViewAuxiliar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista= inflater.inflate(R.layout.fragment_sesion_selection, container, false);

        imageViewEstudiante=(ImageView) vista.findViewById(R.id.imageViewEstudiante);
        imageViewAuxiliar=(ImageView) vista.findViewById(R.id.imageViewAuxiliar);

        imageViewEstudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.escenario,new SesionFragment()).addToBackStack(null).commit();
            }
        });

        imageViewAuxiliar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.escenario,new SesionAuxiliarFragment()).addToBackStack(null).commit();
            }
        });


        return vista;
    }
}
