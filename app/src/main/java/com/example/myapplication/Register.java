package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText passwordEt, passwordEt2, userNameEt, emailEt;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    CheckBox CheckBox, CheckBox2, CheckBox3, CheckBox4, CheckBox5, CheckBox6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        passwordEt = (EditText) findViewById(R.id.edtUsername);
        passwordEt2 = (EditText) findViewById(R.id.edtPassword2);
        emailEt = (EditText) findViewById(R.id.edtMail);
        userNameEt = (EditText) findViewById(R.id.edtPassword);

        CheckBox = (CheckBox) findViewById(R.id.checkBox);
        CheckBox2 = (CheckBox) findViewById(R.id.checkBox2);
        CheckBox3 = (CheckBox) findViewById(R.id.checkBox3);
        CheckBox4 = (CheckBox) findViewById(R.id.checkBox4);
        CheckBox5 = (CheckBox) findViewById(R.id.checkBox5);
        CheckBox6 = (CheckBox) findViewById(R.id.checkBox6);

        Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Register.this, android.R.layout.simple_list_item_1
                , getResources().getStringArray(R.array.typeSpinner));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString(); //this is your selected item
                if (selectedItem.equals("Teacher")) {
                    CheckBox.setVisibility(view.VISIBLE);
                    CheckBox2.setVisibility(view.VISIBLE);
                    CheckBox3.setVisibility(view.VISIBLE);
                    CheckBox4.setVisibility(view.VISIBLE);
                    CheckBox5.setVisibility(view.VISIBLE);
                    CheckBox6.setVisibility(view.VISIBLE);
                }
                else {
                    CheckBox.setVisibility(view.INVISIBLE);
                    CheckBox2.setVisibility(view.INVISIBLE);
                    CheckBox3.setVisibility(view.INVISIBLE);
                    CheckBox4.setVisibility(view.INVISIBLE);
                    CheckBox5.setVisibility(view.INVISIBLE);
                    CheckBox6.setVisibility(view.INVISIBLE);
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String sSelected = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
