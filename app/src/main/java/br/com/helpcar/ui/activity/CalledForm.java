package br.com.helpcar.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModelProvider;

import java.io.ByteArrayOutputStream;
import java.io.File;

import br.com.helpcar.R;
import br.com.helpcar.model.Called;
import br.com.helpcar.viewModel.CalledViewModel;

public class CalledForm extends AppCompatActivity {

    private EditText fieldBrandVehicle;
    private EditText fieldModelVehicle;
    private EditText fieldDescription;
    private CardView imageCardView;
    private ImageView calledImageView;
    private Called called = new Called();
    private CalledViewModel calledViewModel;
    private String photoLocal;
    private String photoBase64;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_called);
        setTitle(R.string.string_formaCalledTitle);
        inicializingFields();
        configConfirmButton();
        configCancelButton();
        calledViewModel = new ViewModelProvider(this).get(CalledViewModel.class);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_camera, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home : finish();
            break;
            case R.id.menuCamera : openCamera();
            break;
            default: return false;
        }
        return false;
    }

    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri photoLocal = definePhotoLocal();
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoLocal);
        startActivityForResult(cameraIntent, 123);
    }

    private Uri definePhotoLocal() {
        photoLocal = getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/" + System.currentTimeMillis() + ".jpg";
        File arquivo = new File(photoLocal);
        return FileProvider.getUriForFile(this, "helpcar.fileprovider", arquivo);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 123 && resultCode == Activity.RESULT_OK) {
            loadPhoto();
        }
    }

    private void loadPhoto() {
        Bitmap bitmap = BitmapFactory.decodeFile(photoLocal);
        Bitmap bm = Bitmap.createScaledBitmap(bitmap, 400, 300, true);
        imageCardView.setVisibility(View.VISIBLE);
        calledImageView.setImageBitmap(bm);
        convertImageToString(bm);
    }

    private void convertImageToString(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        photoBase64 = Base64.encodeToString(b, Base64.DEFAULT);
    }

    private void inicializingFields() {
        fieldBrandVehicle = findViewById(R.id.textBrandVehicle);
        fieldModelVehicle = findViewById(R.id.textModelVehicle);
        fieldDescription = findViewById(R.id.textDescriptionCalled);
        imageCardView = findViewById(R.id.imageCardView);
        calledImageView = findViewById(R.id.calledPhoto);
    }

    private void configConfirmButton() {
        Button confirmButton = findViewById(R.id.btnConfirmCalled);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCalled();
                finish();
            }
        });
    }

    private void createCalled() {
        String vehicleBrand = fieldBrandVehicle.getText().toString();
        String vehicleModel = fieldModelVehicle.getText().toString();
        String calledDescription = fieldDescription.getText().toString();
        called.setBrandVehicle(vehicleBrand);
        called.setModelVehicle(vehicleModel);
        called.setCalledDescription(calledDescription);
        called.setPhotoOfVehicle(photoBase64);
        calledViewModel.createCalled(called);

        Toast.makeText(this, "Novo Chamado:" +
                called.getBrandVehicle() +
                "\n" +
                called.getModelVehicle() +
                "\n" +
                called.getCalledDescription(), Toast.LENGTH_LONG).show();
    }

    private void configCancelButton() {
        Button cancelButton = findViewById(R.id.btnCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
