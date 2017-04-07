package pw.adithya.jammbanking;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class TransactionsActivity extends Activity {

    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                tts.setLanguage(Locale.US);
                tts.setSpeechRate((float)0.7);
                tts.speak("To check your balance top on one of the three buttons below, Tap on the back button at the bottom left of the screen to go back to the main menu.", TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        if(tts != null){
            tts.shutdown();
        }
    }
}
