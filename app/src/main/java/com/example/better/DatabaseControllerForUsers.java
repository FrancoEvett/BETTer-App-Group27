package com.example.better;

import android.os.AsyncTask;

import com.example.better.ui.DatabaseBridge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class DatabaseControllerForUsers extends AsyncTask<String, Void, String> {
    DatabaseBridge databaseBridge = new DatabaseBridge();



    //we are going to get all the data from here/ this class and then i am going to make another class you deals with the true and false part
    @Override
    protected String doInBackground(String... voids) {
        String type = voids[0];

        String userLoginURL = "https://brunelbetterapp.000webhostapp.com/userLogin.php";
       // String userLoginURL = "http://10.0.2.2/andriodApp/userLogin.php";

        String userRegURL ="https://brunelbetterapp.000webhostapp.com/userRegister.php";
       //String userRegURL = "http://10.0.2.2/andriodApp/userRegister.php";

        if(type.equals("Login")){
            String studentID = voids[1];
            try{  //Sending the data to the server/page
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

                }


                return "";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else if(type.equals("register")){
            String studentID = voids[1];
            String userName = voids[2];
            String userEmail = voids[3];
            String userPassword = voids[4];
            try{
                URL url = new URL(userRegURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("studentID", "UTF-8") + "=" + URLEncoder.encode(studentID, "UTF-8") + "&"
                        + URLEncoder.encode("userName", "UTF-8") + "=" + URLEncoder.encode(userName, "UTF-8") + "&"
                        + URLEncoder.encode("userEmail", "UTF-8") + "=" + URLEncoder.encode(userEmail, "UTF-8") + "&"
                        + URLEncoder.encode("userPassword", "UTF-8") + "=" + URLEncoder.encode(userPassword, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

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

                //sending true or false or any info
                if(result.equals("User Exist")){
                    databaseBridge.check_info = "User Exist";
                    return result;
                }


                else if(result.equals("User Added")){
                    databaseBridge.check_info = "User Added";
                    return result;
                }
                else if(result.equals("Error")){
                    databaseBridge.check_info= "Error";
                    return  result;
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }








        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
