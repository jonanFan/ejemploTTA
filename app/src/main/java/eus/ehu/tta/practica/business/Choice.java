package eus.ehu.tta.practica.business;

import java.io.Serializable;

/**
 * Created by jontx on 07/01/2018.
 */

public class Choice implements Serializable {
    public static final String MIME_HTML = "text/html";
    public static final String MIME_AUDIO = "audio";
    public static final String MIME_VIDEO = "video";


    public static final String wordingTag = "answer";
    private String wording;
    public static final String mimeTag = "mime";
    private String mimeType;
    public static final String helpTag = "advise";
    private String help;

    public Choice() {
        this.wording = null;
        this.help = null;
        this.mimeType = null;
    }

    public Choice(String wording) {
        this.wording = wording;
        this.help = null;
        this.mimeType = null;
    }

    public Choice(String wording, String mimeType, String help) {
        this.wording = wording;
        this.help = help;
        this.mimeType = mimeType;
    }

    public String getWording() {
        return wording;
    }

    public void setWording(String wording) {
        this.wording = wording;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}
