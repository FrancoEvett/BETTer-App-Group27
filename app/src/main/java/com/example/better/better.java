package com.example.better;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.text.method.ScrollingMovementMethod;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class better extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;


    public static TextView displayTimetable;


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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void better(){
        Displayatbeg();
    }


    int Lab2001;
    int Lab2002;
    int Lab2003;
    int Lab2004;
    int Lab2005;

    public void weeklyimage (View view){
//        ImageView week = (ImageView) findViewById(R.id.weeklytt);
//        ImageView day = (ImageView) findViewById(R.id.dailytt);

//        day.setVisibility(View.INVISIBLE);
//        week.setVisibility(View.VISIBLE);

        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=Pruthvi's Code -=--=--=-=-=-=-=-=-=-//
        displayTimetable = findViewById(R.id.displayInfo1);
        displayTimetable.setVisibility(View.INVISIBLE);
        //-=-=-=-=-=-=-==-==---==-=-=-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=//


    }

    public void dailyimage (View view) {
//        ImageView week = (ImageView) findViewById(R.id.weeklytt);
//        ImageView day = (ImageView) findViewById(R.id.dailytt);
//        week.setVisibility(View.INVISIBLE);
//        day.setVisibility(View.VISIBLE);

        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=Pruthvi's Code -=--=--=-=-=-=-=-=-=-//
        displayTimetable = findViewById(R.id.displayInfo1);
        displayTimetable.setVisibility(View.INVISIBLE);
        //-=-=-=-=-=-=-==-==---==-=-=-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=//

    }



    //-=-=-=-=-=-=-==-==---= Pruthvi's Code -=-=-=-=-=-=-==-=-=//
    public void onTimetable(View view){
//        ImageView week = (ImageView) findViewById(R.id.weeklytt);
//        ImageView day = (ImageView) findViewById(R.id.dailytt);
//        week.setVisibility(View.INVISIBLE);
//        day.setVisibility(View.INVISIBLE);
        displayTimetable = findViewById(R.id.displayInfo1);
        displayTimetable.setVisibility(View.VISIBLE);
        String type = "timetable";
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = new Date();
        String date = (dateFormat.format(date1));
        BackgroundTask backgroundTask = new BackgroundTask(this, displayTimetable);
        backgroundTask.execute(type, date);
    }
    //-=-=-=-=-=-=-==-==---==-=-=-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=//

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

    //Here starts the code for the CONTROL TIMETABLE assignment ANAS ZOUHIR

    //This method gets the local date and returns a String with the first 3 chars of the day. Example "Mon" or "Tue"
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String daysofWeek(){
        LocalDate date = LocalDate.now();
        DayOfWeek dow = date.getDayOfWeek();
        String dayName = dow.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        return dayName;
    }

    //This method use the Calendar library which is the only one that holds a method
    //that allow us to add and subtract days to the current date
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Calendar CurrentDate(){
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        return c;
    }

    //this method simply changes the format of the date as Pruthvi requires it
    //in a specific format which is dd-MM-yyyy
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String changeformat(Date dt){
        String cdate = new SimpleDateFormat("dd-MM-yyyy").format(dt);
        return cdate;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void Displayatbeg(){
        displayTimetable = (TextView) findViewById(R.id.displayInfo1);
        displayTimetable.setVisibility(View.VISIBLE);
        displayTimetable.setMovementMethod(new ScrollingMovementMethod());
        String type = "timetable";
        BackgroundTask back = new BackgroundTask(this, displayTimetable);
        Calendar c = CurrentDate();
        Date dt = c.getTime();
        String cdate = changeformat(dt);
        back.execute(type, cdate);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void Monday (View view){
        displayTimetable = (TextView) findViewById(R.id.displayInfo1);
        displayTimetable.setVisibility(View.VISIBLE);
        displayTimetable.setMovementMethod(new ScrollingMovementMethod());
        String type = "timetable";
        BackgroundTask back = new BackgroundTask(this, displayTimetable);
        String date = daysofWeek();
        Calendar c = CurrentDate();
        if(date.equals("Tue")){
            c.add(Calendar.DAY_OF_WEEK,-1);
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type, cdate);
        }
        else if(date.equals("Wed")){
            c.add(Calendar.DAY_OF_WEEK,-2);
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type, cdate);
        }
        else if(date.equals("Thu")){
            c.add(Calendar.DAY_OF_WEEK,-3);
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type, cdate);
        }
        else if(date.equals("Fri")){
            c.add(Calendar.DAY_OF_WEEK,-4);
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type, cdate);
        }
        else if(date.equals("Sat")){
            c.add(Calendar.DAY_OF_WEEK,+2);
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
        else if(date.equals("Sun")){
            c.add(Calendar.DAY_OF_WEEK,+1);
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
        else {
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void Tuesday (View view){
        displayTimetable = (TextView) findViewById(R.id.displayInfo1);
        displayTimetable.setVisibility(View.VISIBLE);
        displayTimetable.setMovementMethod(new ScrollingMovementMethod());
        String type = "timetable";
        BackgroundTask back = new BackgroundTask(this, displayTimetable);
        String date = daysofWeek();
        Calendar c = CurrentDate();

        if(date.equals("Mon")){
            c.add(Calendar.DAY_OF_WEEK,+1);
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
        else if(date.equals("Wed")){
            c.add(Calendar.DAY_OF_WEEK,-1);
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
        else if(date.equals("Thu")){
            c.add(Calendar.DAY_OF_WEEK,-2);
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
        else if(date.equals("Fri")){
            c.add(Calendar.DAY_OF_WEEK,-3);
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
        else if(date.equals("Sat")){
            c.add(Calendar.DAY_OF_WEEK,+3);
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
        else if(date.equals("Sun")){
            c.add(Calendar.DAY_OF_WEEK,+2);
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
        else {
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void Wednesday (View view){
        displayTimetable = (TextView) findViewById(R.id.displayInfo1);
        displayTimetable.setVisibility(View.VISIBLE);
        displayTimetable.setMovementMethod(new ScrollingMovementMethod());
        String type = "timetable";
        BackgroundTask back = new BackgroundTask(this, displayTimetable);
        String date = daysofWeek();
        Calendar c = CurrentDate();

        if(date.equals("Mon")){
            c.add(Calendar.DAY_OF_WEEK,+2);
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
        else if(date.equals("Tue")){
            c.add(Calendar.DAY_OF_WEEK,+1);
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
        else if(date.equals("Thu")){
            c.add(Calendar.DAY_OF_WEEK,-1);
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
        else if(date.equals("Fri")){
            c.add(Calendar.DAY_OF_WEEK,-2);
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
        else if(date.equals("Sat")){
            c.add(Calendar.DAY_OF_WEEK,+4);
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
        else if(date.equals("Sun")){
            c.add(Calendar.DAY_OF_WEEK,+3);
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
        else {
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void Thursday (View view){
        displayTimetable = (TextView) findViewById(R.id.displayInfo1);
        displayTimetable.setVisibility(View.VISIBLE);
        displayTimetable.setMovementMethod(new ScrollingMovementMethod());
        String type = "timetable";
        BackgroundTask back = new BackgroundTask(this, displayTimetable);
        String date = daysofWeek();
        Calendar c = CurrentDate();

        if(date.equals("Mon")){
            c.add(Calendar.DAY_OF_WEEK,+3);
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
        else if(date.equals("Tue")){
            c.add(Calendar.DAY_OF_WEEK,+2);
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
        else if(date.equals("Wed")){
            c.add(Calendar.DAY_OF_WEEK,+1);
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
        else if(date.equals("Fri")){
            c.add(Calendar.DAY_OF_WEEK,-1);
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
        else if(date.equals("Sat")){
            c.add(Calendar.DAY_OF_WEEK,+5);
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
        else if(date.equals("Sun")){
            c.add(Calendar.DAY_OF_WEEK,+4);
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
        else {
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void Friday (View view){
        displayTimetable = (TextView) findViewById(R.id.displayInfo1);
        displayTimetable.setVisibility(View.VISIBLE);
        displayTimetable.setMovementMethod(new ScrollingMovementMethod());
        String type = "timetable";
        BackgroundTask back = new BackgroundTask(this, displayTimetable);
        String date = daysofWeek();
        Calendar c = CurrentDate();

        if(date.equals("Mon")){
            c.add(Calendar.DAY_OF_WEEK,+4);
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
        else if(date.equals("Tue")){
            c.add(Calendar.DAY_OF_WEEK,+3);
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
        else if(date.equals("Wed")){
            c.add(Calendar.DAY_OF_WEEK,+2);
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
        else if(date.equals("Thu")){
            c.add(Calendar.DAY_OF_WEEK,+1);
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
        else if(date.equals("Sat")){
            c.add(Calendar.DAY_OF_WEEK,+6);
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
        else if(date.equals("Sun")){
            c.add(Calendar.DAY_OF_WEEK,+5);
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
        else {
            Date dt = c.getTime();
            String cdate = changeformat(dt);
            back.execute(type,cdate);
        }
    }

    public void Enter (View view){
        int empty=0;
        EditText cs1 = findViewById(R.id.editText);
        EditText cs2 = findViewById(R.id.editText2);
        EditText cs3 = findViewById(R.id.editText3);
        EditText cs4 = findViewById(R.id.editText4);
        EditText cs5 = findViewById(R.id.editText5);
        TextView display = findViewById(R.id.textView2);

            if (getLab1() != empty && getLab2() != empty && getLab3() != empty && getLab4() != empty && getLab5() != empty) {
                display.setText(getLab1()+" "+getLab2()+" "+getLab3()+" "+getLab4()+" "+getLab5());
//                cs2.setText(getLab2());
//                cs3.setText(getLab3());
//                cs4.setText(getLab4());
//                cs5.setText(getLab5());
            }
            else{
//            cs1.setText("Updated");
//            cs2.setText("Updated");
//            cs3.setText("Updated");
//            cs4.setText("Updated");
//            cs5.setText("Updated");
            int c1 = Integer.parseInt(cs1.getText().toString());
            int c2 = Integer.parseInt(cs2.getText().toString());
            int c3 = Integer.parseInt(cs3.getText().toString());
            int c4 = Integer.parseInt(cs4.getText().toString());
            int c5 = Integer.parseInt(cs5.getText().toString());
            SetLabs(c1,c2,c3,c4,c5);
            display.setText(getLab1()+" "+getLab2()+" "+getLab3()+" "+getLab4()+" "+getLab5());
        }
    }

    public void SetLabs(int c1, int c2, int c3, int c4, int c5){
        try {
            SharedPreferences preference = getSharedPreferences("com.example.labs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preference.edit();
            editor.putInt("CS2001", c1);
            editor.putInt("CS2002", c2);
            editor.putInt("CS2003", c3);
            editor.putInt("CS2004", c4);
            editor.putInt("CS2005", c5);
            editor.apply();
        }catch (Exception e){
            TextView display = findViewById(R.id.textView2);
            display.setText("ERROR idiot try again!");
        }
    }

    public int getLab1(){
        SharedPreferences preference = getSharedPreferences("com.example.labs", Context.MODE_PRIVATE);
        Lab2001 = preference.getInt("CS2001", 0);
        return Lab2001;
    }
    public int getLab2(){
        SharedPreferences preference = getSharedPreferences("com.example.labs", Context.MODE_PRIVATE);
        Lab2002 = preference.getInt("CS2002", 0);
        return Lab2002;
    }
    public int getLab3(){
        SharedPreferences preference = getSharedPreferences("com.example.labs", Context.MODE_PRIVATE);
        Lab2003 = preference.getInt("CS2003", 0);
        return Lab2003;
    }
    public int getLab4(){
        SharedPreferences preference = getSharedPreferences("com.example.labs", Context.MODE_PRIVATE);
        Lab2004 = preference.getInt("CS2004", 0);
        return Lab2004;
    }
    public int getLab5(){
        SharedPreferences preference = getSharedPreferences("com.example.labs", Context.MODE_PRIVATE);
        Lab2005 = preference.getInt("CS2005", 0);
        return Lab2005;
    }
}
