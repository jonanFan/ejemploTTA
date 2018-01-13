package eus.ehu.tta.practica.business;

import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by jontx on 12/01/2018.
 */

public class BusinessServer implements BusinessInterface {

    private final RestClient rest;

    public BusinessServer(RestClient rest) {
        this.rest = rest;
    }

    @Override
    public User authenticate(String login, String password) throws IOException, JSONException {
        User user = null;

        rest.setHttpBasicAuth(login, password);
        JSONObject json = rest.getJson(String.format("getStatus?dni=%s", login));

        if (json != null) {
            Log.d("REST", json.toString());
            user = new User();
            user.setUsername(json.getString(User.usernameTag));
            user.setLessonNumber(json.getInt(User.lessonNumberTag));
            user.setLessonTitle(json.getString(User.lessonTitleTag));
            user.setNextTest(json.getInt(User.nextTestTag));
            user.setNextExercise(json.getInt(User.nextExerciseTag));
        }

        return user;
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
