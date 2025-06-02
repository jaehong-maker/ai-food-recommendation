package com.example.ictapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button tasteTimeBtn, weatherBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tasteTimeBtn = findViewById(R.id.btn_taste_time);
        weatherBtn = findViewById(R.id.btn_weather);

        tasteTimeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TasteTimeActivity.class);
            startActivity(intent);
        });

        weatherBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
            startActivity(intent);
        });
    }
}
