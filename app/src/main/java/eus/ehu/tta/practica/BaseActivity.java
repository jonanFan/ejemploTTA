package eus.ehu.tta.practica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /*protected <T> void startBaseActivity (Class<T> tClass){
        Intent intent=newIntent(tClass);
        startActivity(intent);
    }

    private <T> Intent newIntent(Class<T> tClass){
        Intent intent=new Intent(getApplicationContext(), tClass);
        intent.putExtras()
    }*/
}
