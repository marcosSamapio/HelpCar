package br.com.helpcar.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import br.com.helpcar.R;
import br.com.helpcar.model.Called;
import br.com.helpcar.viewModel.CalledViewModel;

public class CalledForm extends AppCompatActivity {

    private EditText fieldBrandVehicle;
    private EditText fieldModelVehicle;
    private EditText fieldDescription;
    private Called called = new Called();
    private CalledViewModel calledViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_called);
        setTitle(R.string.string_formaCalledTitle);
        inicializingFields();
        configConfirmButton();
        configCancelButton();
        calledViewModel = new ViewModelProvider(this).get(CalledViewModel.class);
    }

    private void inicializingFields() {
        fieldBrandVehicle = findViewById(R.id.textBrandVehicle);
        fieldModelVehicle = findViewById(R.id.textModelVehicle);
        fieldDescription = findViewById(R.id.textDescriptionCalled);
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
