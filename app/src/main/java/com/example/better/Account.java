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

    //---------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------
    //--------------------------------------EXTERNALLY CALLED METHODS-------------------------------------
    //call this to setup the users labs
    public void SetLabs(String CS2001, String CS2002, String CS2003, String CS2004, String CS2005){
        //first clear the old dictionary
        LabNumbers = null;

        //now add the new lab values
        LabNumbers.put("CS2001", CS2001);
        LabNumbers.put("CS2002", CS2002);
        LabNumbers.put("CS2003", CS2003);
        LabNumbers.put("CS2004", CS2004);
        LabNumbers.put("CS2005", CS2005);
    }

    //call this to get the users lab number for specific module
    public String GetLabNumber(String ModuleCode){
        return LabNumbers.get(ModuleCode);
    }

    //call this to get a dictionary of lab codes and the user lab number
    public Dictionary<String, String> GetLabDictionary(){
        return LabNumbers;
    }

    //------------------------------------EXTERNALLY CALLED METHODS--------------------------------------
    //---------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------

}



























