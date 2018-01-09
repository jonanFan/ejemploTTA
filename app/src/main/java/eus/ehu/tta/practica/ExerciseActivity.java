package eus.ehu.tta.practica;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import eus.ehu.tta.practica.business.Exercise;

public class ExerciseActivity extends BaseActivity {

    private final int WRITE_PERMISSION_CODE = 1;

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

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
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

                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    take_photo();
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_PERMISSION_CODE);
                }
            } else
                Toast.makeText(this, R.string.no_app, Toast.LENGTH_SHORT).show();

        }
    }

    private void take_photo() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

                try {
                    File file = File.createTempFile("tta", "jpg", dir);
                    pictureUri = Uri.fromFile(file);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
                    startActivityForResult(intent, PICTURE_REQUEST_CODE);
                } catch (IOException ex) {
                }
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_PERMISSION_CODE);
            }
        } else
            Toast.makeText(this, R.string.no_app, Toast.LENGTH_SHORT).show();
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

    private void dumpMetadata(Uri uri) {

        Cursor cursor = getContentResolver().query(uri, null, null, null, null, null);

        try {
            if (cursor != null && cursor.moveToFirst()) {
                String displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                int sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE);
                String size = null;

                if (!cursor.isNull(sizeIndex))
                    size = cursor.getString(sizeIndex);
                else
                    size = "Unknown";

                Toast.makeText(this, "Name: " + displayName + " and Size: " + size, Toast.LENGTH_SHORT).show();
            }
        } finally {
            cursor.close();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK)
            return;

        switch (requestCode) {
            case READ_REQUEST_CODE:
                dumpMetadata(data.getData());
                business.sendFile(data.getData());
                break;
            case VIDEO_REQUEST_CODE:
            case AUDIO_REQUEST_CODE:
                business.sendFile(data.getData());
                break;
            case PICTURE_REQUEST_CODE:
                business.sendFile(pictureUri);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case WRITE_PERMISSION_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    take_photo();
                } else {
                    Toast.makeText(this, R.string.no_write_permission, Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
}
