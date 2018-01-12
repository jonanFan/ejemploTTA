package eus.ehu.tta.practica;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import eus.ehu.tta.practica.business.BusinessServer;
import eus.ehu.tta.practica.business.RestClient;
import eus.ehu.tta.practica.presentation.Data;

public abstract class BaseActivity extends AppCompatActivity {

    private static final String URL = "http://u017633.ehu.eus:28080/ServidorTta/rest/tta";
    protected Data data;
    protected RestClient rest;
    protected BusinessServer business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new Data(getIntent().getExtras()); //Recoge los datos del Data que le pasa la otra activity;
        rest = new RestClient(URL);
        business = new BusinessServer(rest);
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
