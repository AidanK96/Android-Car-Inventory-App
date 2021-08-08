package com.example.vehicledatabase;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    String[] VehicleNames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ListView VehicleList = findViewById(R.id.VehicleList);

        //Making a http call
        HttpURLConnection urlConnection;
        InputStream in = null;
        try {
            // the url we wish to connect to
            URL url = new URL("http://10.0.2.2:8005/vehiclesdb/home");
            // open the connection to the specified URL
            urlConnection = (HttpURLConnection) url.openConnection();
            // get the response from the server in an input stream
          in = new BufferedInputStream(urlConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* covert the input stream to a string */
        String response = convertToString(in);
        // print the response to android monitor/log cat
        System.out.println("Server response = " + response);
        final ArrayList<Vehicle> allVehicles = new ArrayList<Vehicle>();


        try{
            //Creates a json array for the vehicle
            JSONArray jsonArray = new JSONArray(response);

            VehicleNames = new String[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++){

                int vehicle_id = Integer.decode(jsonArray.getJSONObject(i).get("vehicle_id").toString());
                String make = jsonArray.getJSONObject(i).get("make").toString();
                String model = jsonArray.getJSONObject(i).get("model").toString();
                int year = Integer.decode(jsonArray.getJSONObject(i).get("year").toString());
                int price = Integer.decode(jsonArray.getJSONObject(i).get("price").toString());
                String license_number = jsonArray.getJSONObject(i).get("license_number").toString();
                String colour = jsonArray.getJSONObject(i).get("colour").toString();
                int number_doors = Integer.decode(jsonArray.getJSONObject(i).get("number_doors").toString());
                String transmission = jsonArray.getJSONObject(i).get("transmission").toString();
                int mileage = Integer.decode(jsonArray.getJSONObject(i).get("mileage").toString());
                String fuel_type = jsonArray.getJSONObject(i).get("fuel_type").toString();
                int engine_size = Integer.decode(jsonArray.getJSONObject(i).get("engine_size").toString());
                String body_style = jsonArray.getJSONObject(i).get("body_style").toString();
                String condition = jsonArray.getJSONObject(i).get("condition").toString();
                String notes = jsonArray.getJSONObject(i).get("notes").toString();

                System.out.println("name = " +make);

                VehicleNames [i] = make +" " + model +" " + license_number +" "+ year;
                //Creates a new vehicle v
                Vehicle v = new Vehicle(vehicle_id, make, model, year, price, license_number, colour, number_doors, transmission, mileage, fuel_type, engine_size, body_style, condition, notes);
                allVehicles.add(v);
            }

        } catch (JSONException e){
            e.printStackTrace();
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, VehicleNames);
        //set the adapter to the listview
        VehicleList.setAdapter(arrayAdapter);
        //When the user clicks on an item in the list it takes them to the details activity page
        VehicleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "You pressed " + allVehicles.get(i).getMake(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("Vehicles", allVehicles.get(i));
                startActivity(intent);
            }

        });

        Button deleteButton = (Button) findViewById(R.id.buttonDelete);
        final HashMap<String, String> params = new HashMap<>();
        deleteButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DeleteActivity.class);
                startActivity(intent);
            }
        });
    }
    //converts the input to a strong
    private String convertToString(InputStream in) {
        java.util.Scanner s = new java.util.Scanner(in).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
