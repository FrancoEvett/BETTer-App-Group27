package com.example.better;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {
static String username = "BETTer";
static String password = "BETTer1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void on_click (View view){
        EditText Username = (EditText) findViewById(R.id.Username);
        EditText Password = (EditText) findViewById(R.id.Password);
        String Username1 = Username.getText().toString();
        String Password1 = Password.getText().toString();

        if (Username1.equals(username) ==  true && Password1.equals(password) == true){
            Intent intent = new Intent(this, better.class);
            startActivity(intent);
        }
        else {
            TextView error = (TextView) findViewById(R.id.errormex);
            error.setText("Username or Password Wrong!");
        }
    }

    public void sign_up (View view){
        Intent intent = new Intent(this, sign_up.class);
        startActivity(intent);

    }

    public void forgot_password (View view){
        Intent intent = new Intent(this, forgot_password.class);
        startActivity(intent);

    }
}
