package com.example.caloriyat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText weightValue,heightValue;
    Spinner ageSpinner;
    RadioButton maleRb,femaleRb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        maleRb=findViewById(R.id.maleRb);
        femaleRb=findViewById(R.id.femaleRb);
        weightValue=findViewById(R.id.weightValue);
        heightValue=findViewById(R.id.heightValue);
        ageSpinner=findViewById(R.id.ageSpinner);
        String [] age ={"Below 24","Above 24"};
        ArrayAdapter<String>spinnerAdapter=new ArrayAdapter<String>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,age);
        ageSpinner.setAdapter(spinnerAdapter);

    }
    public void howFit (View v){
        if(TextUtils.isEmpty(weightValue.getText().toString())){
            weightValue.setError("Attention, you must enter your weight first!");
        }
        if(TextUtils.isEmpty(heightValue.getText().toString())) {
            heightValue.setError("Attention, you must enter your height first!");
        }
        double weight=Double.parseDouble(weightValue.getText().toString());
        double height=Double.parseDouble(heightValue.getText().toString())/100;
        double BMI=weight/(height*height);
        if(BMI<16){
            Toast.makeText(MainActivity.this,"You are severely underweight",Toast.LENGTH_LONG).show();
        }
        else if(BMI>=16 && BMI<=18){
            Toast.makeText(MainActivity.this,"You are underweight",Toast.LENGTH_LONG).show();
        }
        else if(BMI>18 && BMI<25){
            Toast.makeText(MainActivity.this,"Your weight is normal",Toast.LENGTH_LONG).show();
        }
        else if(BMI>=25 && BMI<=30){
            Toast.makeText(MainActivity.this,"You are overweight",Toast.LENGTH_LONG).show();
        }
        else if(BMI>30){
            Toast.makeText(MainActivity.this,"You are obese",Toast.LENGTH_LONG).show();
        }
    }
    public void howMuch (View v){
        double calories = 0;
        if(maleRb.isChecked()){
            calories=(665+(6.3*(Double.parseDouble(weightValue.getText().toString()))*2.2046)+(12.9*(Double.parseDouble(heightValue.getText().toString()))*0.393701)-(6.8*24));
        }
        else if(femaleRb.isChecked() && ageSpinner.getSelectedItem().toString().equals("Below 24")){
            calories=(655+(4.3*(Double.parseDouble(weightValue.getText().toString()))*2.2046)+(4.7*(Double.parseDouble(heightValue.getText().toString()))*0.393701)-(4.7*24));

        }
        else if(femaleRb.isChecked() && ageSpinner.getSelectedItem().toString().equals("Above 24")){
            calories=(455+(4.3*(Double.parseDouble(weightValue.getText().toString()))*2.2046)+(12.9*(Double.parseDouble(heightValue.getText().toString()))*0.393701)-(4.7*24));

        }
        Intent i =new Intent(MainActivity.this,SecondActivity.class);
        i.putExtra("How_Much_Should_I_Eat",calories);
        startActivity(i);
    }
}