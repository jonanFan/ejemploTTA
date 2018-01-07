package eus.ehu.tta.practica.business;

/**
 * Created by jontx on 06/01/2018.
 */

public interface BusinessInterface {

    boolean authenticate(String login, String password);

    Exercise getExercise();
}
