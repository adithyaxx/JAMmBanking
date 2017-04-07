package pw.adithya.jammbanking;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class TransferActivity extends Activity {

    private final int REQ_CODE_SPEECH_INPUT = 100;
    TextToSpeech tts;
    TextView tv;
    ActionBar aB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        aB = getActionBar();
        aB.setTitle("Transfer Funds");

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                tts.setLanguage(Locale.US);
                tts.setSpeechRate((float)0.7);
                tts.speak("There are 4 sections top to bottom on this screen. Tap on the first section and voice out the account to transfer from. Tap on the second section to voice out the account to transfer to. Tap on the third section to voice out the transfer amount. Finally tap on the fourth section to carry out the transfer.", TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        findViewById(R.id.cvAcc).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tts.speak ("Source account", TextToSpeech.QUEUE_FLUSH, null);
                return true;
            }
        });

        findViewById(R.id.cvAcc2).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tts.speak ("Destination account", TextToSpeech.QUEUE_FLUSH, null);
                return true;
            }
        });

        findViewById(R.id.cvMoney).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tts.speak ("Transfer amount", TextToSpeech.QUEUE_FLUSH, null);
                return true;
            }
        });

        findViewById(R.id.transferButton).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tts.speak ("Finalise funds transfer", TextToSpeech.QUEUE_FLUSH, null);
                return true;
            }
        });
    }

    public void cvAcc(View view)
    {
        tts.stop();
        tv = (TextView)findViewById(R.id.tvAcc);
        askSpeechInput();
    }

    public void cvAcc2(View view)
    {
        tts.stop();
        tv = (TextView)findViewById(R.id.tvAcc2);
        askSpeechInput();
    }

    public void cvMoney(View view)
    {
        tts.stop();
        tv = (TextView)findViewById(R.id.tvMoney);
        askSpeechInput();
    }

    public void transferClick(View view)
    {
        tts.speak("funds transferred successfully", TextToSpeech.QUEUE_FLUSH, null);
    }

    private void askSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if (isNumber(result.get(0))) {
                        tv.setText("$" + String.valueOf(Integer.parseInt(result.get(0))));
                        tts.speak(result.get(0) + " dollars", TextToSpeech.QUEUE_FLUSH, null);
                    }
                    else {
                        tv.setText(result.get(0));
                        tts.speak(result.get(0), TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
                break;
            }
        }
    }

    private boolean isNumber(String word)
    {
        boolean isNumber = false;
        try
        {
            Integer.parseInt(word);
            isNumber = true;
        } catch (NumberFormatException e)
        {
            isNumber = false;
        }
        return isNumber;
    }

    @Override
    public void onBackPressed() {
        tts.stop();
        super.onBackPressed();
    }
}
