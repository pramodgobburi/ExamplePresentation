package com.example.gptv.examplepresentation;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText txtEmail, txtPass, txtUser;
    private Button btnRegister;

    //FirebaseAuth is required to authenticate a user
    // and to read and write data to the database

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Initializing Android objects to their XML counter parts(establishing a link)
        txtPass = findViewById(R.id.activity_register_txtPass);
        txtEmail = findViewById(R.id.activity_register_txtEmail);
        txtUser = findViewById(R.id.activity_register_txtDisplayName);

        btnRegister = findViewById(R.id.activity_register_btnRegister);

        //We are initializing the mAuth object to FirebaseAuth's current instance
        //For more info about FirebaseAuth go to: https://developers.google.com/android/reference/com/google/firebase/auth/FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //The following code will run when the user clicks on the register button (btnRegister)
                String Pass = txtPass.getText().toString();
                String Email = txtEmail.getText().toString();
                String DisplayName = txtUser.getText().toString();

                registerUser(Email, Pass, DisplayName);

            }
        });
    }

    private void registerUser (String email, String password, final String displayName)
    {

        //This method is derived from Firebase Assistant tab
        //To access this tab go to the Tools tab above and click on Firebase
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success

                            //We are storing the current user ID as a string
                            String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();

                            //We are referencing to the users table, if the table doesn't exist the table is created
                            DatabaseReference usersDB = FirebaseDatabase.getInstance().getReference("Users");
                            //We are adding every user as a child under 'Users' table  and adding a child 'display_name' to store the displayName
                            usersDB.child(user_id).child("display_name").setValue(displayName);

                            //After we store the name, we will redirect the user to the Login Activity
                            Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(loginIntent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
