package br.com.helpcar.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import br.com.helpcar.R;

public class SupportActivity extends AppCompatActivity {
    private EditText emailAdress;
    private EditText bodyEmail;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        context = this;
        inicializeFields();
        configConfirmButton();
        configCancelButton();
    }

    private void inicializeFields() {
        emailAdress = findViewById(R.id.textEmail);
        bodyEmail = findViewById(R.id.textBodyEmail);

        emailAdress.setTextIsSelectable(false);
        emailAdress.setText(R.string.string_emailSupport);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home: finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void configConfirmButton() {
        Button confirmButton = findViewById(R.id.btnConfirmCalled);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Email enviado com sucesso!", Toast.LENGTH_LONG).show();
                finish();
            }
        });
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
