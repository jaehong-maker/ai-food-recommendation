package com.example.ictapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class TasteTimeActivity extends AppCompatActivity {

    Spinner tasteSpinner;
    RadioGroup timeGroup;
    Button submitBtn, goHomeBtn, autoTimeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taste_time);

        tasteSpinner = findViewById(R.id.taste_spinner);
        timeGroup = findViewById(R.id.time_group);
        submitBtn = findViewById(R.id.submit_btn);
        goHomeBtn = findViewById(R.id.btn_go_home);
        autoTimeBtn = findViewById(R.id.btn_auto_time);

        goHomeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(TasteTimeActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        autoTimeBtn.setOnClickListener(v -> {
            int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            String autoTime;

            if (hour < 11) {
                autoTime = "아침";
            } else if (hour < 17) {
                autoTime = "점심";
            } else {
                autoTime = "저녁";
            }

            // 라디오버튼 중에서 텍스트가 일치하는 것을 찾아 선택
            for (int i = 0; i < timeGroup.getChildCount(); i++) {
                RadioButton rb = (RadioButton) timeGroup.getChildAt(i);
                if (rb.getText().toString().equals(autoTime)) {
                    rb.setChecked(true);
                    Toast.makeText(this, autoTime + " 자동 선택됨", Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        });

        submitBtn.setOnClickListener(v -> {
            String taste = tasteSpinner.getSelectedItem().toString();
            int selectedId = timeGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(this, "식사 시간을 선택하세요.", Toast.LENGTH_SHORT).show();
                return;
            }
            RadioButton selectedTime = findViewById(selectedId);
            String time = selectedTime.getText().toString();

            Intent intent = new Intent(TasteTimeActivity.this, ResultActivity.class);
            intent.putExtra("taste", taste);
            intent.putExtra("time", time);
            startActivity(intent);
        });
    }
}
