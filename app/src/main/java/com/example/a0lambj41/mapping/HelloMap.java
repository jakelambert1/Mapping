package com.example.a0lambj41.mapping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.osmdroid.views.MapView;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.config.Configuration;


public class HelloMap extends Activity implements View.OnClickListener {
    /** Called when the activity is first created. */

    MapView mv;

    public void onCreate(Bundle savedInstanceState) //runs when the activity is opened
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // this line tells OpenStreetMap about our app.
        // If you miss this out, you might get banned from OSM servers
        Configuration.getInstance().load
                (this, PreferenceManager.getDefaultSharedPreferences(this));

        mv = (MapView)findViewById(R.id.map1);

        mv.setBuiltInZoomControls(true); //sets plus and minus icons to zoom in & out
        mv.getController().setZoom(14); //sets it to street level. Higher numbers = more zoomed in
        mv.getController().setCenter(new GeoPoint(40.1,22.5)); //co ordinates of a specific place

        Button b = (Button)findViewById(R.id.button);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        EditText latEditText = (EditText)findViewById(R.id.LatitudeEditText); //The latitude textbox
        EditText longEditText = (EditText)findViewById(R.id.LongitudeEditText); //The longitude textbox

        double lat = Double.parseDouble(latEditText.getText().toString()); //Enter number from keyboard
        double lon = Double.parseDouble(longEditText.getText().toString()); //Enter number from keyboard

        mv.getController().setCenter(new GeoPoint(lat,lon));
    }

    public boolean onCreateOptionsMenu(Menu menu) //expands it into a java menu object
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_hello_map, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) //handles the user selecting a menu item
    {
        if(item.getItemId() == R.id.choosemap)
        {
            //a message to do something, to launch the secondary activity or entirely seperate apps i.e. the phone app
            Intent intent = new Intent(this,MapChooseActivity.class);
            startActivity(intent);
            // react to the menu item being selected...
            return true;
        }
        return false;
    }
}