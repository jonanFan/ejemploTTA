package eus.ehu.tta.practica.business;

/**
 * Created by jontx on 07/01/2018.
 */

public class Exercise {
    private String heading;

    public Exercise() {
        heading = null;
    }

    public Exercise(String heading) {
        this.heading = heading;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }
}
