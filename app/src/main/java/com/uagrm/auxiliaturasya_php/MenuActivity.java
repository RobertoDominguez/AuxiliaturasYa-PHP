package com.uagrm.auxiliaturasya_php;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MenuActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        tabLayout=(TabLayout) findViewById(R.id.TabLayoutMenu);
        viewPager=(ViewPager) findViewById(R.id.ViewPagerMenu);

        tabLayout.setupWithViewPager(viewPager);

        setUpViewPager(viewPager);
    }

    private void setUpViewPager(ViewPager viewPager){
        TabViewPageAdapter tabViewPageAdapter=new TabViewPageAdapter(getSupportFragmentManager());
        tabViewPageAdapter.addFragment(new MateriaPageFragment(),"materias");
        tabViewPageAdapter.addFragment(new AuxiliarPageFragment(),"Auxiliares");
        viewPager.setAdapter(tabViewPageAdapter);
    }
}
