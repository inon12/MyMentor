package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TeacherDetails extends AppCompatActivity {
    Button mSubmit,mEditSubjects;
    RatingBar ratingRatingBar;
    Teacher user;
    TextView mRateCount, mSubjects;
    List<CheckBox> checkboxes = new ArrayList<CheckBox>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView mName=(TextView)findViewById(R.id.name_text);
        mSubjects=(TextView)findViewById(R.id.sSubjects);
        TextView mPrice=(TextView)findViewById(R.id.price);
        TextView mMail=(TextView)findViewById(R.id.mail);
        mRateCount=(TextView)findViewById(R.id.rateCount);
        CircleImageView mImage=(CircleImageView)findViewById(R.id.profile_image);
        mImage.setImageDrawable(getResources().getDrawable(R.drawable.gradient));

         mSubmit=(Button)findViewById(R.id.submit);
         mEditSubjects=(Button)findViewById(R.id.editSubjects);

        checkboxes.add((CheckBox) findViewById(R.id.math));
        checkboxes.add((CheckBox) findViewById(R.id.java));
        checkboxes.add((CheckBox) findViewById(R.id.checkBox3));
        checkboxes.add((CheckBox) findViewById(R.id.Python));
        checkboxes.add((CheckBox) findViewById(R.id.C));
        checkboxes.add((CheckBox) findViewById(R.id.PHP));

        ratingRatingBar = (RatingBar) findViewById(R.id.rating_rating_bar);

        Intent intent=getIntent();
        user=(Teacher)intent.getSerializableExtra("model");

        mName.setText(user.getName());
        mPrice.setText(user.getPrice());
        mMail.setText(user.getEmail());
        mRateCount.setText("rated by "+user.getCount()+" users");

        String subjects="";
        for (int i=0;i<user.getSubjects().size()-1;i++){
            subjects+=user.getSubjects().get(i)+" , ";
        }
        subjects+=user.getSubjects().get(user.getSubjects().size()-1)+".";
        mSubjects.setText(subjects);

        Glide.with(getBaseContext()).load(user.getImage()).into(mImage);

        ratingRatingBar.setIsIndicator(true);
        ratingRatingBar.setRating(user.getRank());
    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.sign_out:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(TeacherDetails.this, MainActivity.class));
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void onClickEditSubjects(View view){
        mEditSubjects.setVisibility(View.INVISIBLE);
        for (CheckBox CheckBox: checkboxes){
            CheckBox.setVisibility(view.VISIBLE);
        }
        mSubmit.setVisibility(View.VISIBLE);
    }
    public void onclickSubmit(View view){

        mEditSubjects.setVisibility(View.VISIBLE);
        mSubmit.setVisibility(View.INVISIBLE);
        for (CheckBox CheckBox: checkboxes){
            CheckBox.setVisibility(view.INVISIBLE);
        }
        final List<String> subjects = new ArrayList<String>();
        final List<String> subjects_to_Remove = new ArrayList<String>();

        for (CheckBox checkBox : checkboxes) {
            if (checkBox.isChecked()) {
                subjects.add(checkBox.getText().toString());
            }
            else{
                subjects_to_Remove.add(checkBox.getText().toString());
            }
        }
        user.setSubjects(subjects);
        final DatabaseReference db = FirebaseDatabase.getInstance().getReference();

        db.child("Teachers").child(user.getEmail().replace(".", "|")).setValue(user);
        db.child("Users").child(user.getEmail().replace(".", "|")).setValue(user);

        for(String s:user.getSubjects()){
            db.child("Subjects").child(s).child(user.getEmail().replace(".", "|")).setValue(user);
        }
        for(String s:subjects_to_Remove){
            db.child("Subjects").child(s).child(user.getEmail().replace(".", "|")).removeValue();
        }
        String subj="";
        for (int i=0;i<user.getSubjects().size()-1;i++){
            subj+=user.getSubjects().get(i)+" , ";
        }
        subj+=user.getSubjects().get(user.getSubjects().size()-1)+".";
        mSubjects.setText(subj);

    }
}
