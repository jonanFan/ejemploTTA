package eus.ehu.tta.practica.presentation;

import android.os.Bundle;

import eus.ehu.tta.practica.business.User;

/**
 * Created by jontx on 06/01/2018.
 * En esta clase se guardan los datos a mantener para todas las actividades. Por ejemplo, login.
 */

public class Data {

    private final static String EXTRA_USERNAME = "eus.ehu.tta.practica.username";
    private final static String EXTRA_PASS = "eus.ehu.tta.practica.password";
    private final static String EXTRA_USER = "eus.ehu.tta.practica.user";

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

    public String getPassword() {
        return bundle.getString(EXTRA_PASS, null);
    }

    public void putPassword(String password) {
        bundle.putString(EXTRA_PASS, password);
    }

    public String getUsername() {
        return bundle.getString(EXTRA_USERNAME, null);
    }


    public void putUser(User user) {
        bundle.putSerializable(EXTRA_USER, user);
    }

    public User getUser() {
        return (User) bundle.getSerializable(EXTRA_USER);
    }
}
