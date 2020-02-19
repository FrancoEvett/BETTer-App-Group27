package com.example.better;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

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

@RequiresApi(api = Build.VERSION_CODES.O)
public class BackgroundTask extends AsyncTask<String, Void, String> {

    //context
    Context context;
    String paredString = "";
    String fullString = "";
    String initial = "";
    better objBetter = new better();

    BackgroundTask(Context ctx, TextView displayTimetable) {
        this.context = ctx;
    }

    @Override
    protected String doInBackground(String... voids) {
        String type = voids[0];

        if (type.equals("timetable")) {
            try {
                //String link = "http://10.0.2.2/andriodApp/timetableQuery.php";
                String link = "https://brunelbetterapp.000webhostapp.com/timetableQuery.php";
                String date = voids[1];
                URL url = new URL(link);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(date, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                //Reading the results
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    initial += line;
                }
                //Closing the connections
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();


                JSONArray jsonArray = new JSONArray(initial);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                    // String Date =  jsonObject.get("Date").toString();
                    String Activity =      jsonObject.get("Activity").toString();
                    String Description =  jsonObject.get("Description").toString();
                    String Start =  jsonObject.get("Start").toString();
                    String End =  jsonObject.get("End").toString();
                    String Room =  jsonObject.get("Room").toString();
                    // String Staff =   jsonObject.get("Staff").toString();


                    //Passing the variables to displayMethod class
                    objBetter.displayMethod( Activity,Description,Start,End,Room);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return null;
    }


//posing the results

    @Override
    protected void onPreExecute() {
        better.displayTimetable.setText(""); //
    }


    @Override
    protected void onPostExecute(String fullString) {
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
