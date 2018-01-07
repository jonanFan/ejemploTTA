package eus.ehu.tta.practica;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import eus.ehu.tta.practica.business.Exercise;

public class ExerciseActivity extends BaseActivity {

    private final int AUDIO_REQUEST_CODE = 1;
    private final int VIDEO_REQUEST_CODE = 2;
    private final int PICTURE_REQUEST_CODE = 3;
    private final int READ_REQUEST_CODE = 4;
    Uri pictureUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        Exercise exercise = business.getExercise();
        ((TextView) findViewById(R.id.exerciseTitle)).setText(exercise.getHeading());
    }

    public void upload_file(View view) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    public void take_photo(View view) {
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
            Toast.makeText(this, R.string.no_camera, Toast.LENGTH_SHORT).show();
        else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {

                File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

                try {
                    File file = File.createTempFile("tta", "jpg", dir);
                    pictureUri = Uri.fromFile(file);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
                    startActivityForResult(intent, PICTURE_REQUEST_CODE);
                } catch (IOException ex) {

                }
            } else
                Toast.makeText(this, R.string.no_app, Toast.LENGTH_SHORT).show();

        }
    }

    public void record_audio(View view) {
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE))
            Toast.makeText(this, R.string.no_micro, Toast.LENGTH_SHORT).show();
        else {
            Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, AUDIO_REQUEST_CODE);
            } else
                Toast.makeText(this, R.string.no_app, Toast.LENGTH_SHORT).show();

        }
    }

    public void record_video(View view) {
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
            Toast.makeText(this, R.string.no_camera, Toast.LENGTH_SHORT).show();
        else {
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, VIDEO_REQUEST_CODE);
            } else
                Toast.makeText(this, R.string.no_app, Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK)
            return;

        switch (requestCode) {
            case READ_REQUEST_CODE:
            case VIDEO_REQUEST_CODE:
            case AUDIO_REQUEST_CODE:
                /*TODO
                  ¿IMPLEMENTAR LA FUNCION QUE ENVIE LOS FILES AL SERVER? PREGUNTAR GORKA...
                  Esto devuelve un contentResolver... Para acceder a los datos por el contentResolver necesito permisos de lectura?
                */
                Toast.makeText(this, data.getData().toString(), Toast.LENGTH_SHORT).show();
                break;
            case PICTURE_REQUEST_CODE:
                Toast.makeText(this, pictureUri.toString(), Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
