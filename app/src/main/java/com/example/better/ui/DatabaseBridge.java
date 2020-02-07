package com.example.better.ui;

import android.util.Log;

import com.example.better.Account;
import com.example.better.DatabaseControllerForUsers;

public class DatabaseBridge {

    public static String studentID = null;
    public static String userName = null;
    public static String userEmail = null;
    public static String userPass = null ;
    public static String check_info;

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
        String type = "register";
        String user_ID = account.StudentID;
        String user_Name = account.Name;
        String user_Email = account.Email;
        String user_pass = account.Password;

        DatabaseControllerForUsers databaseControllerForUsers = new DatabaseControllerForUsers();
        databaseControllerForUsers.execute(type, user_ID,user_Name,user_Email,user_pass);

        return Boolean.parseBoolean(null);
    }

}
