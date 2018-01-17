package eus.ehu.tta.practica.presentation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by jontx on 15/01/2018.
 */

public class NetworkReceiver extends BroadcastReceiver { //En este ejemplo podrian haber sido llamadas directas a las funciones sin tener que hacerlo con esto

    private NetworkInfo networkInfo;

    public NetworkReceiver() {
        networkInfo = null;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = conn.getActiveNetworkInfo();
    }

    public boolean isConnected() {
        return (networkInfo != null && networkInfo.isConnected());
    }
}
