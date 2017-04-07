package pw.adithya.jammbanking;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;

import java.util.Locale;

public class CardActivity extends Activity {

    TextToSpeech tts;
    ActionBar aB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        aB = getActionBar();
        aB.setTitle("Card Services");

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                tts.setLanguage(Locale.US);
                tts.setSpeechRate((float)0.7);
                tts.speak("Pick one of your two cards to pay with. The first card is visa and the second card is mastercard", TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        findViewById(R.id.visa).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tts.speak ("Tap to pay with visa", TextToSpeech.QUEUE_FLUSH, null);
                return true;
            }
        });

        findViewById(R.id.master).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tts.speak ("Tap to pay with mastercard", TextToSpeech.QUEUE_FLUSH, null);
                return true;
            }
        });
    }

    public void visaButton(View view)
    {
        tts.speak("Visa selected", TextToSpeech.QUEUE_FLUSH, null);
    }

    public void masterButton(View view)
    {
        tts.speak("Mastercard selected", TextToSpeech.QUEUE_FLUSH, null);
    }
}
