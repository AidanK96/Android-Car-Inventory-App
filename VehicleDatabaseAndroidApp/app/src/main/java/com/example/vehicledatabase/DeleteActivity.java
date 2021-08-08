package com.example.vehicledatabase;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

    public class DeleteActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_delete);
            //The title of page
            final TextView textView = (TextView) findViewById(R.id.VehicleDelete);


            Button deleteButton = (Button) findViewById(R.id.ButtonConfirm);
            final HashMap<String, String> params = new HashMap<>();

            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Gson gson = new Gson();
                    //parses the value the user enters into the textview box
                    int vehicleid = Integer.parseInt(textView.getText().toString());

                    params.put("vehicleid", String.valueOf(vehicleid));
                    String url = null;
                    try {
                        url = "http://10.0.2.2:8005/vehiclesdb/home?" + getDeleteDataString(params);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    performDeleteCall(url, params);
                }
            });
        }
        //performs the deletion call
        private void performDeleteCall(String url, HashMap<String, String> params) {
            URL Delete;
            String response = "";
            try {
                Delete = new URL(url);
                //Create  connection object
                HttpURLConnection conn = (HttpURLConnection) Delete.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("DELETE");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                int responseCode = conn.getResponseCode();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //gets the string from the delete data
        private String getDeleteDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
            StringBuilder result = new StringBuilder();
            boolean first = true;
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (first)
                    first = false;
                else
                    result.append("&");
                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            }
            return result.toString();
        }
    }