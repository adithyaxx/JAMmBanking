package pw.adithya.jammbanking;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;

import java.util.Locale;

public class MainActivity extends Activity {

    TextToSpeech tts;
    ActionBar aB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aB = getActionBar();
        aB.setTitle("JAM mBanking");

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                tts.setLanguage(Locale.US);
                tts.setSpeechRate((float)0.7);
                tts.speak("Welcome to JAM  m banking. You are on the main screen and there are 4 buttons arranged from the top to the bottom. The 4 buttons are balance, transactions, transfer funds and card services. Tap on the desired button or press and hold each button for further audio guidance", TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        findViewById(R.id.balanceButton).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tts.speak ("balance", TextToSpeech.QUEUE_FLUSH, null);
                return true;
            }
        });

        findViewById(R.id.transactionsButton).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tts.speak ("transactions", TextToSpeech.QUEUE_FLUSH, null);
                return true;
            }
        });

        findViewById(R.id.transferButton).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tts.speak ("transfer funds", TextToSpeech.QUEUE_FLUSH, null);
                return true;
            }
        });

        findViewById(R.id.cardButton).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tts.speak ("card services", TextToSpeech.QUEUE_FLUSH, null);
                return true;
            }
        });
    }

    public void balanceButtonClick(View view)
    {
        Intent i = new Intent(MainActivity.this, BalanceActivity.class);
        Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in_right, R.anim.slide_out_left).toBundle();
        startActivity(i, bndlanimation);
    }

    public void transactionsButtonClick(View view)
    {
        Intent i = new Intent(MainActivity.this, TransactionsActivity.class);
        Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in_right, R.anim.slide_out_left).toBundle();
        startActivity(i, bndlanimation);
    }

    public void transferButtonClick(View view)
    {
        Intent i = new Intent(MainActivity.this, TransferActivity.class);
        Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in_right, R.anim.slide_out_left).toBundle();
        startActivity(i, bndlanimation);
    }

    public void cardButtonClick(View view)
    {
        Intent i = new Intent(MainActivity.this, CardActivity.class);
        Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.slide_in_right, R.anim.slide_out_left).toBundle();
        startActivity(i, bndlanimation);
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
