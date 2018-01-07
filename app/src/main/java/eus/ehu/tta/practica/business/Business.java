package eus.ehu.tta.practica.business;

/**
 * Created by jontx on 06/01/2018.
 */

public class Business implements BusinessInterface {
    @Override
    public boolean authenticate(String login, String password) {

        return password.compareTo("1234") == 0;
    }

    @Override
    public Exercise getExercise() {
        Exercise exercise = new Exercise("Explica cómo aplicarías el patrón de diseño MVP en el desarrollo de una app para Android");

        return exercise;
    }
}
