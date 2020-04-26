package com.uagrm.auxiliaturasya_php;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ServiceNotificaciones extends Service {

    Context thisContext=this;

    @Override
    public void onCreate() {
        time tiempo=new time();
        tiempo.execute();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        return START_STICKY;
    }



    public void hilo(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    void ejecutar(){
        time tiempo=new time();
        tiempo.execute();
    }

    public class time extends AsyncTask<Void,Integer,Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {
            int tiempo=60;//segundos
            for (int i=0; i<tiempo; i++){
                hilo();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            ejecutar();
           // Toast.makeText(thisContext,"se ejecuta cada 3 seg",Toast.LENGTH_SHORT).show();

          //  notificacion();
        }


        void notificacion(){
            NotificationCompat.Builder builder=new NotificationCompat.Builder(thisContext,"Notificacion");
            builder.setSmallIcon(R.drawable.ic_priority_high_black_24dp);
            builder.setContentTitle("Notificacion de auxiliatura");
            builder.setContentText("Tienes una nueva notificacion");
            builder.setColor(Color.GREEN);
            builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
            builder.setLights(Color.MAGENTA,1000,1000);
            builder.setVibrate(new long[]{1000,1000,1000,1000});
            builder.setDefaults(Notification.DEFAULT_SOUND);

            NotificationManagerCompat managerCompat=NotificationManagerCompat.from(thisContext);
            managerCompat.notify(0,builder.build());

        }


    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
