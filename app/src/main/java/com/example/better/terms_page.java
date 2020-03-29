package com.example.better;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class terms_page extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_terms);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void termsButtonClick1(View view) {
        TextView tv = (TextView) findViewById(R.id.textView13);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tv.setTextColor(getResources().getColor(R.color.errorColor, getResources().newTheme()));
        }
        tv.setText(R.string.agree);
        TextView bv = (TextView) findViewById(R.id.textView14);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            bv.setTextColor(getResources().getColor(R.color.errorColor, getResources().newTheme()));
            bv.setText(R.string.terms);
        }
    }

    public void privacyButtonClick2(View view) {

        TextView tv = (TextView) findViewById(R.id.textView13);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tv.setTextColor(getResources().getColor(R.color.errorColor, getResources().newTheme()));
        }
        tv.setMovementMethod(new ScrollingMovementMethod());
        tv.setText(R.string.privacy_policy);
        TextView tv1 = (TextView) findViewById(R.id.textView14);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tv1.setTextColor(getResources().getColor(R.color.errorColor, getResources().newTheme()));
        }
        tv1.setMovementMethod(new ScrollingMovementMethod());
        tv1.setText(R.string.privacy_policy1);
        TextView tv2 = (TextView) findViewById(R.id.textView15);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tv2.setTextColor(getResources().getColor(R.color.errorColor, getResources().newTheme()));
        }
        tv2.setMovementMethod(new ScrollingMovementMethod());
        tv2.setText(R.string.privacy_policy2);
    }

    public void back (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
