package br.com.helpcar.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import br.com.helpcar.R;
import br.com.helpcar.model.Called;
import br.com.helpcar.ui.fragment.MapsFragment;

public class Maps extends AppCompatActivity {
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_maps);

        Called called = (Called) getIntent().getSerializableExtra("called");
        String title = String.valueOf(getResources().getText(R.string.string_called));
        setTitle(title +
                " " +
                called.getCalledId());


        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        bundle.putSerializable("called", called);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = new Fragment();
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.framePrincipal, new MapsFragment());
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            finish();
        }
        return false;
    }
}
