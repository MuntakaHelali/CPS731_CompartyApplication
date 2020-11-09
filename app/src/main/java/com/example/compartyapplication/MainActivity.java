package com.example.compartyapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private ComponentViewModel componentViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button processor = findViewById(R.id.btn_cpu);
        processor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, Processor.class));
            }
        });

        Button graphics_card = findViewById(R.id.btn_gpu);
        graphics_card.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, GraphicsCard.class));
            }
        });

        Button ram = findViewById(R.id.btn_ram);
        ram.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, RAM.class));
            }
        });

        Button motherBoard = findViewById(R.id.btn_mobo);
        motherBoard.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, Motherboard.class));
            }
        });

        Button cases = findViewById(R.id.btn_case);
        cases.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, Cases.class));
            }
        });

    }
}