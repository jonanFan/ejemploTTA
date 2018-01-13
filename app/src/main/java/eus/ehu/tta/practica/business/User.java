package eus.ehu.tta.practica.business;

import java.io.Serializable;

/**
 * Created by jontx on 13/01/2018.
 */

public class User implements Serializable {

    private String username;
    public static final String usernameTag = "user";
    private int lessonNumber;
    public static final String lessonNumberTag = "lessonNumber";
    private String lessonTitle;
    public static final String lessonTitleTag = "lessonTitle";
    private int nextTest;
    public static final String nextTestTag = "nextTest";
    private int nextExercise;
    public static final String nextExerciseTag = "nextExercise";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public int getNextTest() {
        return nextTest;
    }

    public void setNextTest(int nextTest) {
        this.nextTest = nextTest;
    }

    public int getNextExercise() {
        return nextExercise;
    }

    public void setNextExercise(int nextExercise) {
        this.nextExercise = nextExercise;
    }
}
