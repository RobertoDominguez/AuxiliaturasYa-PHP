package com.uagrm.auxiliaturasya_php;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MenuAuxiliarActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_auxiliar);

        tabLayout=(TabLayout) findViewById(R.id.TabLayoutMenuAuxiliar);
        viewPager=(ViewPager) findViewById(R.id.ViewPagerMenuAuxiliar);

        tabLayout.setupWithViewPager(viewPager);

            setUpViewPager(viewPager);

    }

    private void setUpViewPager(ViewPager viewPager){
        TabViewPageAdapter tabViewPageAdapter=new TabViewPageAdapter(getSupportFragmentManager());
        tabViewPageAdapter.addFragment(new GrupoAuxiliarPageFragment(getIntent().getExtras().getString("codigo")),"Mis Grupos");
        viewPager.setAdapter(tabViewPageAdapter);
    }


}
