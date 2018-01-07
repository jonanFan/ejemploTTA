package eus.ehu.tta.practica.business;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Test getTest() {
        List<Choice> choices = new ArrayList<>();

        choices.add(new Choice("Versión de la aplicación", "The manifest describes the components of the application: the activities, services..."));
        choices.add(new Choice("Listado de componentes de la aplicación", "The manifest describes the components of the application: the activities, services..."));
        choices.add(new Choice("Opciones del menú de ajustes", ""));
        choices.add(new Choice("Nivel mínimo de la API Android requerida", ""));
        choices.add(new Choice("Nombre del paquete java de la aplicación", "The manifest describes the components of the application: the activities, services..."));

        return new Test("¿Cuál de las siguientes opciones NO se indica en el fichero de manifiesto de la app?", choices, 2);

    }
}
