package com.example.better;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.better.ui.DatabaseBridge;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class DatabaseControllerForUsers extends AsyncTask<String, Void, String> {
    DatabaseBridge databaseBridge = new DatabaseBridge();



    //we are going to get all the data from here/ this class and then i am going to make another class you deals with the true and false part
    @Override
    protected String doInBackground(String... voids) {
        String type = String.valueOf(voids[0]);
      //  String userLoginURL = "https://brunelbetterapp.000webhostapp.com/userLogin.php";
        String userLoginURL = "http://10.0.2.2/andriodApp/userLogin.php";

        if(type.equals("Login")){
            String studentID = String.valueOf(voids[1]);
            try {
                //Sending the data to the server/page
                URL url = new URL(userLoginURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("studentID", "UTF-8") + "=" + URLEncoder.encode(studentID, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                //Reading data from server/Page
                //Reading the results
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                //Closing the connections
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                //Parsing the data and then passing it to the data here
                if(result.equals("Doesn't Exist")){
                    return null;
                    //check something
                }
                else {
                    JSONArray jsonArray = new JSONArray(result);
                    for (int i =0; i <jsonArray.length(); i++){
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        String student_ID = jsonObject.get("studentID").toString();
                        String userName = jsonObject.get("userName").toString();
                        String userEmail = jsonObject.get("userEmail").toString();
                        String userPassword = jsonObject.get("userPassword").toString();


                        databaseBridge.studentID = student_ID;
                        databaseBridge.userName = userName;
                        databaseBridge.userEmail = userEmail;
                        databaseBridge.userPass = userPassword;


                      //  Log.d("Errpr", studentID + " " +userName +" "+ userEmail +" "+userPassword);

                     //   UserAccountControll.checker = true;
                     //   UserAccountControll.userDetails(student_ID,userName,userEmail,userPassword);
                    }

                    //Send this data from this to another method
                        return "";
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {

    }

}
