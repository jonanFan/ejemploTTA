package eus.ehu.tta.practica;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ((TextView) findViewById(R.id.menuTitle)).setText(getString(R.string.welcome) + " " + data.getUsername());

    }

    public void newTest(View view) {
        startBaseActivity(TestActivity.class);
    }

    public void newExercise(View view) {
        startBaseActivity(EjercicioActivity.class);
    }

    public void newTracking(View view) {
        Toast.makeText(this, R.string.not_implemented, Toast.LENGTH_LONG).show();

    }
}
