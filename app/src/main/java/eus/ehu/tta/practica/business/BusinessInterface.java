package eus.ehu.tta.practica.business;

import android.net.Uri;

/**
 * Created by jontx on 06/01/2018.
 */

public interface BusinessInterface {

    boolean authenticate(String login, String password);

    Exercise getExercise();

    Test getTest();

    void sendFile(Uri uri);
}
