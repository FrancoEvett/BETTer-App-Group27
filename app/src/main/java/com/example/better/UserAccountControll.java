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

public class UserAccountControll {

    UserAccountControll(){
        //class constructor

    }

    public boolean CreateUser() {
        return false;
    }


    public Account CreateNewAccount(int userStudentID, String userName, String userEmail, String userPassword){

        //create an account object from the parsed data
        Account account = new Account(userStudentID, userName, userEmail, userPassword);




        return null;
    }

    //call this method to log int the user
    public boolean LoginUser(){
        return false;
    }

    //call this method to log out the user
    public boolean LogOut(){
        return false;
    }

    //---------------------------------------------------------------------------------------------------
    //----------------------------------BELOW THIS IS METHODS TO SAVE DATA-------------------------------
    //---------------------------------------------------------------------------------------------------

    //call this method to save load data from a binary file
    private void LoadUserData(){

    }

    //call this method to save user data to a binary file
    private void SaveUserData(){

    }

    public void ReceiveUserInfo(String UserID, String UserName, String UserEmail, String UserPassword){


    }






}
