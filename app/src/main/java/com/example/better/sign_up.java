package com.example.better;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class sign_up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void sign_new_account (View view){
        EditText ID = findViewById(R.id.studentID);
        EditText pass = findViewById(R.id.firstpassword);
        EditText repass = findViewById(R.id.repeatpassword);
        TextView message = findViewById(R.id.textView11);
        String password = pass.getText().toString();
        String repeatpass = repass.getText().toString();
        ID.setText("");
        pass.setText("");
        repass.setText("");
        if(password.equals(repeatpass) == true) {
            message.setText("Welcome to BETTer. We hope you will have a pleasant experience");
        }
        else {
            message.setText("The 2 passwords are not equal. Please try again!");
        }
    }

    public void back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
