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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class SellingPage extends AppCompatActivity implements View.OnClickListener {

    Spinner spinner;
    Button btnSelectImage;
    ImageView imgView;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;
    String filePathString = null;

    static final int SELECT_IMAGE = 1000;
    public static final String EXTRA_MESSAGE = "Set Price";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selling_page);

        spinner = (Spinner) findViewById(R.id.category_spinner);
        btnSelectImage = findViewById(R.id.btnSelectImage);
        imgView = findViewById(R.id.imgView);
        btnSelectImage.setOnClickListener(this);

        handlePermission();
    }



    public void listing_overview (View aView) {

        EditText et_price = (EditText) findViewById(R.id.setPrice);
        EditText et_description = (EditText) findViewById(R.id.extraDescription);
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
        Spinner sp_category = (Spinner) findViewById(R.id.category_spinner);
        Spinner sp_size = (Spinner) findViewById(R.id.size_spinner);
        Spinner sp_condition = (Spinner) findViewById(R.id.condition_spinner);
        EditText et_email = (EditText) findViewById(R.id.EmailAddress);
        EditText et_phone = (EditText) findViewById(R.id.editTextPhone);

        String price = et_price.getText().toString();
        String description = et_description.getText().toString();
        String checked = ((RadioButton)findViewById(rg.getCheckedRadioButtonId() )).getText().toString();
        String category = sp_category.getSelectedItem().toString();
        String size = sp_size.getSelectedItem().toString();
        String condition = sp_condition.getSelectedItem().toString();
        String email = et_email.getText().toString();
        String phone = et_phone.getText().toString();

        Intent i = new Intent(this, listing_overview.class);
        i.putExtra("price",price);
        i.putExtra("description", description);
        i.putExtra("checked", checked);
        i.putExtra("category", category);
        i.putExtra("size", size);
        i.putExtra("condition", condition);
        i.putExtra("email", email);
        i.putExtra("phone", phone);

        startActivity(i);

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