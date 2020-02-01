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
    String data;
    UserAccountControll userAccountControll = new UserAccountControll();

    DatabaseControllerUser(Context ctx) {
        this.context = ctx;

    }

    @Override
    protected String doInBackground(String... voids) {
        String type = voids[0];

        if (type.equals("login")) {
            String studentID = voids[1];
            //???  String password = voids[2];
            String link = "https://brunelbetterapp.000webhostapp.com/userLogin.php";
            try {
                //Setting up connection to the link and then posting the data to the link (E.g. such as an API call)
                URL url = new URL(link);
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

                //Reading the output data from the website which is currently in JSON format
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

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


                    //call the method ReceiveUserInfo to send data
                    userAccountControll.ReceiveUserInfo(studentID, userName, userEmail, userPassword);
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        } else if (type.equals("register")) {
            String register_url = "";

            try {
                String studentID = voids[1];
                String userName = voids[2];
                String userEmail = voids[3];
                String userPassword = voids[4];

                URL url = new URL(register_url);
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
                    return "User Exit";
                }
                else if(result.equals("User Added")){
                    return "User Added";
                }
                else if(result.equals("Error")){
                    return "Error";
                }
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return null;
    }


    @Override
    protected void onPostExecute(String result) {

            //send the info
    }
}