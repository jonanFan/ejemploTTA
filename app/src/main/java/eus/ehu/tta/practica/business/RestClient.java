package eus.ehu.tta.practica.business;

import android.util.Base64;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jontx on 12/01/2018.
 */

public class RestClient {
    private final static String AUTH = "Authorization";
    private final String baseUrl;
    private final Map<String, String> properties = new HashMap<>();

    public RestClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setHttpBasicAuth(String user, String password) {
        String basicAuth = Base64.encodeToString(String.format("%s:%s", user, password).getBytes(), Base64.DEFAULT);
        properties.put(AUTH, String.format("Basic %s", basicAuth));
    }

    public String getAuthorization() {
        return properties.get(AUTH);
    }

    public void setAuthorization(String auth) {
        properties.put(AUTH, auth);
    }

    public void setProperty(String name, String value) {
        properties.put(name, value);
    }

    private HttpURLConnection getConnection(String path) throws IOException {
        URL url = new URL(String.format("%s/%s", baseUrl, path));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        for (Map.Entry<String, String> property : properties.entrySet()) {
            conn.setRequestProperty(property.getKey(), property.getValue());
        }
        conn.setUseCaches(false);

        return conn;
    }

    public JSONObject getJson(String path) throws IOException, JSONException {
        return null;
    }

    public int postFile(String path, InputStream inputStream, String filename) throws IOException {
        return 0;
    }

    public int postJson(final JSONObject json, String path) throws IOException {
        return 0;
    }


}
