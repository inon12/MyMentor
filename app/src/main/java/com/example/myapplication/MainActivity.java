package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtUsername,edtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
    }
    public void onClickLogin(View view){
        String username= edtUsername.getText().toString();
        String password= edtPassword.getText().toString();
        String type ="login";


    }
    public void onclickRegisterHere(View view){
        Intent register=new Intent(this,Register.class);
        startActivity(register);
    }


}
