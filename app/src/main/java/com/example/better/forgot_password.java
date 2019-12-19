package com.example.better;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class forgot_password extends AppCompatActivity {
String email = "BETTer@brunel.ac.uk";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }

    public void forgot_pass (View view){
        EditText em = findViewById(R.id.email);
        TextView mex = findViewById(R.id.textView12);
        String usermail = em.getText().toString();
        if (email.equals(usermail) == true){
            mex.setText("Thanks! your request has been received, please check your email for the next step");
        }
        else {
            mex.setText("Email not recognised, please try again");
        }
    }

    public void back (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
