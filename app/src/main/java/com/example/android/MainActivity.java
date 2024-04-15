package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.android.Model.CustomerModel;
import com.example.android.Model.DataBaseHelper;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnViewAll,btnAdd;
    EditText etName,etAge,etSampleText,etIncome,etDateCreated;
    CheckBox isActive;
    ListView lvCustomerList;
    ArrayAdapter customerArrayAdapter;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnViewAll = findViewById(R.id.btn_ViewAll);
        btnAdd = findViewById(R.id.btn_Add);
        etName = findViewById(R.id.et_Name);
        etAge = findViewById(R.id.et_Age);
        etSampleText = findViewById(R.id.etSample);
        etIncome = findViewById(R.id.et_Income);
        etDateCreated = findViewById(R.id.et_DateCreated);
        isActive = findViewById(R.id.cb_Customer);
        lvCustomerList = findViewById(R.id.lv_Data);
        etDateCreated.setText(DateFormat.getDateInstance().format(new Date()).toString());

        GetAllData();

        btnViewAll.setOnClickListener((view)->{
            GetAllData();
            Toast.makeText(this, "Gumana", Toast.LENGTH_SHORT).show();
        });

        btnAdd.setOnClickListener((view) -> {
            CustomerModel customerModel;
            try {
                customerModel = new CustomerModel(-1,etName.getText().toString(),
                        etSampleText.getText().toString(), Integer.parseInt(etAge.getText().toString()),
                        isActive.isChecked(), Integer.parseInt(etIncome.getText().toString()),etDateCreated.getText().toString());
                Toast.makeText(this, customerModel.toString(), Toast.LENGTH_SHORT).show();
                // Initialize the DatabaseHelper Class
                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                // adds the customerModel properties to the database
                boolean success = dataBaseHelper.AddCustomer(customerModel);
                Toast.makeText(this, "Is Success: " + success, Toast.LENGTH_SHORT).show();

            }catch (Exception e){
                Toast.makeText(this, "Empty Fields: "+ e, Toast.LENGTH_SHORT).show();
                // Default value for the failed query
                customerModel = new CustomerModel(-1,"Error","Empty", 0 ,false,0,"No Date");

                Toast.makeText(this, customerModel.toString(), Toast.LENGTH_SHORT).show();
            }

            GetAllData();
        });

        lvCustomerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // cast to Customer Model
                CustomerModel selectedCustomer =(CustomerModel) adapterView.getItemAtPosition(position);
                dataBaseHelper.DeleteCustomer(selectedCustomer);
                GetAllData();
                Toast.makeText(MainActivity.this, "Deleted: " + selectedCustomer.toString() , Toast.LENGTH_SHORT).show();
            }
        });

    }

    // updates the List
    public void GetAllData(){
        dataBaseHelper = new DataBaseHelper(MainActivity.this);
        List<CustomerModel> everyone = dataBaseHelper.GetAll();
        customerArrayAdapter = new ArrayAdapter<CustomerModel>(MainActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.GetAll());
        lvCustomerList.setAdapter(customerArrayAdapter);
    }
}