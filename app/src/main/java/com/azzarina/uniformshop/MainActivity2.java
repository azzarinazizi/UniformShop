package com.azzarina.uniformshop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{
 Spinner spinner;
 Button btnSelectImage;
 ImageView imgView;
 static final int SELECT_IMAGE = 1000;

 public static final String EXTRA_MESSAGE = "Description Entry";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        spinner = (Spinner) findViewById(R.id.category_spinner);
//        Button enterButton = (Button) findViewById(R.id.enterButton);
//        enterButton.setTransformationMethod(null);

        btnSelectImage = findViewById(R.id.btnSelectImage);
        imgView = findViewById(R.id.imgView);

        btnSelectImage.setOnClickListener(this);

        handlePermission();

    }

    public void sendMessage (View aView) {
        Intent intent = new Intent(this,MainActivity3.class);
        EditText editText = (EditText) findViewById(R.id.extraDescription);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
}

    void handlePermission(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            // Don't need for versions less than M
            return;
        }
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PermissionChecker.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    SELECT_IMAGE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case SELECT_IMAGE:
                for(int i = 0; i < permissions.length; i++){
                    String permission = permissions [i];
                    if(grantResults[i] == PackageManager.PERMISSION_DENIED) {
                        boolean showRationale = ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
                        if (showRationale) {
                            // Show own message
                        } else {
                            // user tapped never ask again
                            showSettingsAlert();
                        }
                    }else{
                        //we are good
                    }
                }

        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void showSettingsAlert() {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("App Needs Access to the Storage");
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "DON'T ALLOW", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                openAppSettings();
            }
        });
    }

    private void openAppSettings() {
        Intent i = new Intent();
        i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.setData(Uri.parse("package:" + getPackageName()));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        startActivity(i);
    }

    void openImageChooser(){
       Intent intent = new Intent();
       intent.setType("image/*");
       intent.setAction(Intent.ACTION_GET_CONTENT);
       startActivityForResult(Intent.createChooser(intent, "Select Image"), SELECT_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(resultCode == RESULT_OK){
                    if(requestCode == SELECT_IMAGE){
                        final Uri selectedImageUri = data.getData();
                        if(null != selectedImageUri){
                            imgView.post(new Runnable() {
                                @Override
                                public void run() {
                                    imgView.setImageURI(selectedImageUri);
                                }
                            });
                        }
                    }
                }
            }
        }).start();
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        openImageChooser();

    }
}