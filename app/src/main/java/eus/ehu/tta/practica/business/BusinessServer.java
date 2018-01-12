package eus.ehu.tta.practica.business;

import android.net.Uri;

/**
 * Created by jontx on 12/01/2018.
 */

public class BusinessServer implements BusinessInterface {

    private final RestClient rest;

    public BusinessServer(RestClient rest) {
        this.rest = rest;
    }

    @Override
    public boolean authenticate(String login, String password) {
        return false;
    }

    @Override
    public Exercise getExercise() {
        return null;
    }

    @Override
    public Test getTest() {
        return null;
    }

    @Override
    public void sendFile(Uri uri) {

    }
}
