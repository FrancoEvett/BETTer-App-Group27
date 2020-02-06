package com.example.better;

import android.content.Context;
import android.os.AsyncTask;

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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class DatabaseControllerUser extends AsyncTask<String, Void, String> {

    Context context;

    //not sure why we need this but OK
    DatabaseControllerUser(Context ctx) {
        this.context = ctx;

    }

    //the will get an account object from the database accoring to userID if exist and return null id does not exist
    public Account GetUserAccount(String userID){
            String link = "https://brunelbetterapp.000webhostapp.com/userLogin.php";
            String studentID = userID;
            try {
                //Setting up connection to the link and then posting the data to the link (E.g. such as an API call)
                URL url = new URL(link);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("studentID", "UTF-8") + "=" + URLEncoder.encode(userID, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                //Reading the output data from the website which is currently in JSON format
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String data ="";
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    data += line;
                }

                //Closing all the open connection from the link after submitting request and reading the output
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                //Parsing the JSON data into single variables by passing the data string
                JSONArray jsonArray = new JSONArray(data);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                    String student_ID = jsonObject.get("studentID").toString();
                    String userName = jsonObject.get("userName").toString();
                    String userEmail = jsonObject.get("userEmail").toString();
                    String userPassword = jsonObject.get("userPassword").toString();

                    //build a new account object and return it
                    Account account = new Account(student_ID, userName, userEmail, userPassword);
                    return account;
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //if the process fails the return null
            return null;
    }
    //this will add a new user to database from a account Object and if already exists return false and if successful return true;
    public boolean RegisterAccount(Account account){
        //remeber to NOT decript the password as it will need to be encrypted on teh database
        String register_url = "https://brunelbetterapp.000webhostapp.com/userRegister.php";

        try {
            URL url = new URL(register_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("studentID", "UTF-8") + "=" + URLEncoder.encode(account.StudentID, "UTF-8") + "&"
                    + URLEncoder.encode("userName", "UTF-8") + "=" + URLEncoder.encode(account.Name, "UTF-8") + "&"
                    + URLEncoder.encode("userEmail", "UTF-8") + "=" + URLEncoder.encode(account.Email, "UTF-8") + "&"
                    + URLEncoder.encode("userPassword", "UTF-8") + "=" + URLEncoder.encode(account.Password, "UTF-8");
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
            if(result.equals("User Exist")){ return false;}
            else if(result.equals("User Added")){return false;}
            else if(result.equals("Error")){return false; }

        } catch (MalformedURLException e) {e.printStackTrace();return false;} catch (IOException e) {e.printStackTrace(); return false;}

        //if all is succesfull then return true
        return true;
    }


    //not sure why but ERRORS IF REMOVE
    @Override
    protected String doInBackground(String... voids) {
        return null;
    }
    //not sure why but ERRORS IF REMOVE
    @Override
    protected void onPostExecute(String result) {
    }
}