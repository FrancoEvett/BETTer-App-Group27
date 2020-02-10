package com.example.better;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class sign_up extends AppCompatActivity {

    UserAccountControll userAccountControll = new UserAccountControll();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
    private void popup(String title, String msg) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.show();
    }

    public void signNewAccount(View view){
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
            //message.setText("Error");
            popup("Error", "You have not filled in the sign up" );
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
    public void back (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
