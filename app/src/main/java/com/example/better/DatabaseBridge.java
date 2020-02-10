package com.example.better;

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

        DatabaseControllerForUsers databaseControllerForUsers = new DatabaseControllerForUsers(this);
        databaseControllerForUsers.execute(type,iD);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("User Login Details",studentID + " "+userName+ " "+ userEmail+ " " +userPass);

        if(studentID == null){
            Log.d("Error:", "Student ID is null - Cannot Log in" );
        }else
        {
            Log.d("Database Bridge:", "Login Data Retreival Sucessfull, converting to account" );
            Account account = new Account(studentID,userName,userEmail,userPass);
            return account;

            }
        return null;

    }

    public Boolean RegisterAccount(Account account){
        String type = "register";
        String user_ID = account.StudentID;
        String user_Name = account.Name;
        String user_Email = account.Email;
        String user_pass = account.Password;

        DatabaseControllerForUsers databaseControllerForUsers = new DatabaseControllerForUsers(this);
        databaseControllerForUsers.execute(type, user_ID,user_Name,user_Email,user_pass);

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d("Error: ", "maybe Null check_info" );
        if(check_info != null){
            if(check_info.equals("User Added")){
                return true;
            }
            else if(check_info.equals("User Exist")){
                return false;
            }
        }
        return false;
    }

}
