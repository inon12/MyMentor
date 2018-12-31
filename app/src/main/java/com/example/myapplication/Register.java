package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText passwordEt, passwordEt2, userNameEt, emailEt, nameEt, priceEt;
    private FirebaseAuth auth;
    List<CheckBox> checkboxes = new ArrayList<CheckBox>();
    String spinnerSelectedItem="Student";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        passwordEt = (EditText) findViewById(R.id.edtPassword);
        passwordEt2 = (EditText) findViewById(R.id.edtPassword2);
        emailEt = (EditText) findViewById(R.id.edtMail);
        userNameEt = (EditText) findViewById(R.id.edtUsername);
        nameEt = (EditText) findViewById(R.id.edtFullName);
        priceEt = (EditText) findViewById(R.id.edtlastName);

        checkboxes.add((CheckBox) findViewById(R.id.math));
        checkboxes.add((CheckBox) findViewById(R.id.java));
        checkboxes.add((CheckBox) findViewById(R.id.checkBox3));
        checkboxes.add((CheckBox) findViewById(R.id.Python));
        checkboxes.add((CheckBox) findViewById(R.id.C));
        checkboxes.add((CheckBox) findViewById(R.id.PHP));

        Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Register.this, android.R.layout.simple_list_item_1
                , getResources().getStringArray(R.array.typeSpinner));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerSelectedItem = parent.getItemAtPosition(position).toString(); //this is your selected item
                if (spinnerSelectedItem.equals("Teacher")) {
                    for (CheckBox CheckBox: checkboxes){
                        CheckBox.setVisibility(view.VISIBLE);
                    }
                } else {
                    for (CheckBox CheckBox : checkboxes) {
                        CheckBox.setVisibility(view.INVISIBLE);
                    }
                    for (CheckBox checkBox : checkboxes) {
                        if (checkBox.isChecked()) {
                            checkBox.toggle();
                        }
                    }
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void onClickRegist(View view) {
        final DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        final User user;

        String password = passwordEt.getText().toString().trim();
        String password2 = passwordEt2.getText().toString().trim();
        String email = emailEt.getText().toString().trim();
        String user_name = userNameEt.getText().toString().trim();
        String name = nameEt.getText().         toString().trim();
        String price = priceEt.getText().toString().trim();
        final List<String> subjects = new ArrayList<String>();
        if(!checkfields( name, price,email,password,user_name,password2))
            return;

       if (spinnerSelectedItem.equals("Teacher")) {

           for (CheckBox checkBox : checkboxes) {
               if (checkBox.isChecked()) {
                   subjects.add(checkBox.getText().toString());
               }
           }
           user = new Teacher(name,price,email,password,user_name,subjects,0,"Teacher");
        }else{
            user = new Student(name,price,email,password,user_name,"Student");
        }
        db.child("Users").child(user.getEmail().replace(".", "|")).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            Toast.makeText(Register.this, "User already exists", Toast.LENGTH_LONG).show();
                        } else {
                            if(user instanceof Teacher ) {
                                db.child("Teachers").child(user.getEmail().replace(".", "|")).setValue(user);
                                db.child("Users").child(user.getEmail().replace(".", "|")).setValue(user);

                                for(String s:subjects){
                                    db.child("Subjects").child(s).child(user.getEmail().replace(".", "|")).setValue(user);
                                }

                            }
                            else {
                                db.child("Users").child(user.getEmail().replace(".", "|")).setValue(user);
                            }
                            Toast.makeText(Register.this, "Registration done.", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Register.this,MainActivity.class));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }

    public boolean checkfields(String first_name,String last_name,String email,String password,String user_name,String password2){

        if ((TextUtils.isEmpty(email))) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!(password.equals(password2))) {
            Toast.makeText(getApplicationContext(), "The passwords dont the same", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if ((TextUtils.isEmpty(password2))) {
            Toast.makeText(getApplicationContext(), "Enter password address!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(user_name)) {
            Toast.makeText(getApplicationContext(), "Enter user_mame!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if ((TextUtils.isEmpty(first_name))) {
            Toast.makeText(getApplicationContext(), "Enter first_name address!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(password2)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
