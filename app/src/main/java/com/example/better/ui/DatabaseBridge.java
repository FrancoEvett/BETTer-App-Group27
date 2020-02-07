package com.example.better.ui;

import android.util.Log;

import com.example.better.Account;
import com.example.better.DatabaseControllerForUsers;

public class DatabaseBridge {

    public static String studentID = null;
    public static String userName = null;
    public static String userEmail = null;
    public static String userPass = null ;


    public Account userLogin(String userID){
        String type = "Login";
        String iD = userID;

        DatabaseControllerForUsers databaseControllerForUsers = new DatabaseControllerForUsers();
        databaseControllerForUsers.execute(type,iD);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("Error",studentID + " "+userName+ " "+ userEmail+ " " +userPass);

        if(studentID == null){
            Log.d("Error:", "due to null" );
        }else
        {
            Account account = new Account(studentID,userName,userEmail,userPass); return account;
            }
        return null;

    }

    public boolean RegisterAccount(Account account){


        return Boolean.parseBoolean(null);
    }

}
