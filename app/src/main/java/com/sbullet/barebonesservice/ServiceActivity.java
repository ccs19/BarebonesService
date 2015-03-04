package com.sbullet.barebonesservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class ServiceActivity extends ActionBarActivity {

    private static String TAG = "ServiceActivity";
    private BarebonesService m_barebonesService;


    private ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className,
                                       IBinder binder) {
            Log.d(TAG, "onServiceConnection()");
            m_barebonesService = ((BarebonesService.MyBinder)binder).getService();

            if(m_barebonesService != null) {
                Toast.makeText(ServiceActivity.this, "Connected", Toast.LENGTH_SHORT).show();
                //Get service start time and set text box
                TextView startTime = (TextView) findViewById(R.id.start_time);
                startTime.setText(m_barebonesService.getStartTime());
            }
            else
            {
                Toast.makeText(ServiceActivity.this, "Cannot connect to service", Toast.LENGTH_SHORT).show();
            }

        }

        public void onServiceDisconnected(ComponentName className) {
            m_barebonesService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }


    @Override
    protected void onResume()
    {
        Log.d(TAG, "onResume()");
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        Log.d(TAG, "onPause()");
        super.onPause();
        unbindService(mConnection);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        Log.d(TAG, "onCreateOptionsMenu()");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Log.d(TAG, "onOptionsItemSelected()");
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void bindServicePressed(View view)
    {
        Log.d(TAG, "bindServicePressed()");
        bindService(new Intent(ServiceActivity.this, BarebonesService.class), mConnection, Context.BIND_AUTO_CREATE);
    }

    public void unbindServicePressed(View view)
    {
        Log.d(TAG, "unbindServicePressed()");
        unbindService(mConnection);

    }



}
