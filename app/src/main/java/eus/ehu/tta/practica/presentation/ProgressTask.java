package eus.ehu.tta.practica.presentation;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * Created by jontx on 12/01/2018.
 */

public abstract class ProgressTask<T> extends AsyncTask<Void, Void, T> {

    protected final Context context;
    private final ProgressDialog dialog;
    private Exception e;


    public ProgressTask(Context context, String dialogText) {
        this.context = context;
        dialog = new ProgressDialog(context);
        dialog.setMessage(dialogText);
    }

    @Override
    protected void onPreExecute() {
        dialog.show();
    }

    @Override
    protected T doInBackground(Void... voids) {
        T result = null;

        try {
            result = background();
        } catch (Exception e) {
            this.e = e;
        }

        return result;
    }

    @Override
    protected void onPostExecute(T result) {
        if (dialog.isShowing())
            dialog.dismiss();

        if (e != null)
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        else
            onFinish(result);
    }

    @Override
    protected void onCancelled() {
        if (dialog.isShowing())
            dialog.dismiss();
    }

    protected abstract T background() throws Exception;

    protected abstract void onFinish(T result);
}
