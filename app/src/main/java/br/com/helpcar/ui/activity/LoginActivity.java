package br.com.helpcar.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import br.com.helpcar.R;
import br.com.helpcar.model.User;
import br.com.helpcar.util.CheckField;
import br.com.helpcar.viewModel.UserViewModel;

public class LoginActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    private EditText email;
    private EditText password;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        context = this;
        configFields();
        configLoginButton();
        configNewUserButton();
    }

    private void configNewUserButton() {
        Button btnRegisterNewUser = findViewById(R.id.btnRegisterNewUser);
        btnRegisterNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, RegisterUserActivity.class));
            }
        });
    }

    private void configFields() {
        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);
    }

    private void configLoginButton() {
        Button loginButton = findViewById(R.id.btnLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean empty = fieldIsEmpty();
                if(!empty) {
                    User user = userViewModel.verifyLogin(
                            email.getText().toString(),
                            password.getText().toString()
                    );
                    if (user != null) {
                        Intent intent = new Intent(context, CalledList.class);
                        intent.putExtra("userIdSession", user.getUserId());
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(context, "Erro Login!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private Boolean fieldIsEmpty() {
        Boolean empty = CheckField.isEmpty(email);
        if(!empty) {
            empty = CheckField.isEmpty(password);
            if(!empty) {
                return false;
            } else return true;
        }else {
            CheckField.isEmpty(password);
            return true;
        }
    }
}
