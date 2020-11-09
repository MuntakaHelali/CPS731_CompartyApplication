package com.example.compartyapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class GraphicsCard extends AppCompatActivity {
    private ComponentViewModel componentViewModel;
    public static final int ADD_NOTE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processor);

        FloatingActionButton buttonAddComponent = findViewById(R.id.button_add_note);
        buttonAddComponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(GraphicsCard.this, AddComponentActivity.class);
                startActivityForResult(intent,ADD_NOTE_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        ComponentAdapter componentAdapter = new ComponentAdapter();
        recyclerView.setAdapter(componentAdapter);

        //componentViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ComponentViewModel.class);
        componentViewModel = ViewModelProviders.of(this).get(ComponentViewModel.class);
        componentViewModel.getAllGPUs().observe(this, new Observer<List<Component>>() {
            // componentViewModel.getAllComponents().observe(this, new Observer<List<Component>>() {
            @Override
            public void onChanged(List<Component> components)
            {
                componentAdapter.setComponent(components);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK){
            String title = data.getStringExtra(AddComponentActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddComponentActivity.EXTRA_DESCRIPTION);
            int priority = data.getIntExtra(AddComponentActivity.EXTRA_PRIORITY, 1);

            Component component = new Component(title, 99, description, "default_manufacturer", "default_link", 420.99, "default_type");
        }
    }
}