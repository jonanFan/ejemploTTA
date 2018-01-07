package eus.ehu.tta.practica;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import eus.ehu.tta.practica.business.Choice;
import eus.ehu.tta.practica.business.Test;
import eus.ehu.tta.practica.view.VideoPlayer;
import eus.ehu.tta.practica.view.VideoPlayerInterface;

public class TestActivity extends BaseActivity implements View.OnClickListener {

    private Test test; //TODO Preguntar a gorka si esto es posible
    private int selectedChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        test = business.getTest();
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
    }

    public void showTestHelp(View view) {
        View newView = null;
        LinearLayout layout = findViewById(R.id.testLayout);
        RadioGroup choices = findViewById(R.id.testChoices);
        String mimeType = test.getChoices().get(selectedChoice).getMimeType();
        String help = test.getChoices().get(selectedChoice).getHelp();

        if (mimeType.compareTo(Choice.MIME_HTML) == 0) {
            TextView helpView = new TextView(this);
            helpView.setText(help);
            helpView.setVisibility(View.VISIBLE);

            newView = helpView;
        } else if (mimeType.compareTo(Choice.MIME_AUDIO) == 0) {

        } else if (mimeType.compareTo(Choice.MIME_VIDEO) == 0) {
            newView = VideoPlayer.getVideoPlayer(this, help, new VideoPlayerInterface() {
                @Override
                public void onKeyBackPressed() {
                    finish();
                }
            });

            ((VideoView) newView).start();
        } else {
            Toast.makeText(this, R.string.wrong_mime, Toast.LENGTH_SHORT).show();

        }

        if (newView != null)
            layout.addView(newView);

        findViewById(R.id.testHelpButton).setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        findViewById(R.id.testSendButton).setVisibility(View.VISIBLE);
    }
}
