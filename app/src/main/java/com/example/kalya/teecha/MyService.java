package com.example.kalya.teecha;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.text.format.Formatter;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class MyService extends Service {
    private FirebaseAuth mAuth;
    DatabaseReference ref1 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://homeauto1998.firebaseio.com/Bulb/");
    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://homeauto1998.firebaseio.com/Builtin/");
    DatabaseReference ref3 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://homeauto1998.firebaseio.com/Fan/");
    DatabaseReference ref4 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://homeauto1998.firebaseio.com/Light/");
    Handler  handler;
    private static final int uniqueID=45612;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        final NotificationCompat.Builder[] notification = {new NotificationCompat.Builder(MyService.this)};

        handler = new Handler();
        mAuth = FirebaseAuth.getInstance();
        ref1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue().toString();
                Toast.makeText(MyService.this,value,Toast.LENGTH_LONG).show();
                SimpleDateFormat time_formatter = new SimpleDateFormat("HH:mm");
                String current_time_str = time_formatter.format(System.currentTimeMillis());
            notification[0] = new NotificationCompat.Builder(MyService.this);
            notification[0].setAutoCancel(true);
            notification[0].setSmallIcon(R.drawable.ic_launcher_background);
            notification[0].setSubText(current_time_str);
            notification[0].setContentTitle(value);
                Intent intent = new Intent(MyService.this,MainActivity.class);
                PendingIntent pd = PendingIntent.getActivities(MyService.this,0, new Intent[]{intent},PendingIntent.FLAG_UPDATE_CURRENT);
            notification[0].setContentIntent(pd);
                NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                nm.notify(uniqueID,notification[0].build());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue().toString();
                Toast.makeText(MyService.this,value,Toast.LENGTH_LONG).show();
                SimpleDateFormat time_formatter = new SimpleDateFormat("HH:mm");
                String current_time_str = time_formatter.format(System.currentTimeMillis());
                notification[0] = new NotificationCompat.Builder(MyService.this);
                notification[0].setAutoCancel(true);
                notification[0].setSmallIcon(R.drawable.ic_launcher_background);
                notification[0].setSubText(current_time_str);
                notification[0].setContentTitle(value);
                Intent intent = new Intent(MyService.this,MainActivity.class);
                PendingIntent pd = PendingIntent.getActivities(MyService.this,0, new Intent[]{intent},PendingIntent.FLAG_UPDATE_CURRENT);
                notification[0].setContentIntent(pd);
                NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                nm.notify(uniqueID,notification[0].build());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue().toString();
                Toast.makeText(MyService.this,value,Toast.LENGTH_LONG).show();
                SimpleDateFormat time_formatter = new SimpleDateFormat("HH:mm");
                String current_time_str = time_formatter.format(System.currentTimeMillis());
                notification[0] = new NotificationCompat.Builder(MyService.this);
                notification[0].setAutoCancel(true);
                notification[0].setSmallIcon(R.drawable.ic_launcher_background);
                notification[0].setSubText(current_time_str);
                notification[0].setContentTitle(value);
                Intent intent = new Intent(MyService.this,MainActivity.class);
                PendingIntent pd = PendingIntent.getActivities(MyService.this,0, new Intent[]{intent},PendingIntent.FLAG_UPDATE_CURRENT);
                notification[0].setContentIntent(pd);
                NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                nm.notify(uniqueID,notification[0].build());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ref4.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue().toString();
                Toast.makeText(MyService.this,value,Toast.LENGTH_LONG).show();
                SimpleDateFormat time_formatter = new SimpleDateFormat("HH:mm");
                String current_time_str = time_formatter.format(System.currentTimeMillis());
                notification[0] = new NotificationCompat.Builder(MyService.this);
                notification[0].setAutoCancel(true);
                notification[0].setSmallIcon(R.drawable.ic_launcher_background);
                notification[0].setSubText(current_time_str);
                notification[0].setContentTitle(value);
                Intent intent = new Intent(MyService.this,MainActivity.class);
                PendingIntent pd = PendingIntent.getActivities(MyService.this,0, new Intent[]{intent},PendingIntent.FLAG_UPDATE_CURRENT);
                notification[0].setContentIntent(pd);
                NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                nm.notify(uniqueID,notification[0].build());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return Service.START_STICKY_COMPATIBILITY;
    }
}
