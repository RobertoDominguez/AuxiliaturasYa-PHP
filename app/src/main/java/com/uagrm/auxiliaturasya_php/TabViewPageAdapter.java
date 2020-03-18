package com.uagrm.auxiliaturasya_php;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabViewPageAdapter extends FragmentPagerAdapter {

    final List<Fragment> listaDeFragmentos=new ArrayList<>();
    final List<String> listaDeTitulos=new ArrayList<>();

    public TabViewPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return listaDeFragmentos.get(position);
    }

    @Override
    public int getCount() {
        return listaDeFragmentos.size();
    }

    public void addFragment(Fragment fragment,String string){
        listaDeFragmentos.add(fragment);
        listaDeTitulos.add(string);
    }

    @Override
    public CharSequence getPageTitle(int position){
        return listaDeTitulos.get(position);
    }

}
