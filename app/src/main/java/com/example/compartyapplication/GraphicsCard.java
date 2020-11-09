package com.example.compartyapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class GraphicsCard extends AppCompatActivity {
    private ComponentViewModel componentViewModel;
    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_COMPONENT_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processor);

        FloatingActionButton buttonAddComponent = findViewById(R.id.button_add_note);
        buttonAddComponent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(GraphicsCard.this, AddEditComponentActivity.class);
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

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                componentViewModel.delete(componentAdapter.getComponentAt(viewHolder.getAdapterPosition()));
                Toast.makeText(GraphicsCard.this, "Component Deleted", Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(recyclerView);

        componentAdapter.setOnItemClickListener(new ComponentAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Component component) {
                Intent intent = new Intent(GraphicsCard.this, AddEditComponentActivity.class);
                intent.putExtra(AddEditComponentActivity.EXTRA_ID, component.getId());
                intent.putExtra(AddEditComponentActivity.EXTRA_TITLE, component.getName());
                intent.putExtra(AddEditComponentActivity.EXTRA_DESCRIPTION, component.getDescription());
                intent.putExtra(AddEditComponentActivity.EXTRA_MANUFACTURER, component.getManufacturer());
                intent.putExtra(AddEditComponentActivity.EXTRA_LINK, component.getLink());
                intent.putExtra(AddEditComponentActivity.EXTRA_PRICE, component.getPrice());
                intent.putExtra(AddEditComponentActivity.EXTRA_TYPE, component.getProductType());
                startActivityForResult(intent, EDIT_COMPONENT_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK){
            String title = data.getStringExtra(AddEditComponentActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddEditComponentActivity.EXTRA_DESCRIPTION);
            String manufacturer = data.getStringExtra(AddEditComponentActivity.EXTRA_MANUFACTURER);
            String link = data.getStringExtra(AddEditComponentActivity.EXTRA_LINK);
            double price = data.getDoubleExtra(AddEditComponentActivity.EXTRA_PRICE, 0.00);
            String type = data.getStringExtra(AddEditComponentActivity.EXTRA_TYPE);


            Component component = new Component(title, 0, description, manufacturer, link, price, type);
            componentViewModel.insert(component);

            Toast.makeText(this, "Component saved", Toast.LENGTH_SHORT).show();
        }
        else if(requestCode == EDIT_COMPONENT_REQUEST && resultCode == RESULT_OK){
            int id = data.getIntExtra(AddEditComponentActivity.EXTRA_ID, -1);

            if(id ==-1)
            {
                Toast.makeText(this,"Component can't be updated",Toast.LENGTH_SHORT).show();
                return;
            }

            String title = data.getStringExtra(AddEditComponentActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddEditComponentActivity.EXTRA_DESCRIPTION);
            String manufacturer = data.getStringExtra(AddEditComponentActivity.EXTRA_MANUFACTURER);
            String link = data.getStringExtra(AddEditComponentActivity.EXTRA_LINK);
            double price = data.getDoubleExtra(AddEditComponentActivity.EXTRA_PRICE, 0.00);
            String type = data.getStringExtra(AddEditComponentActivity.EXTRA_TYPE);

            Component component = new Component(title, 0, description, manufacturer, link, price, type);
            component.setId(id);
            componentViewModel.update(component);

            Toast.makeText(this, "Component updated", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Component not saved", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_all_components:
                componentViewModel.deleteAllComponent();
                Toast.makeText(this,"All components deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}