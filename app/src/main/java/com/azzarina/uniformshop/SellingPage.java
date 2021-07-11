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
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class SellingPage extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout textInputEmail;
    private TextInputLayout textInputPhone;
    private TextInputLayout textInputDescription;
    private TextInputLayout textInputPrice;
    private RadioGroup selectedButton;
    private Spinner categorySpinner;
    private Spinner sizeSpinner;
    private Spinner conditionSpinner;

    Spinner spinner;
    Button btnSelectImage;
    ImageView imgView;

    static final int SELECT_IMAGE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selling_page);

        // For validators
        textInputEmail = findViewById(R.id.text_input_email);
        textInputPhone = findViewById(R.id.text_input_phone);
        textInputDescription = findViewById(R.id.text_input_desc);
        textInputPrice = findViewById(R.id.text_input_price);
        selectedButton = findViewById(R.id.radioGroup);
        categorySpinner = findViewById(R.id.category_spinner);
        sizeSpinner = findViewById(R.id.size_spinner);
        conditionSpinner = findViewById(R.id.condition_spinner);

        // For fetching data
        spinner = (Spinner) findViewById(R.id.category_spinner);
        btnSelectImage = findViewById(R.id.btnSelectImage);
        imgView = findViewById(R.id.imgView);
        btnSelectImage.setOnClickListener(this);

        handlePermission(); // ask permission to access external storage (gallery)
    }

    // Email validator
    // Required field to enter or else error message will appear
    private boolean validateEmail(){
        String emailInput = textInputEmail.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()){
            textInputEmail.setError("Field can't be empty");
            return false;
        } else {
            textInputEmail.setError(null);
            return true;
        }
    }

    // Phone number validator
    // Required field to enter or else error message will appear
    // Phone number must be < 10 or else error message will appear
    private boolean validatePhone(){
        String phoneInput = textInputPhone.getEditText().getText().toString().trim();

        if (phoneInput.isEmpty()){
            textInputPhone.setError("Field can't be empty");
            return false;
        } else if (phoneInput.length() > 10){
            textInputPhone.setError("Phone too long");
            return false;
        } else {
            textInputPhone.setError(null);
            return true;
        }
    }

    // Price validator
    // Required field to enter or else error message will appear
    private boolean validatePrice(){
        String priceInput = textInputPrice.getEditText().getText().toString().trim();

        if (priceInput.isEmpty()){
            textInputPrice.setError("Field can't be empty");
            return false;
        } else {
            textInputPrice.setError(null);
            return true;
        }
    }

    // Description validator
    // Description must be < 150 or else error message will appear
    private boolean validateDescription(){
        String descriptionInput = textInputDescription.getEditText().getText().toString().trim();

        if (descriptionInput.length() > 150){
            textInputDescription.setError("Description too long");
            return false;
        } else {
            textInputDescription.setError(null);
            return true;
        }
    }

    // Student Level validator
    // Required to select a radio button or else toast message will appear
    private boolean validateLevel() {

        if (selectedButton.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getApplicationContext(), "Please select a level", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    // Category validator
    // Restrict users from selecting first array; 'Select One'
    private boolean validateCategory() {
        if (categorySpinner.getSelectedItem().toString().trim().equals("Select One")) {
            Toast.makeText(getApplicationContext(), "Please select a category", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    // Size validator
    // Restrict users from selecting first array; 'Select One'
    private boolean validateSize() {
        if (sizeSpinner.getSelectedItem().toString().trim().equals("Select One")) {
            Toast.makeText(getApplicationContext(), "Please select a size", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    // Condition validator
    // Restrict users from selecting first array; 'Select One'
    private boolean validateCondition() {
        if (conditionSpinner.getSelectedItem().toString().trim().equals("Select One")) {
            Toast.makeText(getApplicationContext(), "Please enter the condition of your product", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void listing_overview (View aView) {
        // Call all validators
        if (!validateEmail() | !validatePhone() | !validateDescription() | !validatePrice() | !validateLevel() | !validateCategory() | !validateSize() | !validateCondition()){
            return;
        }

        { Intent i = new Intent(this, ListingOverview.class);

        // Identify where to fetch data from

        EditText et_price = (EditText) findViewById(R.id.setPrice);
        EditText et_desc = (EditText) findViewById(R.id.extraDescription);
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);
        Spinner sp_category = (Spinner) findViewById(R.id.category_spinner);
        Spinner sp_size = (Spinner) findViewById(R.id.size_spinner);
        Spinner sp_condition = (Spinner) findViewById(R.id.condition_spinner);
        EditText et_email = (EditText) findViewById(R.id.email);
        EditText et_phone = (EditText) findViewById(R.id.phone);

        // Fetch data entered by users

        String price = et_price.getText().toString();
        String desc = et_desc.getText().toString();
        String checked = ((RadioButton)findViewById(rg.getCheckedRadioButtonId() )).getText().toString();
        String category = sp_category.getSelectedItem().toString();
        String size = sp_size.getSelectedItem().toString();
        String condition = sp_condition.getSelectedItem().toString();
        String email = et_email.getText().toString();
        String phone = et_phone.getText().toString();

        i.putExtra("price", price);
        i.putExtra("description", desc);
        i.putExtra("checked", checked);
        i.putExtra("category", category);
        i.putExtra("size", size);
        i.putExtra("condition", condition);
        i.putExtra("email", email);
        i.putExtra("phone", phone);

        startActivity(i);}
    }

    // Permission to access external storage for when users need to upload product image
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
                            // User tapped 'Never ask again'
                            showSettingsAlert();
                        }
                    }else{
                        // PASS
                    }
                }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    // For when user taps 'Never ask again'
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

    // Upload Image option for product listing
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
                        assert data != null;
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

    // 'Upload Image' button onclick
    @Override
    public void onClick(View v) {
        openImageChooser();
    }



}