package eus.ehu.tta.practica.business;

import java.util.List;

/**
 * Created by jontx on 07/01/2018.
 */

public class Test {

    private String heading;
    private int correctChoice;
    private List<Choice> choices;

    public Test() {
        this.heading = null;
        this.choices = null;
        this.correctChoice = -1;
    }

    public Test(String heading) {
        this.heading = heading;
        this.choices = null;
        this.correctChoice = -1;
    }

    public Test(String heading, List<Choice> choices, int correctChoice) {
        this.heading = heading;
        this.choices = choices;
        this.correctChoice = correctChoice;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public int getCorrectChoice() {
        return correctChoice;
    }

    public void setCorrectChoice(int correctChoice) {
        this.correctChoice = correctChoice;
    }
}
