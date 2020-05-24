package br.com.helpcar.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.helpcar.R;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                goToHomeActivity();
            }
        }, 2000);

    }

    private void goToHomeActivity() {
        Intent intent = new Intent(SplashScreenActivity.this, CalledList.class);
        startActivity(intent);
        finish();
    }
}
