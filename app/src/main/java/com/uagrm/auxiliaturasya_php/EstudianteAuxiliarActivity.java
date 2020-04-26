package com.uagrm.auxiliaturasya_php;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

public class EstudianteAuxiliarActivity extends AppCompatActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudiante_auxiliar);

        viewPager=(ViewPager) findViewById(R.id.ViewPagerEA);
        setUpViewPager(viewPager);

        TextView textViewNombreAuxiliar=(TextView) findViewById(R.id.textViewNombreAuxiliarEA);
        textViewNombreAuxiliar.setText(getIntent().getExtras().getString("nombreCompletoAuxiliar").toString());

    }


    private void setUpViewPager(ViewPager viewPager){
        TabViewPageAdapter tabViewPageAdapter=new TabViewPageAdapter(getSupportFragmentManager());
        String idEstudiante=getIntent().getExtras().getString("idAuxiliar").toString();
        String idAuxiliar=getIntent().getExtras().getString("idAuxiliar").toString();

        tabViewPageAdapter.addFragment(new MateriaAuxiliarPageFragment(idAuxiliar,idEstudiante),"Materias");
        viewPager.setAdapter(tabViewPageAdapter);
    }
}
