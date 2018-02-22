package com.example.gptv.examplepresentation;

import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    //Declaring Android objects
    private TextView txtUser;
    private Button btnLogout;

    //Read about FirebaseAuth in RegisterActivity.java
    private FirebaseAuth mAuth;

    //Reference to a Firebase database
    private DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing Android objects to their XML counter parts(establishing a link)
        txtUser = findViewById(R.id.activity_main_txtUser);
        btnLogout = findViewById(R.id.activity_main_btnLogout);

        //Initializing mAuth to FirebaseAuth's current instance
        mAuth = FirebaseAuth.getInstance();

        //FirebaseUser holds all of the information of current user such as user id, email, etc.
        FirebaseUser loggedInUser = mAuth.getCurrentUser();

        //This check prevents from a null object reference error
        if(loggedInUser != null) {
            //mReference holds the reference to the 'Users' table in the database
            //If there is no table it returns null value

            mReference = FirebaseDatabase.getInstance().getReference("Users").child(loggedInUser.getUid());

            //This method runs every time the current User's table under the 'Users' table is changed
            mReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //We are retrieving the display_name from the table and storing it in a string
                    String user_name = dataSnapshot.child("display_name").getValue().toString();
                    txtUser.append(user_name);  //Adding the display_name to the txtUser view
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open the login activity and close out the main activity
                //Closing out the main activity will not allow a user to go back to this activity
                //by pressing back button
                mAuth.signOut();
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });


    }


    //Runs before onCreate() to check if a user is already logged in
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if(currentUser == null)
        {
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }

    }


}
