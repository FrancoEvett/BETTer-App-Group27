package com.example.better;


import android.graphics.Region;

import java.util.Hashtable;

//this class is an object that will hold information on the user it describes
public class Account {

    public String StudentID;
    public String Name;
    public String Email;
    public String Password;

    private boolean encrypted;
    private Hashtable<String, String> hashtable;

    public Account(String userStudentID, String userName, String userEmail, String userPassword){
        PopulateHashTable();
        //initialise variables to class from constructor
        StudentID = userStudentID;
        Name = userName;
        Email = userEmail;
        Password = userPassword;
        EncryptPassword();
    }
    public boolean TestPassword(String passwordIN){
        DecryptPassword();
        //then test if passwords match
        if (passwordIN.equals(Password)){
            EncryptPassword();
            return true;
        }
        EncryptPassword();
        return false;
    }
    public void ChangePassword(String newPassword){
        Password = newPassword;
        EncryptPassword();
    }

    //initialise a hash table
    private void PopulateHashTable(){
        hashtable = new Hashtable<String, String>();
        //basic lower case
        hashtable.put("q", "m");
        hashtable.put("w", "n");
        hashtable.put("e", "b");
        hashtable.put("r", "v");
        hashtable.put("t", "c");
        hashtable.put("y", "x");
        hashtable.put("u", "z");
        hashtable.put("i", "l");
        hashtable.put("o", "k");
        hashtable.put("p", "j");
        hashtable.put("a", "h");
        hashtable.put("s", "g");
        hashtable.put("d", "f");
        hashtable.put("f", "d");
        hashtable.put("g", "s");
        hashtable.put("h", "a");
        hashtable.put("j", "p");
        hashtable.put("k", "o");
        hashtable.put("l", "i");
        hashtable.put("z", "u");
        hashtable.put("x", "y");
        hashtable.put("c", "t");
        hashtable.put("v", "r");
        hashtable.put("b", "e");
        hashtable.put("n", "w");
        hashtable.put("m", "q");
        //basic uppercase
        hashtable.put("Q", "M");
        hashtable.put("W", "N");
        hashtable.put("E", "B");
        hashtable.put("R", "V");
        hashtable.put("T", "C");
        hashtable.put("Y", "X");
        hashtable.put("U", "Z");
        hashtable.put("I", "L");
        hashtable.put("O", "K");
        hashtable.put("P", "J");
        hashtable.put("A", "H");
        hashtable.put("S", "G");
        hashtable.put("D", "F");
        hashtable.put("F", "D");
        hashtable.put("G", "S");
        hashtable.put("H", "A");
        hashtable.put("J", "P");
        hashtable.put("K", "O");
        hashtable.put("L", "I");
        hashtable.put("Z", "U");
        hashtable.put("X", "Y");
        hashtable.put("C", "T");
        hashtable.put("V", "R");
        hashtable.put("B", "E");
        hashtable.put("N", "W");
        hashtable.put("M", "Q");
        //NUMBERS
        hashtable.put("1", "2");
        hashtable.put("2", "1");

        hashtable.put("3", "4");
        hashtable.put("4", "3");

        hashtable.put("5", "6");
        hashtable.put("6", "5");

        hashtable.put("7", "8");
        hashtable.put("8", "7");

        hashtable.put("9", "0");
        hashtable.put("0", "9");
        //SPECIAL SYSMOLS
        hashtable.put("#", ">");
        hashtable.put(">", "#");

        hashtable.put("@", "<");
        hashtable.put("<", "@");

        hashtable.put("-", "+");
        hashtable.put("+", "-");

        hashtable.put("(", ")");
        hashtable.put(")", "(");

        hashtable.put("[", "{");
        hashtable.put("{", "[");

        hashtable.put("]", "}");
        hashtable.put("}", "]");
    }

    //utility methods (Encryption)
    public void EncryptPassword(){
        if (encrypted){return;}
        encrypted = true;

        //create an array of the passwords char characters
        char[] passwordArray = Password.toCharArray();
        String encryptedPassword = "";

        //cycle through string array and encrypt according to the hash table (VALUES nOT RECODED IN THE TABLE WILL BE LEF AS THEY ARE)
        for (char val: passwordArray){
            if (hashtable.containsKey(String.valueOf(val))){
                encryptedPassword += hashtable.get(String.valueOf(val));
            }else {
                encryptedPassword += String.valueOf(val);
            }
        }
        Password = encryptedPassword;
    }
    public void DecryptPassword(){
        if(!encrypted){return;}
        encrypted = false;

        //create an array of the passwords char characters
        char[] passwordArray = Password.toCharArray();
        String decryptedPassword = "";

        //cycle through string array and encrypt according to the hash table (VALUES nOT RECODED IN THE TABLE WILL BE LEF AS THEY ARE)
        for (char val: passwordArray){
            if (hashtable.containsKey(String.valueOf(val))){
                decryptedPassword += hashtable.get(String.valueOf(val));
            }else {
                decryptedPassword += String.valueOf(val);
            }
        }
        Password = decryptedPassword;
    }
}



























