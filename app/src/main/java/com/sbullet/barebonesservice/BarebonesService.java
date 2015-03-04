package com.sbullet.barebonesservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Chris Schneider on 3/2/2015.
 */


/**
 * IMPORTANT: If extending Service class and starting a service from an activity,
 * we need to start that service in its own thread.
 */
public class BarebonesService extends Service {


    private String m_time = "";
    private String m_startTime = "";


    //IBinder for this service


    public class MyBinder extends Binder
    {
        BarebonesService getService(){

            return BarebonesService.this;
        }
    }


    //onBind is overridden to allow activities to bind to the service
    //If binding is not allowed, you return null, otherwise you return
    //an IBinder which allows communication between activity and service
    @Override
    public IBinder onBind(Intent intent) {
        return m_binder;
    }

    private final IBinder m_binder = new MyBinder();


    @Override
    public void onCreate()
    {
        m_startTime = getTime();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        return START_STICKY; //Persistent service
    }



    //Clean up any threads, listeners, receivers, etc. when the service ends.
    @Override
    public void onDestroy()
    {
        Toast.makeText(this, "Service unbound at: " + getTime(), Toast.LENGTH_SHORT).show();
    }




    private String getTime()
    {
        //Calculate time
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        m_time = dateFormat.format(date);
        return m_time;
    }

    public String getStartTime()
    {
        return m_startTime;
    }






}
