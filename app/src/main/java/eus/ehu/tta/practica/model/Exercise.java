package eus.ehu.tta.practica.model;

import java.io.Serializable;

/**
 * Created by jontx on 07/01/2018.
 */

public class Exercise implements Serializable {

    private int id;
    private String heading;

    public Exercise() {
        heading = null;
    }

    public Exercise(String heading) {
        this.heading = heading;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }
}
