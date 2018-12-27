package com.example.myapplication;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.RatingBar;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.bumptech.glide.Glide;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

        import de.hdodenhof.circleimageview.CircleImageView;

public class DetailActivity extends AppCompatActivity {

    Button mSubmit,mSendMail,mRateMe;
    RatingBar ratingBar,ratingRatingBar;
    Teacher user;
    TextView mRateCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView mName=(TextView)findViewById(R.id.name_text);
        TextView mSubjects=(TextView)findViewById(R.id.subjects);
        TextView mPrice=(TextView)findViewById(R.id.price);
        TextView mMail=(TextView)findViewById(R.id.mail);
        mRateCount=(TextView)findViewById(R.id.rateCount);
        CircleImageView mImage=(CircleImageView)findViewById(R.id.profile_image);
        mImage.setImageDrawable(getResources().getDrawable(R.drawable.gradient));


        mRateMe=(Button)findViewById(R.id.rateMe);
        mSendMail=(Button)findViewById(R.id.send_mail);
        mSubmit=(Button)findViewById(R.id.submit);

        ratingRatingBar = (RatingBar) findViewById(R.id.rating_rating_bar);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

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
    public void onClickReteMe(View view){
        mRateMe.setVisibility(view.INVISIBLE);
        ratingBar.setVisibility(view.VISIBLE);
        mSubmit.setVisibility(view.VISIBLE);
        mSendMail.setVisibility(view.INVISIBLE);
    }
    public void onClickSubmit(View view){
        final DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        Toast.makeText(DetailActivity.this, "Thanks for your rate", Toast.LENGTH_LONG).show();
        user.setCount(user.getCount()+1);
        user.setRank((ratingBar.getRating()+(user.getRank()*(user.getCount()-1)))/(user.getCount()));

        ratingBar.setIsIndicator(true);
        ratingRatingBar.setRating(user.getRank());
        mRateCount.setText("rated by "+user.getCount()+" users");

        mRateMe.setEnabled(false);
        mRateMe.setVisibility(view.VISIBLE);
        ratingBar.setVisibility(view.INVISIBLE);
        mSubmit.setVisibility(view.INVISIBLE);
        mSendMail.setVisibility(view.VISIBLE);

        db.child("Teachers").child(user.getEmail().replace(".", "|")).setValue(user);
        db.child("Users").child(user.getEmail().replace(".", "|")).setValue(user);

        for(String s:user.getSubjects()){
            db.child("Subjects").child(s).child(user.getEmail().replace(".", "|")).setValue(user);
        }

    }
    public void onClickSendmail(View view){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{user.email});
        i.putExtra(Intent.EXTRA_SUBJECT, "Hey i saw your details in MyMentor");
        //i.putExtra(Intent.EXTRA_TEXT   , "body of email");
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(DetailActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}

