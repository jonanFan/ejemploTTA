package eus.ehu.tta.practica;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        String username = ((EditText) findViewById(R.id.username)).getText().toString();
        String passwd = ((EditText) findViewById(R.id.passwd)).getText().toString();

        if (username.compareTo("") != 0 && passwd.compareTo("") != 0) {
            if (business.authenticate(username, passwd)) {
                data.putUsername(username);
                startBaseActivity(MenuActivity.class);
            } else
                Toast.makeText(this, R.string.login_error, Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(this, R.string.not_filled, Toast.LENGTH_LONG).show();
    }
}
