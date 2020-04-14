package br.com.helpcar.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import br.com.helpcar.R;
import br.com.helpcar.model.User;
import br.com.helpcar.viewModel.UserViewModel;

import static br.com.helpcar.R.id.textUserCPF;
import static br.com.helpcar.R.id.textUserName;

public class RegisterUserActivity extends AppCompatActivity {

    private EditText fieldUserName;
    private EditText fieldUserCPF;
    private EditText fieldUserEmail;
    private User user = new User();
    private UserViewModel userViewModel;
    private Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_register);
        setTitle(R.string.string_register);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        context = this;
        inicializingFields();
        configConfirmButton();
        configCancelButton();
    }
    private void inicializingFields() {
        fieldUserName = findViewById(textUserName);
        fieldUserCPF = findViewById(textUserCPF);
        fieldUserEmail = findViewById(textUserCPF);
    }

    private void configConfirmButton() {
        Button confirmButton = findViewById(R.id.btnConfirmRegister);
        confirmButton.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createRegister();
                startActivity(new Intent(context, CalledList.class));
                finish();
            }
        });
    }

    private void createRegister() {
        String username = fieldUserName.getText().toString();
        String userCpf = fieldUserCPF.getText().toString();
        String userEmail = fieldUserEmail.getText().toString();
        user.setUserName(username);
        user.setUserCpf(userCpf);
        user.setUserEmail(userEmail);
        userViewModel.createUser(user);

        Toast.makeText(this, "Cadastro Realizado:" +
                user.getUserName() +
                "\n" +
                user.getUserCpf() +
                "\n" +
                user.getUserEmail(), Toast.LENGTH_LONG).show();
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
