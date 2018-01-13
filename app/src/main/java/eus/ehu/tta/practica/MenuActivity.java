package eus.ehu.tta.practica;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import eus.ehu.tta.practica.business.Exercise;
import eus.ehu.tta.practica.business.Test;
import eus.ehu.tta.practica.business.User;
import eus.ehu.tta.practica.presentation.ProgressTask;

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
        new ProgressTask<Test>(this, getString(R.string.test_loading)) {

            @Override
            protected Test background() throws Exception {
                return business.getTest(user.getNextTest());
            }

            @Override
            protected void onFinish(Test result) {
                if (result != null) {

                    data.putTest(result);
                    startBaseActivity(TestActivity.class);
                } else
                    Toast.makeText(context, R.string.test_error, Toast.LENGTH_LONG).show();
            }
        }.execute();
        //startBaseActivity(TestActivity.class);
    }

    public void newExercise(View view) {
        new ProgressTask<Exercise>(this, getString(R.string.exercise_loading)) {

            @Override
            protected Exercise background() throws Exception {
                return business.getExercise(user.getNextExercise());
            }

            @Override
            protected void onFinish(Exercise result) {
                if (result != null) {

                    data.putExercise(result);
                    startBaseActivity(ExerciseActivity.class);
                } else
                    Toast.makeText(context, R.string.exercise_error, Toast.LENGTH_LONG).show();
            }
        }.execute();


        //startBaseActivity(ExerciseActivity.class);
    }

    public void newTracking(View view) {
        Toast.makeText(this, R.string.not_implemented, Toast.LENGTH_SHORT).show();

    }
}
