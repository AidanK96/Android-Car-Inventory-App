package com.example.vehicledatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // get the intent
        Bundle extras = getIntent().getExtras();
        // create a vehicle object from the vehicle object that was passed over from
        // the MainActivity. Notice you use the key ('Make') to retrieve the value/variable needed.
        Vehicle v = (Vehicle) extras.get("Vehicles");
        System.err.println("received from the intent: "+v.getMake());
        //below are all of the text buttons with which display the details of the selected vehicle
        TextView TextVehicleID = (TextView)findViewById(R.id.textVehicle_ID);
        TextVehicleID.setText("Vehicle ID:" + v.getVehicle_id());

       TextView TextMake = (TextView)findViewById(R.id.textVehicle_Make);
        TextMake.setText("Make: " + v.getMake());

        TextView TextModel= (TextView)findViewById(R.id.textVehicle_Model);
        TextModel.setText("Model: " + v.getModel());

        TextView TextYear = (TextView)findViewById(R.id.textVehicle_Year);
        TextYear.setText("Year: " + v.getYear());

        TextView TextPrice = (TextView)findViewById(R.id.textVehicle_Price);
        TextPrice.setText("Price: " + v.getPrice());

        TextView TextLicenseNumber = (TextView)findViewById(R.id.textVehicle_LicenseNumber);
        TextLicenseNumber.setText("License Number: " + v.getLicense_number());

        TextView TextColour = (TextView)findViewById(R.id.textVehicle_Colour);
        TextColour.setText("Colour: " + v.getColour());

        TextView TextNumberDoor = (TextView)findViewById(R.id.textVehicle_NumberDoor);
        TextNumberDoor.setText("Number of Doors: " + v.getNumber_doors());

        TextView TextTransmission = (TextView)findViewById(R.id.textVehicle_Transmission);
        TextTransmission.setText("Transmission: " + v.getTransmission());

        TextView TextMileage = (TextView)findViewById(R.id.textVehicle_Mileage);
        TextMileage.setText("Mileage: " + v.getMileage());

        TextView TextFuelType = (TextView)findViewById(R.id.textVehicle_FuelType);
        TextFuelType.setText("Fuel Type: " + v.getFuel_type());

        TextView TextEngineSize = (TextView)findViewById(R.id.textVehicle_EngineSize);
        TextEngineSize.setText("Engine Size: " + v.getEngine_size());

        TextView TextBodyStyle = (TextView)findViewById(R.id.textVehicle_BodyStyle);
        TextBodyStyle.setText("Body Style: " + v.getBody_style());

        TextView TextCondition = (TextView)findViewById(R.id.textVehicle_Condition);
        TextCondition.setText("Condition: " + v.getCondition());

        TextView TextNotes = (TextView)findViewById(R.id.textVehicle_Notes);
        TextNotes.setText("Notes: " + v.getnotes());
    }
}
