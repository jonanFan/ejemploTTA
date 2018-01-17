package eus.ehu.tta.practica;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;

import eus.ehu.tta.practica.model.Choice;
import eus.ehu.tta.practica.model.Test;
import eus.ehu.tta.practica.presentation.ProgressTask;
import eus.ehu.tta.practica.view.AudioPlayer;
import eus.ehu.tta.practica.view.VideoPlayer;

public class TestActivity extends BaseActivity implements View.OnClickListener {

    private Test test;
    private int selectedChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //test = business.getTest(1);
        test = data.getTest();
        ((TextView) findViewById(R.id.testTitle)).setText(test.getHeading());

        RadioGroup choices = findViewById(R.id.testChoices);
        for (Choice choice : test.getChoices()) {
            RadioButton radioChoice = new RadioButton(this);
            radioChoice.setText(choice.getWording());
            radioChoice.setOnClickListener(this);
            choices.addView(radioChoice);
        }
    }

    public void sendTest(View view) {
        RadioGroup choices = findViewById(R.id.testChoices);
        for (int i = 0; i < choices.getChildCount(); i++) {
            choices.getChildAt(i).setEnabled(false);
            if (choices.getChildAt(i).getId() == choices.getCheckedRadioButtonId())
                selectedChoice = i;
        }

        choices.getChildAt(test.getCorrectChoice()).setBackgroundColor(Color.GREEN);
        if (selectedChoice != test.getCorrectChoice()) {
            choices.getChildAt(selectedChoice).setBackgroundColor(Color.RED);
            Toast.makeText(this, R.string.wrong_answer, Toast.LENGTH_SHORT).show();

            String mimeType = test.getChoices().get(selectedChoice).getMimeType();
            String help = test.getChoices().get(selectedChoice).getHelp();

            if (mimeType != null && help != null && !help.isEmpty())
                findViewById(R.id.testHelpButton).setVisibility(View.VISIBLE);
        } else
            Toast.makeText(this, R.string.correct_answer, Toast.LENGTH_SHORT).show();

        findViewById(R.id.testSendButton).setVisibility(View.GONE);
        sendSolution(test.getChoices().get(selectedChoice).getId());
    }

    @SuppressLint("StaticFieldLeak")
    public void sendSolution(final int choiceId) {
        final int userId = data.getUser().getId();

        if (network.isConnected()) {

            new ProgressTask<Boolean>(this, getString(R.string.sending_answer)) {
                @Override
                protected Boolean background() throws Exception {
                    return business.sendTest(userId, choiceId);
                }

                @Override
                protected void onFinish(Boolean result) {
                    if (!result)
                   /* Toast.makeText(context, getString(R.string.send_ans_ok), Toast.LENGTH_SHORT).show();
                else*/
                        Toast.makeText(context, getString(R.string.send_ans_error), Toast.LENGTH_SHORT).show();
                }
            }.execute();
        } else
            Toast.makeText(this, R.string.no_internet, Toast.LENGTH_LONG).show();
    }

    public void showTestHelp(View view) {
        View newView = null;
        LinearLayout layout = findViewById(R.id.testLayout);
        RadioGroup choices = findViewById(R.id.testChoices);
        String mimeType = test.getChoices().get(selectedChoice).getMimeType();
        String help = test.getChoices().get(selectedChoice).getHelp();

        findViewById(R.id.testHelpButton).setEnabled(false);

        /*TextView helpView = new TextView(this);
                helpView.setText(help);
                helpView.setVisibility(View.VISIBLE);

                newView = helpView;*/

        if (mimeType.compareTo(Choice.MIME_HTML) == 0) {

            if (URLUtil.isHttpsUrl(help) || URLUtil.isHttpUrl(help)) {
                Uri uri = Uri.parse(help);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            } else {
                WebView webView = new WebView(this);

                webView.loadData(help, Choice.MIME_HTML, null);
                webView.setBackgroundColor(Color.TRANSPARENT);
                webView.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
                newView = webView;
            }
        } else if (mimeType.startsWith(Choice.MIME_AUDIO)) {

            newView = new View(this);

            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            newView.setLayoutParams(params);
            AudioPlayer audioPlayer = new AudioPlayer(newView, new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            });

            try {
                audioPlayer.setAudioUri(help);
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else if (mimeType.startsWith(Choice.MIME_VIDEO)) {
            newView = VideoPlayer.getVideoPlayer(this, help, new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            });

            ((VideoView) newView).start();
        } else {
            Toast.makeText(this, R.string.wrong_mime, Toast.LENGTH_SHORT).show();

        }

        if (newView != null)
            layout.addView(newView);

    }

    @Override
    public void onClick(View v) {
        findViewById(R.id.testSendButton).setVisibility(View.VISIBLE);
    }
}
