package com.example.better;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
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

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity {


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

    private void popup(String title, String msg) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.show();
    }

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
            Intent intent = new Intent(this, better.class);
            startActivity(intent);
            if (userAccountControll.Login(userName, passWord)){
                //login sucessfull
                //Intent intent = new Intent(this, better.class);
                //startActivity(intent);
            }
            else {
               // Error.setText("Invalid Login");
               // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                   // popup("Error", "Invalid login" );
               // }

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
