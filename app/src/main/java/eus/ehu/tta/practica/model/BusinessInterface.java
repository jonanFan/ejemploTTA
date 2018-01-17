package eus.ehu.tta.practica.model;

import java.io.InputStream;

/**
 * Created by jontx on 06/01/2018.
 */

public interface BusinessInterface {

    User authenticate(String login, String password) throws Exception;

    Exercise getExercise(int index) throws Exception;

    Test getTest(int index) throws Exception;

    Boolean sendExercise(int userId, int exerciseId, InputStream inputStream, String filename) throws Exception;

    Boolean sendTest(int userId, int choiceId) throws Exception;
}
