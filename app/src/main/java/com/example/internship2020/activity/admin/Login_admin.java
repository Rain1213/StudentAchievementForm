package com.example.internship2020.activity.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.internship2020.R;

import java.util.regex.PatternSyntaxException;

public class Login_admin extends AppCompatActivity {
    private Button btn_submit;
    private EditText editTextEmail;
    private EditText editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        btn_submit=findViewById(R.id.btn_submit);
        editTextEmail = findViewById(R.id.admin_email);
        editTextPassword = findViewById(R.id.admin_password);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if(email.isEmpty()){
                    editTextEmail.setError("Email is required");
                    editTextEmail.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    editTextEmail.setError("Enter a valid email");
                    editTextEmail.requestFocus();
                    return;

                }
                if(password.isEmpty()){
                    editTextPassword.setError("Password Required");
                    editTextPassword.requestFocus();
                    return;
                }
                if(password.length() <6){
                    editTextPassword.setError("Password should atleast be 6 characters long");
                    editTextPassword.requestFocus();
                    return;
                }

                Intent submit=new Intent(Login_admin.this, AdminMainPage.class);
                startActivity(submit);


            }
        });
    }
}
