package eus.ehu.tta.practica;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        String username=((EditText)findViewById(R.id.username)).getText().toString();
        String passwd=((EditText)findViewById(R.id.passwd)).getText().toString();

        //TODO AUTHENTICATE && PASS PARAMS
        startActivity(intent);

    }
}
