package br.com.helpcar.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                    if (user == null) {

                    } else {
                        Intent intent = new Intent(context, CalledList.class);
                        intent.putExtra("userIdSession", user.getUserId());
                        startActivity(intent);
                        finish();
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
        }else return true;
    }
}
