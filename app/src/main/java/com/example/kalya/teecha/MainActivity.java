package com.example.kalya.teecha;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button abc;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private FirebaseAuth mAuth;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://homeauto1998.firebaseio.com");
    String j="1";
    String valu = "";
    int selected = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            valu = getIntent().getStringExtra("key").toString();
        }catch (Exception e){

        }
        abc = (Button)findViewById(R.id.btn);
        mAuth = FirebaseAuth.getInstance();
        abc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected == 1)
                {
                    int value = 1;
                    if (valu.equals("fan"))
                    {
                        ref.child("fan").setValue(value);
                    }
                    if (valu.equals("light"))
                    {
                        ref.child("light").setValue(value);
                    }
                    if (valu.equals("bulb"))
                    {
                        ref.child("bulb").setValue(value);
                    }
                    if (valu.equals("device"))
                    {
                        ref.child("number").setValue(value);
                    }

                    abc.setText("stop");
                    abc.setBackgroundColor(Color.RED);
                    selected = 0;
                }else {
                    int value = 0;
                    if (valu.equals("fan"))
                    {
                        ref.child("fan").setValue(value);
                    }
                    if (valu.equals("light"))
                    {
                        ref.child("light").setValue(value);
                    }
                    if (valu.equals("bulb"))
                    {
                        ref.child("bulb").setValue(value);
                    }
                    if (valu.equals("device"))
                    {
                        ref.child("number").setValue(value);
                    }
                    ref.child("number").setValue(value);
                    abc.setText("start");
                    abc.setBackgroundColor(Color.GREEN);
                    selected = 1;
                }
                /*if (j.equals("1"))
                {
                    int value = 1;
                        ref.child("number").setValue(value);
                        abc.setText("off");
                        j="0";
                }
                if (j.equals("0"))
                {
                    int value = 0;
                        ref.child("number").setValue(value);
                        abc.setText("on");
                        j="1";
                }*/


            }
        });
    }
}
