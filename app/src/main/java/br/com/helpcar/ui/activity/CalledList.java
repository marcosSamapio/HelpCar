package br.com.helpcar.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.com.helpcar.R;
import br.com.helpcar.model.Called;
import br.com.helpcar.ui.adapter.CalledListAdapter;

public class CalledList extends AppCompatActivity {

    private CalledListAdapter adapter;
    private List<Called> calleds;
    private ViewGroup viewRoot;
    private ListView calledList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic_called);
        setTitle(R.string.string_calleds);
        viewRoot = (ViewGroup) findViewById(android.R.id.content);
        calleds = new CalledDAO().getCalleds();
        calledList = (ListView) findViewById(R.id.calledList);
        configCalledList();
        configFabNewCalled();
    }

    @Override
    protected void onResume() {
        super.onResume();
        calleds = new CalledDAO().getCalleds();
        configCalledList();
    }

    private void configCalledList() {
        adapter = new CalledListAdapter(this, calleds);
        calledList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
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
