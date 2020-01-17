package com.example.better.ui;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

public class BackgroundTask extends AsyncTask<String,Void,String> {

    DatabaseController context;


    public BackgroundTask(DatabaseController context){

        this.context = context;

    }




    @Override
    protected String doInBackground(String... voids) {
        String link_url = "";


        return null;
    }

    @Override
    protected void onPreExecute() {}


    @Override
    protected void onPostExecute(String result) {

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
