package pw.adithya.jammbanking;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;

import java.util.Locale;

public class BalanceActivity extends Activity {

    TextToSpeech tts;
    ActionBar aB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);

        aB = getActionBar();
        aB.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3F51B5")));
        aB.setTitle("Balance");

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                tts.setLanguage(Locale.US);
                tts.setSpeechRate((float)0.7);
                tts.speak("To check your balance top on one of the three buttons below, Tap on the back button at the bottom left of the screen to go back to the main menu.", TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        findViewById(R.id.esavingsButton).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                esavingsButtonClick(findViewById(R.id.esavingsButton));
                return true;
            }
        });

        findViewById(R.id.savingsPlusButton).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                savingsPlusButtonClick(findViewById(R.id.savingsPlusButton));
                return true;
            }
        });

        findViewById(R.id.currentButton).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                currentButtonClick(findViewById(R.id.currentButton));
                return true;
            }
        });
    }

    public void esavingsButtonClick(View view)
    {
        tts.speak("Your e savings account has $120", TextToSpeech.QUEUE_FLUSH, null);
    }

    public void savingsPlusButtonClick(View view)
    {
        tts.speak("Your savings plus account has $1100", TextToSpeech.QUEUE_FLUSH, null);
    }

    public void currentButtonClick(View view)
    {
        tts.speak("Your current account has $2320", TextToSpeech.QUEUE_FLUSH, null);
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
