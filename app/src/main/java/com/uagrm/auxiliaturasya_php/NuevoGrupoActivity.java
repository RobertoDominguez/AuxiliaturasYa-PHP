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

public class NuevoGrupoActivity extends AppCompatActivity implements Response.Listener<JSONObject>,Response.ErrorListener{
    //idAuxiliar    idMateria    en intent

    Button buttonFechaInicialGrupo,buttonFechaFinalGrupo,buttonHoraGrupo,buttonEnviarGrupo,buttonAumentarDuracion,buttonDisminuirDuracion;
    EditText editTextFechaInicialGrupo,editTextFechaFinalGrupo,
            editTextNombreGrupo,editTextDiaGrupo,editTextHoraGrupo,editTextHoraDuracionGrupo,editTextMinutosDuracionGrupo,editTextPrecioGrupo;

    private int año,mes,dia,hora,minuto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_grupo);

        requestQueue = Volley.newRequestQueue(this.getApplicationContext());

        buttonFechaInicialGrupo=(Button) findViewById(R.id.buttonFechaInicialGrupo);
        buttonFechaFinalGrupo=(Button) findViewById(R.id.buttonFechaFinalGrupo);
        buttonHoraGrupo=(Button) findViewById(R.id.buttonHoraGrupo);
        buttonEnviarGrupo=(Button) findViewById(R.id.buttonEnviarGrupo);
        buttonAumentarDuracion=(Button) findViewById(R.id.buttonAumentarHoraGrupo);
        buttonDisminuirDuracion=(Button) findViewById(R.id.buttonDisminuirHoraGrupo);

        editTextFechaInicialGrupo=(EditText) findViewById(R.id.editTextFechaInicialGrupo);
        editTextFechaInicialGrupo.setEnabled(false);
        editTextFechaFinalGrupo=(EditText) findViewById(R.id.editTextFechaFinalGrupo);
        editTextFechaFinalGrupo.setEnabled(false);
        editTextHoraGrupo=(EditText) findViewById(R.id.editTextHoraGrupo);
        editTextHoraGrupo.setEnabled(false);
        editTextHoraDuracionGrupo=(EditText) findViewById(R.id.editTextDuracionHoraGrupo);
        editTextHoraDuracionGrupo.setEnabled(false);
        editTextMinutosDuracionGrupo=(EditText) findViewById(R.id.editTextDuracionMinutoGrupo);
        editTextMinutosDuracionGrupo.setEnabled(false);
        editTextPrecioGrupo=(EditText) findViewById(R.id.editTextPrecioGrupo);
        editTextNombreGrupo=(EditText) findViewById(R.id.editTextNombreGrupo);
        editTextDiaGrupo=(EditText) findViewById(R.id.editTextDiaGrupo);

        buttonAumentarDuracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hora=Integer.parseInt(editTextHoraDuracionGrupo.getText().toString());
                int minuto=Integer.parseInt(editTextMinutosDuracionGrupo.getText().toString());
                if ( hora!=5){
                    if (minuto==45){
                        hora++; minuto=0;
                    }else{
                        minuto+=15;
                    }
                }
                editTextHoraDuracionGrupo.setText(hora+"");
                editTextMinutosDuracionGrupo.setText(minuto+"");
            }
        });

        buttonDisminuirDuracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hora=Integer.parseInt(editTextHoraDuracionGrupo.getText().toString());
                int minuto=Integer.parseInt(editTextMinutosDuracionGrupo.getText().toString());

                if (!(minuto==30 && hora==0)) {
                    if (minuto == 0 && hora != 0) {
                        hora--;
                        minuto = 45;
                    } else {
                        minuto -= 15;
                    }
                }
                editTextHoraDuracionGrupo.setText(hora+"");
                editTextMinutosDuracionGrupo.setText(minuto+"");
            }
        });


        buttonFechaInicialGrupo.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                obtenerFechaInicial();
            }
        });

        buttonFechaFinalGrupo.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                obtenerFechaFinal();
            }
        });

        buttonHoraGrupo.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                obtenerHora();
            }
        });


        buttonEnviarGrupo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                enviarGrupo();
            }
        });

    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    void obtenerFechaInicial(){
        final Calendar calendar=Calendar.getInstance();
        dia=calendar.get(Calendar.DAY_OF_MONTH);
        mes=calendar.get(Calendar.MONTH);
        año=calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                editTextFechaInicialGrupo.setText( year + "-" + (month+1) + "-" + dayOfMonth);
            }
        },año,mes,dia);
        datePickerDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void obtenerFechaFinal(){
        final Calendar calendar=Calendar.getInstance();
        dia=calendar.get(Calendar.DAY_OF_MONTH);
        mes=calendar.get(Calendar.MONTH);
        año=calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                editTextFechaFinalGrupo.setText( year + "-" + (month+1) + "-" + dayOfMonth);
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
                editTextHoraGrupo.setText(hourOfDay+":"+minute);
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
        Toast.makeText(this,"Se ha creado el grupo con exito revisa tus grupos!"+".",Toast.LENGTH_SHORT).show();
        irAMenuAuxiliar();
    }

    private void enviarGrupo(){
        String nombre=editTextNombreGrupo.getText().toString();
        String dia=editTextDiaGrupo.getText().toString();
        String hora=editTextHoraGrupo.getText().toString();
        String fechaInicio=editTextFechaInicialGrupo.getText().toString();
        String fechaFin=editTextFechaFinalGrupo.getText().toString();
        String duracion=editTextHoraDuracionGrupo.getText().toString()+":"+editTextMinutosDuracionGrupo.getText().toString();
        String precio=editTextPrecioGrupo.getText().toString();
        String idMateria=getIntent().getStringExtra("idMateria").toString();
        String idAuxiliar=getIntent().getStringExtra("codigo").toString();


        String url =getString(R.string.host)+"/nuevoGrupo.php?nombre="+nombre+"&dia="+dia+"&hora="+hora+"&fechaInicio="+fechaInicio+"&fechaFin="+fechaFin
                +"&duracion="+duracion+"&precio="+precio+"&idMateria="+idMateria+"&idAuxiliar="+idAuxiliar;
        jsonRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        requestQueue.add(jsonRequest);
    }


    public void irAMenuAuxiliar(){
        Intent intent=new Intent(this,MenuAuxiliarActivity.class);
        intent.putExtra("codigo",getIntent().getStringExtra("codigo").toString());
        startActivity(intent);
    }

}

