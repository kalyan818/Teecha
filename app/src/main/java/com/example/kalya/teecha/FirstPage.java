package com.example.kalya.teecha;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.Manifest;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Locale;

public class FirstPage extends AppCompatActivity {
    RelativeLayout fan,light,bulb,device;
    ImageView mic;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://homeauto1998.firebaseio.com");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        final SpeechRecognizer mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        final Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());
        Intent a = new Intent(FirstPage.this, MyService.class);
        FirstPage.this.startService(a);
        fan = (RelativeLayout)findViewById(R.id.fan);
        mic = (ImageView)findViewById(R.id.mic);
        light = (RelativeLayout)findViewById(R.id.light);
        bulb = (RelativeLayout)findViewById(R.id.bulb);
        device = (RelativeLayout)findViewById(R.id.device);
        checkPermission();
        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
            }
        });

        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                //getting all the matches
                ArrayList<String> matches = bundle
                        .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                //displaying the first match
                if (matches != null)
                    Toast.makeText(FirstPage.this,matches.get(0),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });
        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptspeech();
            }
        });
        fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstPage.this,MainActivity.class);
                i.putExtra("key","fan");
                startActivity(i);
            }
        });
        light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstPage.this,MainActivity.class);
                i.putExtra("key","light");
                startActivity(i);
            }
        });
        bulb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstPage.this,MainActivity.class);
                i.putExtra("key","bulb");
                startActivity(i);
            }
        });
        device.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FirstPage.this,MainActivity.class);
                i.putExtra("key","device");
                startActivity(i);
            }
        });


    }

    private void promptspeech() {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT,"say somthing");
        try {
            startActivityForResult(i,100);
        }catch (ActivityNotFoundException e)
        {
            Toast.makeText(this,"your device doesnt support",Toast.LENGTH_LONG).show();
        }
    }
    public void onActivityResult(int request_code,int result_code,Intent i)
    {
        super.onActivityResult(request_code,result_code,i);
        switch (request_code)
        {
            case 100:if (result_code==RESULT_OK && i!=null)
            {
                ArrayList<String> result = i.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                String a = result.get(0);
               flow(a);
            }
        }
    }
    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)) {
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + getPackageName()));
                startActivity(intent);
                finish();
            }
        }
    }
    private void flow(String a) {
        if (a.equals("fan on"))
        {
            ref.child("fan").setValue(1);
            Toast.makeText(this,"fan on",Toast.LENGTH_LONG).show();
        }
        if (a.equals("fan off"))
        {
            ref.child("fan").setValue(0);
            Toast.makeText(this,"fan off",Toast.LENGTH_LONG).show();
        }
        if (a.equals("light on"))
        {
            ref.child("light").setValue(1);
            Toast.makeText(this,"light on",Toast.LENGTH_LONG).show();
        }
        if (a.equals("light off"))
        {
            ref.child("light").setValue(0);
            Toast.makeText(this,"light off",Toast.LENGTH_LONG).show();
        }
        if (a.equals("bulb on"))
        {
            ref.child("bulb").setValue(1);
            Toast.makeText(this,"bulb on",Toast.LENGTH_LONG).show();
        }
        if (a.equals("bulb off"))
        {
            ref.child("bulb").setValue(0);
            Toast.makeText(this,"bulb off",Toast.LENGTH_LONG).show();
        }
        if (a.equals("device on"))
        {
            ref.child("number").setValue(1);
            Toast.makeText(this,"device on",Toast.LENGTH_LONG).show();
        }
        if (a.equals("device off"))
        {
            ref.child("number").setValue(0);
            Toast.makeText(this,"device off",Toast.LENGTH_LONG).show();
        }
    }
}
