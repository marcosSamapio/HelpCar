package br.com.helpcar.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.provider.MediaStore;
import android.provider.Settings;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.io.ByteArrayOutputStream;
import java.io.File;

import br.com.helpcar.R;
import br.com.helpcar.model.Called;
import br.com.helpcar.viewModel.CalledViewModel;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

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
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Location location;

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

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getLocation();
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
        called.setLatitude(location.getLatitude());
        called.setLongitude(location.getLongitude());
        calledViewModel.createCalled(called);

        Toast.makeText(this, "Novo Chamado:" +
                called.getLatitude() +
                "\n" +
                called.getLongitude(), Toast.LENGTH_LONG).show();
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

    private void getLocation() {
        if (checkPermission()) {
            if (isLocationEnable()) {
                requestActualLocation();
            } else {
                Toast.makeText(this, "Ligue o GPS", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else requestLocationPermission();
    }

    private void requestActualLocation() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setSmallestDisplacement(10.0F);

        fusedLocationProviderClient.requestLocationUpdates(
                locationRequest, locationCallback, Looper.myLooper()
        );


    }

    private LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            location = locationResult.getLastLocation();
        }
    };

    private boolean checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{ACCESS_FINE_LOCATION}, 123);
    }

    private boolean isLocationEnable() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 123 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getLocation();
        } else {
            Toast.makeText(this, "Não será possível pegar a localização sem permissão.", Toast.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }
}
