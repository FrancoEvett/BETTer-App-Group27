package com.example.better.ui;

import android.content.Context;
import android.util.Log;

import java.sql.*;

public class DatabaseController {


    public String starter(String date, String day){
    if(day.equals("Monday")){
        String name = "1";
        BackgroundTask backgroundTask = new BackgroundTask(this );
        backgroundTask.execute(name);
    }
    else if (day.equals("Tuesday")){
        String name = "2";
        BackgroundTask backgroundTask = new BackgroundTask(this );
        backgroundTask.execute(name);
    }
    else if (day.equals("Wednesday")) {


        String name = "2";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(name);
    }
        else if (day.equals("Thursday")) {

        String name = "3";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(name);

    }
        else if(day.equals("Friday")) {
        String name = "4";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(name);
    }



        return " ";
    }




}
