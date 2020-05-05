package br.com.helpcar.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import br.com.helpcar.R;
import br.com.helpcar.model.User;
import br.com.helpcar.viewModel.UserViewModel;

public class UpdateUserRegisterActivity extends AppCompatActivity {

    private UserViewModel userViewModel;
    private EditText fieldUserName;
    private EditText fieldUserCPF;
    private EditText fieldUserEmail;
    private User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_register);
        setTitle(R.string.string_register);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        user = getUser();
        inicializingFields();
        fillFields();
        configConfirmButton();
        configCancelButton();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void fillFields() {
        fieldUserName.setText(user.getUserName());
        fieldUserCPF.setText(user.getUserCpf());
        fieldUserEmail.setText(user.getUserEmail());
    }

    private User getUser() {
        return userViewModel.getUser();
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

    private void inicializingFields() {
        fieldUserName = findViewById(R.id.textUserName);
        fieldUserCPF = findViewById(R.id.textUserCPF);
        fieldUserEmail = findViewById(R.id.textUserEmail);
        fieldUserName.setText(user.getUserName());
    }

    private void configConfirmButton() {
        Button confirmButton = findViewById(R.id.btnConfirmRegister);
        confirmButton.setText(R.string.string_save);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUser();
                finish();
            }
        });
    }

    private void updateUser() {
        String username = fieldUserName.getText().toString();
        String userCpf = fieldUserCPF.getText().toString();
        String userEmail = fieldUserEmail.getText().toString();
        user.setUserName(username);
        user.setUserCpf(userCpf);
        user.setUserEmail(userEmail);
        userViewModel.updateUser(user);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home : finish();
            break;
            default: return false;
        }
        return false;
    }
}
