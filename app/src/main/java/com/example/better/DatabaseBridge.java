package com.example.better;

import android.util.Log;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class DatabaseBridge {

    public static String studentID ;
    public static String userName ;
    public static String userEmail;
    public static String userPass;
    public static String userToken;
    public static String check_info;
    public static Boolean await = true;


    public Account userLogin(String userID){
        String type = "Login";
        String iD = userID;

        DatabaseControllerForUsers databaseControllerForUsers = new DatabaseControllerForUsers();
        databaseControllerForUsers.execute(type,iD);
        try {
            databaseControllerForUsers.get(1000, TimeUnit.MILLISECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d("Error",studentID + " "+userName+ " "+ userEmail+ " " +userPass);

        if(studentID == null){
            Log.d("Error:", "due to null" );
        }else
        {
            Account account = new Account(studentID,userName,userEmail,userPass, userToken);
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
        String user_token = account.Token;

        DatabaseControllerForUsers databaseControllerForUsers = new DatabaseControllerForUsers();
        databaseControllerForUsers.execute(type, user_ID,user_Name,user_Email,user_pass, user_token);
        try {
            databaseControllerForUsers.get(1000, TimeUnit.MILLISECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(1000);
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

    public void awaitMethod(){
        await = false;
    }

}
