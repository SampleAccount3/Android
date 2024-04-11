package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.android.Model.CustomerModel;

public class MainActivity extends AppCompatActivity {

    Button btnViewAll,btnAdd;
    EditText etName,etAge;
    CheckBox isActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnViewAll = findViewById(R.id.btn_ViewAll);
        btnAdd = findViewById(R.id.btn_Add);
        etName = findViewById(R.id.et_Name);
        etAge = findViewById(R.id.et_Age);
        isActive = findViewById(R.id.cb_Customer);

        btnViewAll.setOnClickListener((view)->{
            Toast.makeText(this, "Hello World View All", Toast.LENGTH_SHORT).show();

            CustomerModel customerModel = new CustomerModel(-1,etName.getText().toString(),
                    Integer.parseInt(etAge.getText().toString()),isActive.isChecked());
        });

        btnAdd.setOnClickListener((view)->{
            Toast.makeText(this, "Hello World Add", Toast.LENGTH_SHORT).show();
        });
    }
}