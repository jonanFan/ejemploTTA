package eus.ehu.tta.practica;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import eus.ehu.tta.practica.business.Exercise;

public class ExerciseActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        Exercise exercise = business.getExercise();
        ((TextView) findViewById(R.id.exerciseTitle)).setText(exercise.getHeading());
    }

    public void upload_file(View view) {
        Toast.makeText(this, R.string.not_implemented, Toast.LENGTH_SHORT).show();

    }

    public void take_photo(View view) {
        Toast.makeText(this, R.string.not_implemented, Toast.LENGTH_SHORT).show();

    }

    public void record_audio(View view) {
        Toast.makeText(this, R.string.not_implemented, Toast.LENGTH_SHORT).show();

    }

    public void record_video(View view) {
        Toast.makeText(this, R.string.not_implemented, Toast.LENGTH_SHORT).show();

    }
}
