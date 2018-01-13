package eus.ehu.tta.practica.business;

import android.net.Uri;

/**
 * Created by jontx on 06/01/2018.
 */

public interface BusinessInterface {

    User authenticate(String login, String password) throws Exception;

    Exercise getExercise(int index) throws Exception;

    Test getTest(int index) throws Exception;

    void sendFile(Uri uri);
}
