package com.example.caloriyat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView calorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        calorie=findViewById(R.id.calorie);
        Intent j =getIntent();
        double calories=j.getDoubleExtra("How_Much_Should_I_Eat",-1);
        calorie.setText("You need to eat "+calories+" per day");
    }
}