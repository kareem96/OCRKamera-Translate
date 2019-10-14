package com.abdulkarim.ocrkameratranslate;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.abdulkarim.ocrkameratranslate.API.RetrofitClient;
import com.abdulkarim.ocrkameratranslate.Constant.Constant;
import com.abdulkarim.ocrkameratranslate.Model.TranslateResponse;
import com.abdulkarim.ocrkameratranslate.Utility.Dialog;
import com.abdulkarim.ocrlibrary.OCRCapture;
import com.google.android.gms.common.api.CommonStatusCodes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.abdulkarim.ocrlibrary.OcrCaptureActivity.TextBlockObject;


public class MainActivity extends AppCompatActivity {



    private TextView textView, textView2;
    private final int CAMERA_SCAN_TEXT = 0;
    private final int LOAD_IMAGE_RESULTS = 1;

    String text = null;
    String lang = "en-id";
    String key = Constant.API_KEY;
    Call<TranslateResponse> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
        
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionCamera:
                OCRCapture.Builder(this)
                        .setUseFlash(false)
                        .setAutoFocus(true)
                        .buildWithRequestCode(CAMERA_SCAN_TEXT);
                break;
            case R.id.actionAbout:
                Dialog.showDialog(this, R.string.about, R.string.is);
//                if (hasPermission()) {
//                    pickImage();
//                } else {
//                    getPermission();
//                }
                break;
            case R.id.actionCredits:
                Dialog.showDialog(this, R.string.credits, R.string.isi);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getPermission() {
// Permission is not granted
        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //TODO:
        } else {
            // No explanation needed; request the permission
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    pickImage();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    private void pickImage() {
        Intent intentGallery = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intentGallery, LOAD_IMAGE_RESULTS);
    }

    private boolean hasPermission() {
        return ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == CAMERA_SCAN_TEXT) {
                if (resultCode == CommonStatusCodes.SUCCESS) {
                    textView.setText(data.getStringExtra(TextBlockObject));
                    String ab = data.getStringExtra(TextBlockObject);

                    call = RetrofitClient.getRetrofitClient().getTranslate(key, ab, lang);
                    call.enqueue(new Callback<TranslateResponse>() {
                        @Override
                        public void onResponse(Call<TranslateResponse> call, Response<TranslateResponse> response) {
                            TranslateResponse tr = response.body();

                            if (tr != null){
                                try {
                                    for (int i = 0; i < tr.getText().size(); i++){
                                        textView2.setText(tr.getText().get(0));
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<TranslateResponse> call, Throwable t) {

                        }
                    });

                }
            } else if (requestCode == LOAD_IMAGE_RESULTS) {
                Uri pickedImage = data.getData();
                text = OCRCapture.Builder(this).getTextFromUri(pickedImage);
                textView.setText(text);
            }

        }
    }

    private void loadTranslate(){

    }
}
