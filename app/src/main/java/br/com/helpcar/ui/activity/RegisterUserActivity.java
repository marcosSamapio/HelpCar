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

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import br.com.helpcar.R;
import br.com.helpcar.model.User;
import br.com.helpcar.util.CheckField;
import br.com.helpcar.viewModel.UserViewModel;

import static br.com.helpcar.R.id.textPassword;
import static br.com.helpcar.R.id.textUserCPF;
import static br.com.helpcar.R.id.textUserEmail;
import static br.com.helpcar.R.id.loginEmail;

public class RegisterUserActivity extends AppCompatActivity {

    private EditText fieldUserName;
    private EditText fieldUserCPF;
    private EditText fieldUserEmail;
    private EditText fieldPassword;
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
        fieldUserName = findViewById(loginEmail);
        fieldUserCPF = findViewById(textUserCPF);
        fieldUserEmail = findViewById(textUserEmail);
        fieldPassword = findViewById(textPassword);

        //Criando máscara para campo cpf

        SimpleMaskFormatter smf = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher mtw = new MaskTextWatcher(fieldUserCPF, smf);
        fieldUserCPF.addTextChangedListener(mtw);
            }
        //Fim da máscara
    private void configConfirmButton() {
        Button confirmButton = findViewById(R.id.btnLogin);
        confirmButton.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean empty = checkFields();
                if(!empty) {
                    Boolean created = createRegister();
                    if(created) {
                        startActivity(new Intent(context, LoginActivity.class));
                        finish();
                    }
                }
            }
        });
    }

    private Boolean checkFields() {
        Boolean empty = CheckField.isEmpty(fieldUserName);
        if(!empty) {
            empty = CheckField.isEmpty(fieldUserCPF);
            if(!empty) {
                empty = CheckField.isEmpty(fieldUserEmail);
                if(!empty) {
                    return false;
                }
            } else {
                CheckField.isEmpty(fieldUserName);
                CheckField.isEmpty(fieldUserEmail);
                return true;
            }
        } else {
            CheckField.isEmpty(fieldUserCPF, 11);
            CheckField.isEmpty(fieldUserEmail);
            return true;
        }
        return true;
    }

    private Boolean createRegister() {
        String username = fieldUserName.getText().toString();
        String userCpf = fieldUserCPF.getText().toString();
        String userEmail = fieldUserEmail.getText().toString();
        String userPassword = fieldPassword.getText().toString();
        Boolean passwordVeryfied = verifyPassword(userPassword);
        if (passwordVeryfied) {
            user.setUserName(username);
            user.setUserCpf(userCpf);
            user.setUserEmail(userEmail);
            user.setUserPassword(userPassword);
            userViewModel.createUser(user);

            Toast.makeText(this, "Cadastro Realizado:\n" +
                    "Nome: " + user.getUserName() +
                    "\nCPF: " +
                    user.getUserCpf() +
                    "\nEmail: " +
                    user.getUserEmail(), Toast.LENGTH_LONG).show();
            return true;
        } else return false;
    }

    private Boolean verifyPassword(String password) {
        EditText fieldConfirmPassword = findViewById(R.id.textConfirmPassword);
        String confirmPassword = fieldConfirmPassword.getText().toString();
        if(password.equals(confirmPassword)) {
            return true;
        } else return false;
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
