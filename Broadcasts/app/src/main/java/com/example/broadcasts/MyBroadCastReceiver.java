package com.example.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadCastReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent){
        Toast.makeText(context, "receive in mybroadcastreceiver", Toast.LENGTH_SHORT).show();
        abortBroadcast();
    }
}
