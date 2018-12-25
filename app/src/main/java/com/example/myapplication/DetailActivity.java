package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView mName=(TextView)findViewById(R.id.name_text);
        TextView mSubjects=(TextView)findViewById(R.id.subjects);
        TextView mPrice=(TextView)findViewById(R.id.price);
        TextView mMail=(TextView)findViewById(R.id.mail);

        Intent intent=getIntent();
        User user=(User)intent.getSerializableExtra("model");

        mName.setText(user.getName());
        mPrice.setText(user.getPrice());
        mMail.setText(user.getEmail());



        String subjects="";
        for (int i=0;i<user.getSubjects().size()-1;i++){
            subjects+=user.getSubjects().get(i)+" , ";
        }
        subjects+=user.getSubjects().get(user.getSubjects().size()-1)+".";
        mSubjects.setText(subjects);

        final RatingBar ratingRatingBar = (RatingBar) findViewById(R.id.rating_rating_bar);
        ratingRatingBar.setIsIndicator(true);
      //  ratingRatingBar.setRating(user.);

     }
    }

