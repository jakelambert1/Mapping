package com.example.a0lambj41.mapping;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MapTypeListActivity extends ListActivity
{
    private String[] descriptions;
    String[] names;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        names = new String[] { "Regular Map", "Cycle Map" };
        descriptions= new String[] { "Standard", "Show Cycle Routes" };
        MyAdapter adapter = new MyAdapter();
        setListAdapter(adapter);
    }

    public void onListItemClick(ListView lv, View view, int index, long id)
    {
        //Set the cycle map boolean variable to false by default.
        boolean cyclemap=false;

        //Get the ID of the button that was pressed. If its the ID for the cyclemap
        //Button, set the cyclemap boolean to true
        if (index == 1){
            cyclemap = true;
        }

        //Create an intent to send information back to the main activity.
        Intent intent = new Intent();

        //Create a bundle to store names to be sent back
        Bundle bundle = new Bundle();

        //Add the boolean to the bundle
        bundle.putBoolean("com.example.cyclemap", cyclemap);

        //Add the bundle to the intent
        intent.putExtras(bundle);

        //Set an OK result, meaning that everything was successful.
        setResult(RESULT_OK,intent);

        //Close the second activity (this will return to the first)
        finish();
    }

    class MyAdapter extends ArrayAdapter<String> {

        public MyAdapter(){
            super(MapTypeListActivity.this, android.R.layout.simple_list_item_1, names);
        }

        @Override
        public View getView(int index, View convertView, ViewGroup parent) {
            // Inflate our poi entry layout
            LayoutInflater inflater;
            inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(R.layout.poi_entry, parent, false);

            // Populate our poi entry with names
            TextView nameTextView = (TextView) view.findViewById(R.id.poi_name);
            nameTextView.setText (names[index]);

            TextView descriptionTextView = (TextView) view.findViewById(R.id.poi_desc);
            descriptionTextView.setText(descriptions[index]);

            // Return view
            return view;
        }
    }

}
