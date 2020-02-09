package com.example.better;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
better b = new better();
UserAccountControll userAccountControll = new UserAccountControll();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void on_click (View view){
        EditText Username = (EditText) findViewById(R.id.Username);
        EditText Password = (EditText) findViewById(R.id.Password);
        String userName = Username.getText().toString();
        String passWord = Password.getText().toString();
        TextView Error =  findViewById(R.id.errorMsg);
        if (((userName == null) || (userName.isEmpty()== true)) || ((passWord == null) || (passWord.isEmpty() == true))){
            Error.setText("Error");
        }
        else {
            if (userAccountControll.Login(userName, passWord)){
                //login sucessfull
                Intent intent = new Intent(this, better.class);
                startActivity(intent);

            }
            else {
                Error.setText("Invalid Login");
            }

    }}

    public void signNewAccount(View view){

    }

    public void sign_new_account (View v){
        EditText ID = findViewById(R.id.studentID);
        EditText pass = findViewById(R.id.firstpassword);
        EditText repass = findViewById(R.id.repeatpassword);
        EditText username = findViewById(R.id.userName);
        EditText useremail = findViewById(R.id.userEmail);
        TextView message = findViewById(R.id.textView11);
        String id = ID.getText().toString();
        String userName = username.getText().toString();
        String userEmail = useremail.getText().toString();
        String password = pass.getText().toString();
        String repeatpass = repass.getText().toString();
        ID.setText("");
        pass.setText("");
        repass.setText("");
        if(((password == null) || (password.isEmpty() == true))  || ((userEmail == null) || (userEmail.isEmpty() == true) ||  ((userName == null) || (userName.isEmpty() ==true)))){
            //editetxt display enter somethigm
            message.setText("Error");
        }

        else {
            if (password.equals(repeatpass) == true) {
                if (userAccountControll.CreateNewAccount(id, userName, userEmail, password)) {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    // Adding all the data j
                } else {
                    message.setText("Error has occurred");
                }
            } else {
                message.setText("The 2 passwords are not equal. Please try again!");
                // If the password is not the same
            }
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
    public void back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
