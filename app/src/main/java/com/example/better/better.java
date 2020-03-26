package com.example.better;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
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
    @Override
    protected void onResume() {
        super.onResume();
        Displayatbeg();
    }

//    public static final String MyPREFERENCES = "com.example.labs" ;
//    public static final String Lab1 = "l1key";
//    public static final String Lab2 = "l2key";
//    public static final String Lab3 = "l3key";
//    public static final String Lab4 = "l4key";
//    public static final String Lab5 = "l5key";
//
//    public int l1;
//    public int l2;
//    public int l3;
//    public int l4;
//    public int l5;

    SharedPreferences sharedpreferences;

    //Displaying the timetable info from here
    public void displayMethod(String activity, String description, String start, String end, String room) {
                displayTimetable.append(activity + "\n" + description + "\n" + "Start: " + start + "\n" + "End: " + end + "\n" + "Room: " + room + "\n"
                + "----------------------------------------------------------------------------------" + "\n\n");

    }

//    public void Set_Preferences(){
//        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedpreferences.edit();
//        editor.putInt(Lab1,l1);
//        editor.putInt(Lab2,l2);
//        editor.putInt(Lab3,l3);
//        editor.putInt(Lab4,l4);
//        editor.putInt(Lab5,l5);
//        editor.apply();
//    }

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
        Button mon = findViewById(R.id.Monday);
        String date = daysofWeek();
        if(date.equals("Tue")){
            Button tue = findViewById(R.id.Tuesday);
            tue.setBackgroundColor(Color.rgb(128,0,128));
        }
        else if (date.equals("Mon")){
            mon.setBackgroundColor(Color.rgb(128,0,128));
        }
        else if (date.equals("Wed")){
            Button wed = findViewById(R.id.Wednesday);
            wed.setBackgroundColor(Color.rgb(128,0,128));
        }
        else if (date.equals("Thu")){
            Button thu = findViewById(R.id.Thursday);
            thu.setBackgroundColor(Color.rgb(128,0,128));
        }
        else if (date.equals("Fri")){
            Button fri = findViewById(R.id.friday);
            fri.setBackgroundColor(Color.rgb(128,0,128));
        }
        else if (date.equals("Sat")){
            mon.setBackgroundColor(Color.rgb(128,0,128));
            c.add(Calendar.DAY_OF_WEEK,+2);
        }
        else{
            mon.setBackgroundColor(Color.rgb(128,0,128));
            c.add(Calendar.DAY_OF_WEEK,+1);
        }
        Date dt = c.getTime();
        String cdate = changeformat(dt);
        back.execute(type, cdate);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void Monday (View view){
        Button mon = findViewById(R.id.Monday);
        Button tue = findViewById(R.id.Tuesday);
        Button wed = findViewById(R.id.Wednesday);
        Button thu = findViewById(R.id.Thursday);
        Button fri = findViewById(R.id.friday);
        mon.setBackgroundColor(Color.rgb(128,0,128));
        tue.setBackgroundColor(Color.rgb(48, 25, 52));
        wed.setBackgroundColor(Color.rgb(48, 25, 52));
        thu.setBackgroundColor(Color.rgb(48, 25, 52));
        fri.setBackgroundColor(Color.rgb(48, 25, 52));
        displayTimetable = (TextView) findViewById(R.id.displayInfo1);
        displayTimetable.setVisibility(View.VISIBLE);
        displayTimetable.setText("");
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
        Button mon = findViewById(R.id.Monday);
        Button tue = findViewById(R.id.Tuesday);
        Button wed = findViewById(R.id.Wednesday);
        Button thu = findViewById(R.id.Thursday);
        Button fri = findViewById(R.id.friday);
        mon.setBackgroundColor(Color.rgb(48,25,52));
        tue.setBackgroundColor(Color.rgb(128, 0, 128));
        wed.setBackgroundColor(Color.rgb(48, 25, 52));
        thu.setBackgroundColor(Color.rgb(48, 25, 52));
        fri.setBackgroundColor(Color.rgb(48, 25, 52));
        displayTimetable = (TextView) findViewById(R.id.displayInfo1);
        displayTimetable.setVisibility(View.VISIBLE);
        displayTimetable.setText("");
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
        Button mon = findViewById(R.id.Monday);
        Button tue = findViewById(R.id.Tuesday);
        Button wed = findViewById(R.id.Wednesday);
        Button thu = findViewById(R.id.Thursday);
        Button fri = findViewById(R.id.friday);
        mon.setBackgroundColor(Color.rgb(48,25,52));
        tue.setBackgroundColor(Color.rgb(48, 25, 52));
        wed.setBackgroundColor(Color.rgb(128, 0, 128));
        thu.setBackgroundColor(Color.rgb(48, 25, 52));
        fri.setBackgroundColor(Color.rgb(48, 25, 52));
        displayTimetable = findViewById(R.id.displayInfo1);
        displayTimetable.setVisibility(View.VISIBLE);
        displayTimetable.setText("");
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
        Button mon = findViewById(R.id.Monday);
        Button tue = findViewById(R.id.Tuesday);
        Button wed = findViewById(R.id.Wednesday);
        Button thu = findViewById(R.id.Thursday);
        Button fri = findViewById(R.id.friday);
        mon.setBackgroundColor(Color.rgb(48,25,52));
        tue.setBackgroundColor(Color.rgb(48, 25, 52));
        wed.setBackgroundColor(Color.rgb(48, 25, 52));
        thu.setBackgroundColor(Color.rgb(128, 0, 128));
        fri.setBackgroundColor(Color.rgb(48, 25, 52));
        displayTimetable = (TextView) findViewById(R.id.displayInfo1);
        displayTimetable.setVisibility(View.VISIBLE);
        displayTimetable.setText("");
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
        Button mon = findViewById(R.id.Monday);
        Button tue = findViewById(R.id.Tuesday);
        Button wed = findViewById(R.id.Wednesday);
        Button thu = findViewById(R.id.Thursday);
        Button fri = findViewById(R.id.friday);
        mon.setBackgroundColor(Color.rgb(48,25,52));
        tue.setBackgroundColor(Color.rgb(48, 25, 52));
        wed.setBackgroundColor(Color.rgb(48, 25, 52));
        thu.setBackgroundColor(Color.rgb(48, 25, 52));
        fri.setBackgroundColor(Color.rgb(128, 0, 128));
        displayTimetable = (TextView) findViewById(R.id.displayInfo1);
        displayTimetable.setVisibility(View.VISIBLE);
        displayTimetable.setText("");
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
//
//            if (getLab1() != empty && getLab2() != empty && getLab3() != empty && getLab4() != empty && getLab5() != empty) {
//                display.setText(getLab1()+" "+getLab2()+" "+getLab3()+" "+getLab4()+" "+getLab5());
//            }
            try {
                int l1 = Integer.parseInt(cs1.getText().toString());
                int l2 = Integer.parseInt(cs2.getText().toString());
                int l3 = Integer.parseInt(cs3.getText().toString());
                int l4 = Integer.parseInt(cs4.getText().toString());
                int l5 = Integer.parseInt(cs5.getText().toString());
//
//                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedpreferences.edit();
//                editor.putInt(Lab1,l1);
//                editor.putInt(Lab2,l2);
//                editor.putInt(Lab3,l3);
//                editor.putInt(Lab4,l4);
//                editor.putInt(Lab5,l5);
//                editor.apply();
//                Set_Preferences(c1,c2,c3,c4,c5);
            } catch (Exception e){
                display.setText("Enter something please");
            }
    }

    public void termsButtonClick(View view) {
        TextView tv = (TextView) findViewById(R.id.textView13);
        tv.setText(R.string.agree);
        TextView bv = (TextView) findViewById(R.id.textView14);
        bv.setText(R.string.terms);
    }

    public void privacyButtonClick(View view) {
        TextView tv = (TextView) findViewById(R.id.textView13);
        tv.setMovementMethod(new ScrollingMovementMethod());
        tv.setText(R.string.privacy_policy);
        TextView tv1 = (TextView) findViewById(R.id.textView14);
        tv1.setMovementMethod(new ScrollingMovementMethod());
        tv1.setText(R.string.privacy_policy1);
        TextView tv2 = (TextView) findViewById(R.id.textView15);
        tv2.setMovementMethod(new ScrollingMovementMethod());
        tv2.setText(R.string.privacy_policy2);
    }

//    public void SetLabs(int c1, int c2, int c3, int c4, int c5){
//        try {
//            SharedPreferences preference = getSharedPreferences("com.example.labs", Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor = preference.edit();
//            editor.putInt("CS2001", c1);
//            editor.putInt("CS2002", c2);
//            editor.putInt("CS2003", c3);
//            editor.putInt("CS2004", c4);
//            editor.putInt("CS2005", c5);
//            editor.apply();
//        }catch (Exception e){
//            TextView display = findViewById(R.id.textView2);
//            display.setText("ERROR idiot try again!");
//        }
//    }

//    public int getLab1(){
//        SharedPreferences preference = getSharedPreferences("com.example.labs", Context.MODE_PRIVATE);
//        int Lab2001 = preference.getInt("CS2001", 0);
//        return Lab2001;
//    }
//        public int getLab2(){
//            SharedPreferences preference = getSharedPreferences("com.example.labs", Context.MODE_PRIVATE);
//        int Lab2002 = preference.getInt("CS2002", 0);
//        return Lab2002;
//    }
//        public int getLab3(){
//        SharedPreferences preference = getSharedPreferences("com.example.labs", Context.MODE_PRIVATE);
//        int Lab2003 = preference.getInt("CS2003", 0);
//        return Lab2003;
//    }
//        public int getLab4(){
//        SharedPreferences preference = getSharedPreferences("com.example.labs", Context.MODE_PRIVATE);
//        int Lab2004 = preference.getInt("CS2004", 0);
//        return Lab2004;
//    }
//        public int getLab5(){
//        SharedPreferences preference = getSharedPreferences("com.example.labs", Context.MODE_PRIVATE);
//        int Lab2005 = preference.getInt("CS2001", 0);
//        return Lab2005;
//    }

    ImageView wlfb = findViewById(R.id.wlfb_image);
    ImageView lect = findViewById(R.id.lect_image);
    ImageView towc = findViewById(R.id.towc_image);
    ImageView esgw = findViewById(R.id.esgw_image);
    ImageView qdnt = findViewById(R.id.qdnt_image);
    ImageView hwll = findViewById(R.id.howell);
    ImageView orig = findViewById(R.id.originalmap);

    public void wlfb (View view){
        orig.setVisibility(View.INVISIBLE);
        hwll.setVisibility(View.INVISIBLE);
        lect.setVisibility(View.INVISIBLE);
        towc.setVisibility(View.INVISIBLE);
        esgw.setVisibility(View.INVISIBLE);
        qdnt.setVisibility(View.INVISIBLE);
        wlfb.setVisibility(View.VISIBLE);
    }
    public void lect (View view){
        orig.setVisibility(View.INVISIBLE);
        hwll.setVisibility(View.INVISIBLE);
        towc.setVisibility(View.INVISIBLE);
        esgw.setVisibility(View.INVISIBLE);
        qdnt.setVisibility(View.INVISIBLE);
        wlfb.setVisibility(View.INVISIBLE);
        lect.setVisibility(View.VISIBLE);
    }
    public void towc (View view){
        orig.setVisibility(View.INVISIBLE);
        hwll.setVisibility(View.INVISIBLE);
        lect.setVisibility(View.INVISIBLE);
        esgw.setVisibility(View.INVISIBLE);
        qdnt.setVisibility(View.INVISIBLE);
        wlfb.setVisibility(View.INVISIBLE);
        towc.setVisibility(View.VISIBLE);
    }
    public void esgw (View view){
        orig.setVisibility(View.INVISIBLE);
        hwll.setVisibility(View.INVISIBLE);
        lect.setVisibility(View.INVISIBLE);
        towc.setVisibility(View.INVISIBLE);
        qdnt.setVisibility(View.INVISIBLE);
        wlfb.setVisibility(View.INVISIBLE);
        esgw.setVisibility(View.VISIBLE);
    }
    public void qdnt (View view){
        orig.setVisibility(View.INVISIBLE);
        hwll.setVisibility(View.INVISIBLE);
        lect.setVisibility(View.INVISIBLE);
        towc.setVisibility(View.INVISIBLE);
        esgw.setVisibility(View.INVISIBLE);
        wlfb.setVisibility(View.INVISIBLE);
        qdnt.setVisibility(View.VISIBLE);
    }
    public void hwll(View view){
        orig.setVisibility(View.INVISIBLE);
        hwll.setVisibility(View.VISIBLE);
        lect.setVisibility(View.INVISIBLE);
        towc.setVisibility(View.INVISIBLE);
        esgw.setVisibility(View.INVISIBLE);
        wlfb.setVisibility(View.INVISIBLE);
        qdnt.setVisibility(View.INVISIBLE);
    }
    public void originalmap(View view){
        orig.setVisibility(View.VISIBLE);
        hwll.setVisibility(View.INVISIBLE);
        lect.setVisibility(View.INVISIBLE);
        towc.setVisibility(View.INVISIBLE);
        esgw.setVisibility(View.INVISIBLE);
        wlfb.setVisibility(View.INVISIBLE);
        qdnt.setVisibility(View.INVISIBLE);

    }
}
