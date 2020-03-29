package com.example.better;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

@RequiresApi(api = Build.VERSION_CODES.O)
public class sign_up extends AppCompatActivity {

    UserAccountControll userAccountControll = new UserAccountControll();
    MainActivity mainActivity = new MainActivity();
    public static final String CHANNEL_ID = "Main 1";
    public static final String CHANNEL_NAME = "Main 2";
    public static final String CHANNEL_DESC = "Notification";
    public static String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if(task.isSuccessful()){
                            token = task.getResult().getToken();
                        }
                    }
                });
    }


    @RequiresApi(api = Build.VERSION_CODES.O)

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
        CheckBox checkbx = findViewById(R.id.checkBox);
        String id = ID.getText().toString();
        String userName = username.getText().toString();
        String userEmail = useremail.getText().toString();
        String password = pass.getText().toString();
        String repeatpass = repass.getText().toString();
        ID.setText("");
        pass.setText("");
        repass.setText("");


        if(((password == null) || (password.isEmpty() == true))  || ((userEmail == null) || (userEmail.isEmpty() == true) ||  ((userName == null) || (userName.isEmpty() ==true)) || (token == null))){
            //editetxt display enter somethigm
            //message.setText("Error");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                popup("Error", "You have not filled in the sign up" );
            }
        }

        else {
            if (password.equals(repeatpass) == true) {
                if ((checkbx.isChecked()) && (userAccountControll.CreateNewAccount(id, userName, userEmail, password,token))) {

                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O  ) {
                        popup("Success", "Account Created");
                    }
                }
                else
                    {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        popup("Error:", "Account Creation Failed. Please try again!");
                    }
                }
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    popup("Error", "Password Do Not Match. Please try again!");
                }

                // If the password is not the same
            }
        }
        }

    public void back (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void terms(View view) {
        Intent intent = new Intent(this, terms_page.class);
        startActivity(intent);
    }
}
