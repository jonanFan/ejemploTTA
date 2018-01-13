package eus.ehu.tta.practica;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import eus.ehu.tta.practica.business.User;
import eus.ehu.tta.practica.presentation.ProgressTask;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        final String username = ((EditText) findViewById(R.id.username)).getText().toString();
        final String passwd = ((EditText) findViewById(R.id.passwd)).getText().toString();

        if (username.compareTo("") != 0 && passwd.compareTo("") != 0) {

            new ProgressTask<User>(this, getString(R.string.login_text)) {

                @Override
                protected User background() throws Exception {
                    return business.authenticate(username, passwd);
                }

                @Override
                protected void onFinish(User result) {
                    if (result != null) {

                        data.putUsername(username);
                        data.putPassword(passwd);
                        data.putUser(result);
                        startBaseActivity(MenuActivity.class);
                    } else
                        Toast.makeText(context, R.string.login_error, Toast.LENGTH_LONG).show();
                }
            }.execute();

            /*if (business.authenticate(username, passwd) != null) {
                data.putUsername(username);
                startBaseActivity(MenuActivity.class);
            } else
                Toast.makeText(this, R.string.login_error, Toast.LENGTH_LONG).show();*/
        } else
            Toast.makeText(this, R.string.not_filled, Toast.LENGTH_LONG).show();
    }
}
