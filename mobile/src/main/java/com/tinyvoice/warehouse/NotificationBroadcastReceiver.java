package com.tinyvoice.warehouse;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Not implemented, rests here just in case I decide to implement Broadcast receiver
 * filter is added in manifest
 */

public class NotificationBroadcastReceiver extends BroadcastReceiver {
    public NotificationBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //String action = intent.getAction();
        //Toast a message with the string action
        Toast.makeText(context, "Got a broadcast: " + intent.getAction(), Toast.LENGTH_LONG).show();

//        throw new UnsupportedOperationException("Not yet implemented");
    }

}
