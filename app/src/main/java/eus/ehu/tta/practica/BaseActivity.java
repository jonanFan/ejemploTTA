package eus.ehu.tta.practica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import eus.ehu.tta.practica.business.Business;
import eus.ehu.tta.practica.presentation.Data;

public abstract class BaseActivity extends AppCompatActivity {

    protected Data data;
    protected Business business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new Data(getIntent().getExtras()); //Recoge los datos del Data que le pasa la otra activity;
        business = new Business();
    }

    protected <T> void startBaseActivity(Class<T> tClass) {
        Intent intent=newIntent(tClass);
        startActivity(intent);
    }

    protected <T> void startBaseActivityForResult(Class<T> tClass, int requestCode) {
        Intent intent = newIntent(tClass);
        startActivityForResult(intent, requestCode);
    }

    private <T> Intent newIntent(Class<T> tClass) { //Pasa los datos del Data a traves de un Intent
        Intent intent=new Intent(getApplicationContext(), tClass);
        intent.putExtras(data.getBundle());
        return intent;
    }
}
