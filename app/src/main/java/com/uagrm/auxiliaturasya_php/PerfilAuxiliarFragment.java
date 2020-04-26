package com.uagrm.auxiliaturasya_php;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class PerfilAuxiliarFragment extends Fragment {

    String idAuxiliar,nombreCompletoAuxiliar;
    TextView textViewNombreCompletoAux;
    ViewPager viewPagerPA;
    Button buttonAñadirMateriaAuxiliar;

    public PerfilAuxiliarFragment(String idAuxiliar,String nombreCompletoAuxiliar){
        this.idAuxiliar=idAuxiliar;
        this.nombreCompletoAuxiliar=nombreCompletoAuxiliar;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_perfil_auxiliar, container, false);

        textViewNombreCompletoAux=(TextView) view.findViewById(R.id.textViewNombreAuxiliarPA);
        textViewNombreCompletoAux.setText(nombreCompletoAuxiliar);

        viewPagerPA=(ViewPager) view.findViewById(R.id.ViewPagerPA);

        setUpViewPager(viewPagerPA);

        buttonAñadirMateriaAuxiliar=(Button) view.findViewById(R.id.buttonAñadirMateriaAuxiliar);

        buttonAñadirMateriaAuxiliar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),NuevaMateriaAuxiliarActivity.class);
                intent.putExtra("codigo",idAuxiliar);
                intent.putExtra("nombreCompletoauxiliar",nombreCompletoAuxiliar);
                startActivity(intent);
            }
        });

        return view;
    }


    private void setUpViewPager(ViewPager viewPager){
        TabViewPageAdapter tabViewPageAdapter=new TabViewPageAdapter( getParentFragmentManager());
        tabViewPageAdapter.addFragment(new AuxiliarMateriaAuxiliarPageFragment(idAuxiliar),"Materias");
        viewPager.setAdapter(tabViewPageAdapter);
    }

}
