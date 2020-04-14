package br.com.helpcar.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import br.com.helpcar.R;
import br.com.helpcar.model.Called;
import br.com.helpcar.model.User;
import br.com.helpcar.ui.adapter.CalledListAdapter;
import br.com.helpcar.viewModel.CalledViewModel;
import br.com.helpcar.viewModel.UserViewModel;

public class CalledList extends AppCompatActivity {

    private CalledListAdapter adapter;
    private List<Called> calleds;
    private ListView calledList;
    private CalledViewModel calledViewModel;
    private UserViewModel userViewModel;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        User user = userViewModel.getUser();
        if (user == null) {
            startActivity(new Intent(this, RegisterUserActivity.class));
            finish();
        }
        setContentView(R.layout.activity_historic_called);
        setTitle(R.string.string_calleds);

        configFabNewCalled();
        context = this;
        calledList = findViewById(R.id.calledList);
        calledViewModel = new ViewModelProvider(this).get(CalledViewModel.class);
        calledViewModel.listCalleds().observe(this, observe());
    }

    @NotNull
    private Observer<List<Called>> observe() {
        return new Observer<List<Called>>() {
            @Override
            public void onChanged(List<Called> calleds) {
                adapter = new CalledListAdapter(context, calleds);
                calledList.setAdapter(adapter);
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemId = item.getItemId();
        if(menuItemId == R.id.menuItemRegister) {
            startActivity(new Intent(this, RegisterUserActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void configFabNewCalled() {
        FloatingActionButton btnNewCalled = findViewById(R.id.fabNewCalled);
        btnNewCalled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalledForm();
            }
        });
    }

    private void openCalledForm() {
        startActivity(new Intent(this, CalledForm.class));
    }
}
