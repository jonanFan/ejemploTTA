package eus.ehu.tta.practica.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

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
            user = new User();
            user.setId(json.getInt("id"));
            user.setUsername(json.getString("user"));
            user.setLessonNumber(json.getInt("lessonNumber"));
            user.setLessonTitle(json.getString("lessonTitle"));
            user.setNextTest(json.getInt("nextTest"));
            user.setNextExercise(json.getInt("nextExercise"));
        }

        return user;
    }

    @Override
    public Exercise getExercise(int index) throws JSONException, IOException {
        Exercise exercise = null;
        JSONObject json = rest.getJson(String.format("getExercise?id=%s", index));

        if (json != null) {
            exercise = new Exercise();
            exercise.setId(json.getInt("id"));
            exercise.setHeading(json.getString("wording"));
        }

        return exercise;
    }

    @Override
    public Test getTest(int index) throws IOException, JSONException {

        Test test = null;
        int correctChoice = 0;

        JSONObject json = rest.getJson(String.format("getTest?id=%s", index));

        if (json != null) {
            test = new Test();
            List<Choice> choices = new ArrayList<>();
            Log.d("REST", json.toString());
            test.setHeading(json.getString("wording"));
            JSONArray jsonArray = json.getJSONArray("choices");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonChoice = jsonArray.getJSONObject(i);
                Choice choice = new Choice();
                choice.setWording(jsonChoice.getString("answer"));
                choice.setHelp(jsonChoice.getString("advise"));

                if (!jsonChoice.isNull("resourceType")) {
                    JSONObject resourceType = jsonChoice.getJSONObject("resourceType");
                    choice.setMimeType(resourceType.getString("mime"));
                }

                if (jsonChoice.getBoolean("correct")) {
                    correctChoice = i;
                }

                choices.add(choice);
            }

            test.setCorrectChoice(correctChoice);
            test.setChoices(choices);

        }


        return test;
    }

    @Override
    public Boolean sendExercise(int userId, int exerciseId, InputStream inputStream, String filename) throws IOException {

        int httpResponseCode;

        if (inputStream != null && filename != null && !filename.isEmpty()) {
            httpResponseCode = rest.postFile(String.format("postExercise?user=%s&id=%s", userId, exerciseId), inputStream, filename);
            if (httpResponseCode == HttpURLConnection.HTTP_OK || httpResponseCode == HttpURLConnection.HTTP_NO_CONTENT)
                return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    @Override
    public Boolean sendTest(int userId, int choiceId) throws Exception {

        JSONObject json = new JSONObject();
        json.put("userId", userId);
        json.put("choiceId", choiceId);
        int httpResponseCode = rest.postJson(json, "postChoice");

        if (httpResponseCode == HttpURLConnection.HTTP_OK || httpResponseCode == HttpURLConnection.HTTP_NO_CONTENT)
            return Boolean.TRUE;

        return Boolean.FALSE;
    }
}
