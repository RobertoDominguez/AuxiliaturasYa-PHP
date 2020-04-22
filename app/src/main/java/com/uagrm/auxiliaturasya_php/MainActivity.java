package com.uagrm.auxiliaturasya_php;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.WidgetContainer;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



                new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView splashScreen=(ImageView) findViewById(R.id.splashScreen);
                splashScreen.setVisibility(View.INVISIBLE);

                FragmentManager fm=getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.escenario,new SesionSelectionFragment()).commit();

            }
        },2000);

    }


}
