package com.iot.smarthomemasterandroid.ui;

import android.content.Context;
import android.os.AsyncTask;

import com.iot.smarthomemasterandroid.remote.AuthorizationSharedHelper;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


public  class SmartHomeAsyncTask extends AsyncTask<String, Void, String> {
    // This is the JSON body of the post
    JSONObject postData;
    String  hash;
    public AsyncResponse delegate = null;//Call back interface

    // This is a constructor that allows you to pass in the JSON body
    public SmartHomeAsyncTask(Map<String, Object> postData , Context context , AsyncResponse delegate) {
        if (postData != null) {
            this.postData = new JSONObject(postData);
            hash = AuthorizationSharedHelper.getAuthorizationHash(context);
            this.delegate = delegate;
        }
    }

    // This is a function that we are overriding from AsyncTask. It takes Strings as parameters because that is what we defined for the parameters of our async task
    @Override
    protected String doInBackground(String... params) {

        try {
            // This is getting the url from the string we passed in
            URL url = new URL("http://192.168.1.2:8090/api/Sensors/Sync");

            // Create the urlConnection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();


            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            urlConnection.setRequestProperty("Content-Type", "application/json");

            urlConnection.setRequestMethod("POST");


            // OPTIONAL - Sets an authorization header
            urlConnection.setRequestProperty("AuthorizationHash", hash);

            // Send the post body
            if (this.postData != null) {
                OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                writer.write(postData.toString());
                writer.flush();
            }

            int statusCode = urlConnection.getResponseCode();

            if (statusCode ==  200) {

                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());

                String response = convertInputStreamToString(inputStream);
                System.out.println(response);
                return response;
                // From here you can convert the string to JSON with whatever JSON parser you like to use
                // After converting the string to JSON, I call my custom callback. You can follow this process too, or you can implement the onPostExecute(Result) method
            } else {
                // Status code is not 200
                // Do something to handle the error
            }

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
       return  null;
    }

    @Override
    protected void onPostExecute(String json) {
        super.onPostExecute(json);
        try {
            JSONObject jsonObject = new JSONObject(json);
            delegate.processFinish(jsonObject.optString("message"));
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    private String convertInputStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
