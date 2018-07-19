package com.example.broadcasttext2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AnotherBroadCast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "received in AnotherBroadCastReceiver", Toast.LENGTH_SHORT).show();
        abortBroadcast();
    }
}
