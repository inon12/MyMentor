package com.example.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Search extends AppCompatActivity {

    private EditText mSearchField;
    private ImageButton mSearchBtn;

    private RecyclerView mResultList;

    private DatabaseReference mUserDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mUserDatabase=FirebaseDatabase.getInstance().getReference("Subjects");

        mSearchField= (EditText)findViewById(R.id.search_field);
        mSearchBtn=(ImageButton)findViewById(R.id.search_btn);

        mResultList=(RecyclerView)findViewById(R.id.result_list);
       // mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String searchText = mSearchField.getText().toString();

                firebaseUserSearch(searchText);

            }
        });

    }

    private void firebaseUserSearch(String searchText) {

        Toast.makeText(Search.this, "Started Search", Toast.LENGTH_LONG).show();

        Query firebaseSearchQuery = mUserDatabase.child(searchText).orderByChild("name");

        FirebaseRecyclerOptions<User> options=new FirebaseRecyclerOptions.Builder<User>()
                .setQuery(firebaseSearchQuery,User.class)
                .build();
        FirebaseRecyclerAdapter<User, UsersViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<User, UsersViewHolder>(options) {
            @NonNull
            @Override
            public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view=getLayoutInflater().from(viewGroup.getContext()).inflate(R.layout.list_layout,viewGroup,false);
                UsersViewHolder viewHolder =new UsersViewHolder(view);
                return viewHolder;
            }

            @Override
            protected void onBindViewHolder(@NonNull UsersViewHolder holder, int position, @NonNull User model) {
                holder.setDetails(getApplicationContext(), model.getName(), model.getPrice(), model.getImage());

            }

        };

        mResultList.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();
    }

    public static class UsersViewHolder extends  RecyclerView.ViewHolder{

        View mView;
        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);

            mView=itemView;
        }
        public void setDetails(Context ctx, String userName, String userPrice, String userImage){

            TextView user_name =(TextView) mView.findViewById(R.id.name_text);
            TextView user_price =(TextView) mView.findViewById(R.id.status_text);
            ImageView user_image =(ImageView) mView.findViewById(R.id.profile_image);

            user_name.setText(userName);
            user_price.setText(userPrice);
            Glide.with(ctx).load(userImage).into(user_image);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

}
}
