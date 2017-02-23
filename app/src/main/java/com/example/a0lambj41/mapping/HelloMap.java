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

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
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
            startActivityForResult(intent,0);
            // react to the menu item being selected...
            return true;
        }
        else if (item.getItemId() == R.id.setlocation)
        {
            Intent intent = new Intent(this, SetLocationActivity.class);
            startActivityForResult(intent, 1);
            return true;
        }

        else if (item.getItemId() == R.id.ChooseMapStyleList)
        {
            Intent intent = new Intent(this, MapTypeListActivity.class);
            startActivityForResult(intent, 0);
            return true;
        }
        return false;
    }


    protected void onActivityResult(int requestCode,int resultCode,Intent intent)
    {

        if(requestCode==0)
        {

            if (resultCode==RESULT_OK)
            {
                Bundle extras=intent.getExtras();
                boolean cyclemap = extras.getBoolean("com.example.cyclemap");
                if(cyclemap==true)
                {
                    mv.setTileSource(TileSourceFactory.CYCLEMAP);
                }
                else
                {
                    mv.getTileProvider().setTileSource(TileSourceFactory.MAPNIK);
                }
            }

        }
        else if (requestCode==1)
        {
            if (resultCode==RESULT_OK) {
                Bundle latlongBundle = intent.getExtras();
                double latitude = latlongBundle.getDouble("com.0lambj41.latitude");
                double longitude = latlongBundle.getDouble("com.0lambj41.longitude");

                mv.getController().setCenter(new GeoPoint(longitude, latitude));
            }
        }

    }
}