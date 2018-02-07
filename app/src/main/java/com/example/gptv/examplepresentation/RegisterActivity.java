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

public class RegisterActivity extends AppCompatActivity {

    private EditText txtEmail, txtPass;
    private Button btnRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtPass = findViewById(R.id.activity_register_txtPass);
        txtEmail = findViewById(R.id.activity_register_txtEmail);

        btnRegister = findViewById(R.id.activity_register_btnRegister);



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Pass = txtPass.getText().toString();
                String Email = txtEmail.getText().toString();

                registerUser(Email, Pass);

            }
        });
    }

    private void registerUser ( String email, String password)
    {

    }

}
