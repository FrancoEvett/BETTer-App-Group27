package com.example.better;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class forgot_password extends AppCompatActivity {
String email = "BETTer@brunel.ac.uk";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }

    private void popup(String title, String msg) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.show();
    }

    public void forgot_pass (View view){
        EditText em = findViewById(R.id.email);
        TextView mex = findViewById(R.id.textView12);
        String usermail = em.getText().toString();
        if (!usermail.equals("") == true) {
            try {
                GMailSender sender = new GMailSender("better.group27@gmail.com", "BETTer_27");
                sender.sendMail("This is Subject",
                        "Your password is: ",
                        "better.group27@gmail.com",
                        usermail);
                mex.setText("Thanks! your request has been received, please check your email for the next step");
            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
                mex.setText("The email is not recognised");
            }
        }
        else {
            mex.setText("Enter your email please!");
        }


//            Intent i = new Intent(Intent.ACTION_SEND);
//            i.setType("message/rfc822");
//            i.putExtra(Intent.EXTRA_EMAIL  , new String[]{usermail});
//            i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
//            i.putExtra(Intent.EXTRA_TEXT   , "body of email");
//            try {
//                startActivity(Intent.createChooser(i, "Your password is FUCK OFF"));
//            } catch (android.content.ActivityNotFoundException ex) {
//                mex.setText("This email is not registered in our database!");
//            }
 //       }
//        else {
//            //mex.setText("Email not recognised, please try again");
//            popup("Error", "Email not recognised, please try again" );
//            // Invalid email Error Message
//        }
    }

    public void back (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
