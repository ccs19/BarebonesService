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

    private String testString = "asdf";
    private static String TAG = "ServiceActivity";
    private BarebonesService m_barebonesService;
    private ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className,
                                       IBinder binder) {
            Log.d(TAG, "onServiceConnection()");
            BarebonesService.MyBinder b = (BarebonesService.MyBinder) binder;
            m_barebonesService = b.getService();
            Toast.makeText(ServiceActivity.this, "Connected", Toast.LENGTH_SHORT).show();
            testString = "This is a test string";
        }

        public void onServiceDisconnected(ComponentName className) {
            m_barebonesService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

    }


    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        unbindService(mConnection);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
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


    public void startServicePressed(View view)
    {
        Intent intent = new Intent(this, BarebonesService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        Toast.makeText(ServiceActivity.this, testString, Toast.LENGTH_SHORT).show();
        //Get service start time and set text box
        TextView startTime = (TextView) findViewById(R.id.start_time);
        startTime.setText(m_barebonesService.getStartTime());
    }

    public void stopServicePressed(View view)
    {
        m_barebonesService.stopService(new Intent(this, BarebonesService.class));
    }



}
