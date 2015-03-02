package com.sbullet.barebonesservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Chris Schneider on 3/2/2015.
 */
public class ServiceReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Intent startServiceIntent = new Intent(context, BarebonesService.class);
        context.startService(startServiceIntent);
    }

}
