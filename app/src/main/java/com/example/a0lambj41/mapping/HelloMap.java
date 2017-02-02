package com.example.a0lambj41.mapping;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import org.osmdroid.views.MapView;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.config.Configuration;

public class HelloMap extends Activity
{

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
    }
}