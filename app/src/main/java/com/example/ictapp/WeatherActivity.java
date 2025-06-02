package com.example.ictapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class WeatherActivity extends AppCompatActivity {

    Button sunnyBtn, rainyBtn, snowyBtn, hotBtn, coldBtn,goHomeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        sunnyBtn = findViewById(R.id.btn_sunny);
        rainyBtn = findViewById(R.id.btn_rainy);
        snowyBtn = findViewById(R.id.btn_snowy);
        hotBtn = findViewById(R.id.btn_hot);
        coldBtn = findViewById(R.id.btn_cold);
        goHomeBtn = findViewById(R.id.btn_go_home);

        sunnyBtn.setOnClickListener(v -> goToResult("맑음"));
        rainyBtn.setOnClickListener(v -> goToResult("비"));
        snowyBtn.setOnClickListener(v -> goToResult("눈"));
        hotBtn.setOnClickListener(v -> goToResult("더움"));
        coldBtn.setOnClickListener(v -> goToResult("추움"));


        goHomeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(WeatherActivity.this, MainActivity.class);  // ✅ TasteTimeActivity → MainActivity
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });


    }

    private void goToResult(String weather) {
        Intent intent = new Intent(WeatherActivity.this, ResultActivity.class);
        intent.putExtra("weather", weather);
        intent.putExtra("source", "weather");
        startActivity(intent);
    }

}
