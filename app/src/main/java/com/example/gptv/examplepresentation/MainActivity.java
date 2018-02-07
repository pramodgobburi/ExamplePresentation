package com.example.gptv.examplepresentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Declaring Android objects
    private TextView txtUser;
    private Button btnLogout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing Android objects to their XML counter parts(establishing a link)
        txtUser = findViewById(R.id.activity_main_txtUser);
        btnLogout = findViewById(R.id.activity_main_btnLogout);




        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open the login activity and close out the main activity
                //Closing out the main activity will not allow a user to go back to this activity
                //by pressing back button
                Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });


    }

    //Runs before onCreate() to check if a user is already logged in
    @Override
    public void onStart() {
        super.onStart();

    }


}
