package pw.adithya.jammbanking;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;

import java.util.Locale;

public class TransactionsActivity extends Activity {

    TextToSpeech tts;
    ActionBar aB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        aB = getActionBar();
        aB.setTitle("Transactions");

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                tts.setLanguage(Locale.US);
                tts.setSpeechRate((float)0.7);
                tts.speak("To listen to your latest five transactions, tap on each of the five transactions on the screen. Tap on the back button at the bottom left of the screen to go back to the main menu.", TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        findViewById(R.id.cv1).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                cv1Click(findViewById(R.id.cv1));
                return true;
            }
        });

        findViewById(R.id.cv2).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                cv2Click(findViewById(R.id.cv2));
                return true;
            }
        });

        findViewById(R.id.cv3).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                cv3Click(findViewById(R.id.cv3));
                return true;
            }
        });

        findViewById(R.id.cv4).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                cv4Click(findViewById(R.id.cv4));
                return true;
            }
        });

        findViewById(R.id.cv5).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                cv5Click(findViewById(R.id.cv5));
                return true;
            }
        });
    }

    public void cv1Click (View view)
    {
        tts.speak("$21.50 was withdrawn from your account", TextToSpeech.QUEUE_FLUSH, null);
    }

    public void cv2Click (View view)
    {
        tts.speak("$11.20 was withdrawn from your account", TextToSpeech.QUEUE_FLUSH, null);
    }

    public void cv3Click (View view)
    {
        tts.speak("$300 was deposited to your account", TextToSpeech.QUEUE_FLUSH, null);
    }

    public void cv4Click (View view)
    {
        tts.speak("$120 was deposited to your account", TextToSpeech.QUEUE_FLUSH, null);
    }

    public void cv5Click (View view)
    {
        tts.speak("$39.40 was withdrawn from your account", TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        if(tts != null){
            tts.shutdown();
        }
    }

    @Override
    public void onBackPressed() {
        tts.stop();
        super.onBackPressed();
    }
}
