package eus.ehu.tta.practica.business;

import android.net.Uri;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by jontx on 06/01/2018.
 */

public interface BusinessInterface {

    User authenticate(String login, String password) throws IOException, JSONException;

    Exercise getExercise();

    Test getTest();

    void sendFile(Uri uri);
}
