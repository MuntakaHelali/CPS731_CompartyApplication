package com.example.compartyapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class processor extends AppCompatActivity {
    private ComponentViewModel componentViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processor);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        ComponentAdapter componentAdapter = new ComponentAdapter();
        recyclerView.setAdapter(componentAdapter);

        componentViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ComponentViewModel.class);
        componentViewModel.getAllNotes().observe(this, new Observer<List<Component>>() {
            @Override
            public void onChanged(List<Component> components)
            {
                componentAdapter.setComponent(components);
            }
        });

    }
}