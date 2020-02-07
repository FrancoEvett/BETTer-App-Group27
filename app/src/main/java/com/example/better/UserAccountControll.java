package com.example.better;


//CLASS USE

//use this class by creating an instance where it is needed
//make the instant persistent by globally declaring it outside of a any methods
//create this from a setup method for use
//this call will store the details of any logged in user
//call methods from this class to manipulate the users information
//it has recently come to my attention that we do not acualy need to store the user details on a database
//none of the data needs to be accessed by anyone else spo here i will store all of the information
//on lab data in this class - i will be making binary files on the local storage (this give us more to write about since we have already done databases)

import com.example.better.ui.DatabaseBridge;

public class UserAccountControll {

    private DatabaseControllerUser databaseControllerUser = new DatabaseControllerUser(null);
    private DatabaseBridge databaseBridge = new DatabaseBridge();
    private Account loggedINAccount;
    public static Boolean checker;
    //call this method to Login (Returns true id successful)
    public boolean Login(String userID, String UserPassword){
        //check if user is already logged in if so return false
        if (loggedINAccount != null) {
            return false;
        }

        //request user account details from database

//        Account account = databaseControllerUser.GetUserAccount(userID);   //Can u please call a different method

        //Account account = databaseBridge.userLogin(userID);

       // databaseBridge.userLogin(userID);
        Account account = databaseBridge.userLogin(userID);
     //   Account account = databaseBridge.userDetail();


        // From here we exit and then use the useDetail for the to check the user details as we pass the info there
        //I cannot chnage my class as there are pre-build libries with doesn't work outside
        //Please use the userDetails for further check




        //now check if the account exists in the database
        if (account == null){
            return false;
        }//user does not exist

        //user exist in database, now check to see if passwords match
        if (!account.TestPassword(UserPassword)){
            return false;
        }

        //everything checks out allow the user to login
        loggedINAccount = account;

        return true;
    }

    //call this method to logOut (Always successful)
    public void LogOut(){
        //log out the user
        loggedINAccount = null;
    }

    //call this method to create a new account(Returns true id successful)
    public boolean CreateNewAccount(String userID, String userName, String userEmail, String userPassword){
        //compile a new account object
        Account account = new Account(userID, userName, userEmail, userPassword);
        if (databaseControllerUser.RegisterAccount(account)){
            return true;
        }
        return false;
    }

    //call this method to change password (Returns True if successful and False if not)
    public boolean ChangePassword(String userCurentPassword, String newPassword){
        if (loggedINAccount == null){return false;}//user not logged in
        if (!loggedINAccount.TestPassword(userCurentPassword)){return false;}//wrong current password

        //everythn gis good go ahed and change the password
        loggedINAccount.ChangePassword(newPassword);
        return true;
    }




    public static void userDetails(String studentID, String userName, String userEmail, String userPassword){
        if (checker){
            String student_ID = studentID;
            String user_Name = userName;
            String user_Email = userEmail;
            String user_Pass = userPassword;
        }
        else {
            //User doesn't exist

        }


    }


}
