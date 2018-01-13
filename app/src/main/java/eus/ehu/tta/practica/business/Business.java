package eus.ehu.tta.practica.business;

import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jontx on 06/01/2018.
 */

public class Business implements BusinessInterface {
    @Override
    public User authenticate(String login, String password) {
        User user = null;

        if (password.compareTo("1234") == 0) {
            user = new User();
            user.setUsername("JUAN");
            user.setNextTest(1);
            user.setNextExercise(1);
            user.setLessonNumber(1);
            user.setLessonTitle("Introducción");
        }

        return user;
    }

    @Override
    public Exercise getExercise(int index) {

        return new Exercise("Explica cómo aplicarías el patrón de diseño MVP en el desarrollo de una app para Android");
    }

    @Override
    public Test getTest(int index) {
        List<Choice> choices = new ArrayList<>();

        choices.add(new Choice("Versión de la aplicación", Choice.MIME_HTML, "<b>The manifest describes the components of the application</b>: the activities, services..."));
        choices.add(new Choice("Listado de componentes de la aplicación", Choice.MIME_HTML, "https://egela1718.ehu.eus/"));
        choices.add(new Choice("Opciones del menú de ajustes", null, null));
        //  choices.add(new Choice("Nivel mínimo de la API Android requerida","video", "http://u017633.ehu.eus:28080/static/ServidorTta/AndroidManifest.mp4"));
        // choices.add(new Choice("Nombre del paquete java de la aplicación","audio", "http://u017633.ehu.eus:28080/static/ServidorTta/AndroidManifest.mp4"));
        choices.add(new Choice("Nivel mínimo de la API Android requerida", Choice.MIME_VIDEO, "https://www.w3schools.com/html/mov_bbb.mp4"));
        choices.add(new Choice("Nombre del paquete java de la aplicación", Choice.MIME_AUDIO, "https://www.w3schools.com/html/mov_bbb.mp4"));


        return new Test("¿Cuál de las siguientes opciones NO se indica en el fichero de manifiesto de la app?", choices, 2);

    }

    @Override
    public Boolean sendExercise(int userId, int exerciseId, InputStream inputStream, String filename) {
        Log.d("BUSSINESS", "El filename del file a enviar es " + filename);
        return Boolean.TRUE;
    }

    @Override
    public Boolean sendTest(int userId, int choiceId) throws Exception {
        Log.d("BUSSINESS", "El userId es " + userId + " y el choiceId es " + choiceId);
        return Boolean.TRUE;
    }
}
