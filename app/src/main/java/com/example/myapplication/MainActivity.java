package com.example.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
     final   String email= edtUsername.getText().toString();
     final   String password= edtPassword.getText().toString();

     final DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        if ((TextUtils.isEmpty(email))) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }
     db.child("Users")
     .child(email.replace(".","|"))
     .addListenerForSingleValueEvent(
             new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    boolean login=false;
                    User user=null;

                    if(dataSnapshot.exists()){
                        user=dataSnapshot.getValue(User.class);
                        if(password.equals(user.getPassword())){
                            login=true;
                        }
                    }
                    if (login){
                        Toast.makeText(MainActivity.this, "incorrect.", Toast.LENGTH_LONG).show();
                        if(user instanceof Teacher) {
                            startActivity(new Intent(MainActivity.this,Search.class));
                        }else{
                            startActivity(new Intent(MainActivity.this, Search.class));

                        }

                    }else{
                        Toast.makeText(MainActivity.this, "Either email or password is incorrect.", Toast.LENGTH_LONG).show();
                    }
                 }

                 @Override
                 public void onCancelled(@NonNull DatabaseError databaseError) {

                 }
             }
     );


    }
    public void onclickRegisterHere(View view){
        Intent register=new Intent(this,Register.class);
        startActivity(register);
    }


}
