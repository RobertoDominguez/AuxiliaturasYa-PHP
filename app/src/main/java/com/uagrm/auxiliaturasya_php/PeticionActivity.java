package com.uagrm.auxiliaturasya_php;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class PeticionActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{

    Button buttonFechaPeticion,buttonHoraPeticion,buttonEnviarPeticion,buttonAumentarDuracion,buttonDisminuirDuracion;
    EditText editTextFechaPeticion,editTextHoraPeticion,editTextHoraDuracionPeticion,editTextMinutosDuracionPeticion,editTextPrecioPeticion;

    private int año,mes,dia,hora,minuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peticion);


        requestQueue = Volley.newRequestQueue(this.getApplicationContext());

        buttonFechaPeticion=(Button) findViewById(R.id.buttonFechaPeticion);
        buttonHoraPeticion=(Button) findViewById(R.id.buttonHoraPeticion);
        buttonEnviarPeticion=(Button) findViewById(R.id.buttonEnviarPeticion);
        buttonAumentarDuracion=(Button) findViewById(R.id.buttonAumentarHoraPeticion);
        buttonDisminuirDuracion=(Button) findViewById(R.id.buttonDisminuirHoraPeticion);




        editTextFechaPeticion=(EditText) findViewById(R.id.editTextFechaPeticion);
            editTextFechaPeticion.setEnabled(false);
        editTextHoraPeticion=(EditText) findViewById(R.id.editTextHoraPeticion);
            editTextHoraPeticion.setEnabled(false);
        editTextHoraDuracionPeticion=(EditText) findViewById(R.id.editTextDuracionHoraPeticion);
            editTextHoraDuracionPeticion.setEnabled(false);
        editTextMinutosDuracionPeticion=(EditText) findViewById(R.id.editTextDuracionMinutoPeticion);
            editTextMinutosDuracionPeticion.setEnabled(false);
            editTextPrecioPeticion=(EditText) findViewById(R.id.editTextPrecioPeticion);

        buttonAumentarDuracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hora=Integer.parseInt(editTextHoraDuracionPeticion.getText().toString());
                int minuto=Integer.parseInt(editTextMinutosDuracionPeticion.getText().toString());
                if ( hora!=5){
                    if (minuto==45){
                        hora++; minuto=0;
                    }else{
                        minuto+=15;
                    }
                }
                editTextHoraDuracionPeticion.setText(hora+"");
                editTextMinutosDuracionPeticion.setText(minuto+"");
            }
        });

        buttonDisminuirDuracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hora=Integer.parseInt(editTextHoraDuracionPeticion.getText().toString());
                int minuto=Integer.parseInt(editTextMinutosDuracionPeticion.getText().toString());

                if (!(minuto==30 && hora==0)) {
                    if (minuto == 0 && hora != 0) {
                        hora--;
                        minuto = 45;
                    } else {
                        minuto -= 15;
                    }
                }
                editTextHoraDuracionPeticion.setText(hora+"");
                editTextMinutosDuracionPeticion.setText(minuto+"");
            }
        });


        buttonFechaPeticion.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
               obtenerFecha();
            }
        });

        buttonHoraPeticion.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                obtenerHora();
            }
        });


        buttonEnviarPeticion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                enviarPeticion();
            }
        });

    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    void obtenerFecha(){
        final Calendar calendar=Calendar.getInstance();
        dia=calendar.get(Calendar.DAY_OF_MONTH);
        mes=calendar.get(Calendar.MONTH);
        año=calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                editTextFechaPeticion.setText( year + "-" + (month+1) + "-" + dayOfMonth);
            }
        },año,mes,dia);
        datePickerDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void obtenerHora(){
        final Calendar calendar=Calendar.getInstance();
        hora=calendar.get(Calendar.HOUR_OF_DAY);
        minuto=calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                editTextHoraPeticion.setText(hourOfDay+":"+minute);
            }
        },hora,minuto,true);
        timePickerDialog.show();
    }


    //metodos internet
    RequestQueue requestQueue;
    JsonRequest jsonRequest;

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this,"Revisa los datos (fecha)"+error.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {


        JSONArray jsonArray=response.optJSONArray("datos");
        JSONObject jsonObject=null;

        try {
            jsonObject=jsonArray.getJSONObject(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Toast.makeText(this,"Se ha enviado la peticion con exito, espera que el auxiliar cree el grupo si lo acepta!"+".",Toast.LENGTH_SHORT).show();
        irAMenu();
    }

    private void enviarPeticion(){
        String idEstudiante=getIntent().getStringExtra("idEstudiante").toString();
        String idAuxiliar=getIntent().getStringExtra("idAuxiliar").toString();
        String idMateria=getIntent().getStringExtra("idMateria").toString();
        String fechaInicio=editTextFechaPeticion.getText().toString();
        String hora=editTextHoraPeticion.getText().toString()+":"+editTextMinutosDuracionPeticion.getText().toString();
        String precio=editTextPrecioPeticion.getText().toString();

       String url =getString(R.string.host)+"/nuevaPeticion.php?idEstudiante="+idEstudiante+"&idAuxiliar="+idAuxiliar+"&idMateria="+idMateria+
                "&fechaInicio="+fechaInicio+"&hora="+hora+"&precio="+precio;
        jsonRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        requestQueue.add(jsonRequest);
    }


    public void irAMenu(){
        Intent intent=new Intent(this,MenuActivity.class);
        intent.putExtra("idEstudiante",getIntent().getStringExtra("idEstudiante").toString());
        startActivity(intent);
    }

}


