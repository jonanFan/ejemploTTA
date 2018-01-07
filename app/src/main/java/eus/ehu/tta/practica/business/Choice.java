package eus.ehu.tta.practica.business;

/**
 * Created by jontx on 07/01/2018.
 */

public class Choice {
    private String wording;
    private String advice;

    public Choice() {
        this.wording = null;
        this.advice = null;
    }

    public Choice(String wording) {
        this.wording = wording;
    }

    public Choice(String wording, String advice) {
        this.wording = wording;
        this.advice = advice;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}
