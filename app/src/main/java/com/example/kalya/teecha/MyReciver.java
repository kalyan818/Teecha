package com.example.kalya.teecha;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent a = new Intent(context, MyService.class);
        context.startService(a);
    }
}
