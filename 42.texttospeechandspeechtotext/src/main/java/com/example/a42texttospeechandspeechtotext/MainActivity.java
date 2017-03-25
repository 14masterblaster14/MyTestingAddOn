package com.example.a42texttospeechandspeechtotext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    protected static final int RESULT_SPEECH = 1;
    TextToSpeech textToSpeech;
    EditText editText;
    TextView txtView;
    int result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        editText = (EditText) findViewById(R.id.EdtSpeak);
        txtView = (TextView) findViewById(R.id.TxtView);
        findViewById(R.id.BtnStartSpeaking).setOnClickListener(this::startSpeaking);
        findViewById(R.id.BtnStopSpeaking).setOnClickListener(this::stopSpeaking);
        findViewById(R.id.BtnStartWriting).setOnClickListener(this::startWriting);

        textToSpeech = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if (status == TextToSpeech.SUCCESS) {

                    result = textToSpeech.setLanguage(Locale.UK);
                } else {

                    Toast.makeText(MainActivity.this, "Feature not supported in your Device", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    private void startSpeaking(View view) {
        String text = editText.getText().toString();

        if (result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA) {

            Toast.makeText(getApplicationContext(), "Feature not supported in your Device", Toast.LENGTH_LONG).show();
        } else {

            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    private void stopSpeaking(View view) {

        if (textToSpeech != null) {
            textToSpeech.stop();
        }
    }

    private void startWriting(View view) {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

        try {
            startActivityForResult(intent, RESULT_SPEECH);
            txtView.setText("");
        } catch (ActivityNotFoundException e) {

            Toast.makeText(getApplicationContext(), "OOPS!! Your device doesn't support Speech to text", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case RESULT_SPEECH: {

                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    txtView.setText(text.get(0));
                }
            }
            break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
}
