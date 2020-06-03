package br.com.helpcar.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
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
    private Context context;
    private int userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        userId = (int) getIntent().getSerializableExtra("userIdSession");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic_called);
        setTitle(R.string.string_calleds);

        configFabNewCalled();
        context = this;
        calledList = findViewById(R.id.calledList);
        calleds = new ArrayList<>();
        configAdapter();
        calledViewModel = new ViewModelProvider(this).get(CalledViewModel.class);
        calledViewModel.listCalleds(userId).observe(this, observe());
        configListView();
    }

    private void configListView() {
        calledList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Called called = (Called) adapter.getItem(position);
                String uri = "https://www.google.com/maps/search/?api=1&query=" +
                        called.getLatitude() + "," +
                        called.getLongitude();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);
            }
        });
    }

    private void configAdapter() {
        String cardTitle = getCardTitle();
        adapter = new CalledListAdapter(context, calleds, cardTitle);
        calledList.setAdapter(adapter);
    }

    private String getCardTitle() {
        return String.valueOf(getResources().getText(R.string.string_called));
    }

    @NotNull
    private Observer<List<Called>> observe() {
        return new Observer<List<Called>>() {
            @Override
            public void onChanged(List<Called> calleds) {
                adapter.setCalleds(calleds);
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
        switch (menuItemId) {
            case R.id.menuItemRegister: startActivity(openProfile());
            break;
            case R.id.menuItemSupport: startActivity(new Intent(this, SupportActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private Intent openProfile() {
        Intent intent = new Intent(this, UpdateUserRegisterActivity.class);
        intent.putExtra("userIdSession", userId);
        return intent;
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
        Intent intent = new Intent(this, CalledForm.class);
        intent.putExtra("userIdSession", userId);
        startActivity(intent);
    }
}
