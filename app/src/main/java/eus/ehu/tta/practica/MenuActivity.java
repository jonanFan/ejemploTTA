package eus.ehu.tta.practica;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import eus.ehu.tta.practica.business.User;

public class MenuActivity extends BaseActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        user = data.getUser();

        String menuTitle = getString(R.string.welcome) + " " + user.getUsername();
        String lessonTitle = getString(R.string.lesson) + " " + user.getLessonNumber() + ": " + user.getLessonTitle();

        ((TextView) findViewById(R.id.menuTitle)).setText(menuTitle);
        ((TextView) findViewById(R.id.lessonTitle)).setText(lessonTitle);

    }

    public void newTest(View view) {
        startBaseActivity(TestActivity.class);
    }

    public void newExercise(View view) {
        startBaseActivity(ExerciseActivity.class);
    }

    public void newTracking(View view) {
        Toast.makeText(this, R.string.not_implemented, Toast.LENGTH_SHORT).show();

    }
}
