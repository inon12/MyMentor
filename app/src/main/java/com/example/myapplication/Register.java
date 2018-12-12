package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText passwordEt,userNameEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Spinner mySpinner =(Spinner) findViewById(R.id.spinner1);


        passwordEt=(EditText)findViewById(R.id.edtUsername);
        userNameEt=(EditText)findViewById(R.id.edtPassword);


        ArrayAdapter<String> myAdapter =new ArrayAdapter<String>(Register.this,android.R.layout.simple_list_item_1
                ,getResources().getStringArray(R.array.typeSpinner));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String sSelected=parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
