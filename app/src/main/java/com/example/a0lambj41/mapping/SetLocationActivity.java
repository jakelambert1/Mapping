package com.example.a0lambj41.mapping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SetLocationActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_location);

        Button submitButton = (Button)findViewById(R.id.submitButton);
        submitButton.setOnClickListener (this);
    }

    @Override
    public void onClick (View view){
        //retreive our lat and long values from the edit teext boxes
        EditText latitudeEditText = (EditText) findViewById(R.id.LatitudeEditText);
        double latitude = Double.parseDouble(latitudeEditText.getText().toString());

        EditText longitudeEditText = (EditText) findViewById(R.id.LongitudeEditText);
        double longitude = Double.parseDouble(latitudeEditText.getText().toString());

        //assemble our lat long bundle
        Bundle latlongBundle = new Bundle();
        latlongBundle.putDouble("com.0lambj41.latitude", latitude);
        latlongBundle.putDouble("com.0lambj41.longitude", longitude);

        //send the bundle to the parent activity
        Intent intent = new Intent();
        intent.putExtras(latlongBundle);
        setResult(RESULT_OK, intent);
        finish();

    }
}
