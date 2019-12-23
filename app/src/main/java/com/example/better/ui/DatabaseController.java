package com.example.better.ui;

import android.util.Log;

import java.sql.*;

public class DatabaseController {


    public static void starter(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bruneltimetable", "bruneluser","Brunel@123");
            Statement stmt = con.createStatement();
        }catch (Exception e){
           Log.d("Error","Error! Unable to connect to Database");
        }
    }




}
