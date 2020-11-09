package com.example.compartyapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditComponentActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
            "com.example.compartyapplication.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.example.compartyapplication.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.example.compartyapplication.EXTRA_DESCRIPTION";
    public static final String EXTRA_MANUFACTURER =
            "com.example.compartyapplication.EXTRA_MANUFACTURER";
    public static final String EXTRA_LINK =
            "com.example.compartyapplication.EXTRA_LINK";
    public static final String EXTRA_PRICE =
            "com.example.compartyapplication.EXTRA_PRICE";
    public static final String EXTRA_TYPE =
            "com.example.compartyapplication.EXTRA_TYPE";

    private EditText editTextTitle;
    private EditText editTextDescription;
    private EditText editTextManufacturer;
    private EditText editTextLink;
    private EditText editTextPrice;
    private EditText editTextType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_component);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        editTextManufacturer = findViewById(R.id.edit_text_manufacturer);
        editTextLink = findViewById(R.id.edit_text_link);
        editTextPrice= findViewById(R.id.edit_text_price);
        editTextType = findViewById(R.id.edit_text_type);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID))
        {
            setTitle("Edit Component");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            editTextManufacturer.setText(intent.getStringExtra(EXTRA_MANUFACTURER));
            editTextLink.setText(intent.getStringExtra(EXTRA_LINK));
            editTextPrice.setText(intent.getStringExtra(EXTRA_PRICE));
            editTextType.setText(intent.getStringExtra(EXTRA_TYPE));
        }
        else {
            setTitle("Add Component");
        }
    }

    private void saveComponent(){
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        String manufacturer = editTextManufacturer.getText().toString();
        String link = editTextLink.getText().toString();
        Double price = Double.parseDouble(editTextPrice.getText().toString());
        String type = editTextType.getText().toString();


        if(title.trim().isEmpty() || description.trim().isEmpty() || manufacturer.trim().isEmpty() || link.trim().isEmpty() || price == null || type.trim().isEmpty()){
            Toast.makeText(this, "Please insert data into all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_MANUFACTURER, manufacturer);
        data.putExtra(EXTRA_LINK, link);
        data.putExtra(EXTRA_PRICE, price);
        data.putExtra(EXTRA_TYPE, type);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1){
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_component_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.save_note:
                saveComponent();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}