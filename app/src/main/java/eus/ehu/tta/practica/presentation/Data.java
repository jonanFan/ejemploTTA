package eus.ehu.tta.practica.presentation;

import android.os.Bundle;

/**
 * Created by jontx on 06/01/2018.
 * En esta clase se guardan los datos a mantener para todas las actividades. Por ejemplo, login.
 */

public class Data {

    private final static String EXTRA_USERNAME = "eus.ehu.tta.practica.username";

    private final Bundle bundle;

    public Data(Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }

        this.bundle = bundle;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void putUsername(String username) {
        bundle.putString(EXTRA_USERNAME, username);
    }

    public String getUsername() {
        return bundle.getString(EXTRA_USERNAME, null);
    }
}