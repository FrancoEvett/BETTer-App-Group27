package com.example.better;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.InputMismatchException;

public class better extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_better);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.better, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public void weeklyimage (View view){
        ImageView week = (ImageView) findViewById(R.id.weeklytt);
        ImageView day = (ImageView) findViewById(R.id.dailytt);
        day.setVisibility(View.INVISIBLE);
        week.setVisibility(View.VISIBLE);
    }

    public void dailyimage (View view) {
        ImageView week = (ImageView) findViewById(R.id.weeklytt);
        ImageView day = (ImageView) findViewById(R.id.dailytt);
        week.setVisibility(View.INVISIBLE);
        day.setVisibility(View.VISIBLE);
    }

    public void exit (View view){

        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    public void go_home (View view){
        Intent intent = new Intent(this, better.class);
        startActivity(intent);
    }

    public void switch_notifications (View view) {
        Switch not = (Switch) findViewById(R.id.switch1);
        TextView notif = (TextView) findViewById(R.id.switchnotifications);

        if(not.isChecked() == true){
            notif.setText("Switch to turn OFF notifications");
        }
        if(not.isChecked() == false){
            notif.setText("Switch to turn ON notifications");
        }
    }

    public void time (View view){
        TextView mex = findViewById(R.id.displaytime);
        try {
            EditText h = findViewById(R.id.hours);
            EditText min = findViewById(R.id.minutes);
            CheckBox tick = findViewById(R.id.checkBox);
            String hours = h.getText().toString();
            String minutes = min.getText().toString();
            int h1 = Integer.parseInt(hours);
            int m1 = Integer.parseInt(minutes);
            if (tick.isChecked() == true) {
                if (m1 <= 60 && m1 >= 0 && h1 <= 24 && h1 >= 0) {
                    mex.setText("Your notification time has been set to " + hours + ":" + minutes + " prior your activity");
                }
            }
            else if (m1 > 60 || m1 < 0 || h1 > 24 || h1 < 0){
                mex.setText("Please insert time as we know it on planet earth");
            }
            else {
                mex.setText("Please enter the amount of time preferred");
            }
        } catch (Exception e){
            mex.setText("Please enter a valid amount of time");
        }
    }

    public void event (View view) {
        EditText ev = findViewById(R.id.event);
        EditText da = findViewById(R.id.date);
        EditText ti = findViewById(R.id.time);
        TextView newev = findViewById(R.id.newevent);
        TextView newda = findViewById(R.id.newdatevent);
        String event = ev.getText().toString();
        String date = da.getText().toString();
        String time = ti.getText().toString();
        newev.setText(event);
        newda.setText(date + "   " + time);
    }

    public void editevent (View view){
        TextView evdescription = findViewById(R.id.textView7);
        TextView evtime = findViewById(R.id.textView8);
        if(evdescription.getVisibility() == View.VISIBLE) {
            evdescription.setVisibility(View.INVISIBLE);
            evtime.setVisibility(View.INVISIBLE);
        }
        else {
            evdescription.setVisibility(View.VISIBLE);
            evtime.setVisibility(View.VISIBLE);
        }
    }

    public void reveal (View view){
        TextView anas = findViewById(R.id.anas);
        TextView franco = findViewById(R.id.franco);
        TextView pruhtvi = findViewById(R.id.pruthvi);
        TextView romi = findViewById(R.id.romi);
        TextView anthony = findViewById(R.id.anthony);
        TextView felipe = findViewById(R.id.felipe);
        TextView camron = findViewById(R.id.camron);

        anas.setVisibility(View.VISIBLE);
        franco.setVisibility(View.VISIBLE);
        pruhtvi.setVisibility(View.VISIBLE);
        romi.setVisibility(View.VISIBLE);
        anthony.setVisibility(View.VISIBLE);
        felipe.setVisibility(View.VISIBLE);
        camron.setVisibility(View.VISIBLE);
    }

    public void enter (View view){
        EditText cs1 = findViewById(R.id.editText);
        EditText cs2 = findViewById(R.id.editText2);
        EditText cs3 = findViewById(R.id.editText3);
        EditText cs4 = findViewById(R.id.editText4);
        EditText cs5 = findViewById(R.id.editText5);
        cs1.setText("Updated");
        cs2.setText("Updated");
        cs3.setText("Updated");
        cs4.setText("Updated");
        cs5.setText("Updated");
    }
}
