package com.example.better;


import java.util.Dictionary;

//this class is an object that will hold information on the user it describes
public class Account {

    public int StudentID;
    public String Name;
    public String Email;
    public String Password;

    //hold information on labs
    public Dictionary<String, String> LabNumbers;

    Account(int userStudentID, String userName, String userEmail, String userPassword){
        //initalise variables to class from contrustor
        StudentID = userStudentID;
        Name = userName;
        Email = userEmail;
        Password = userPassword;
    }
}



























