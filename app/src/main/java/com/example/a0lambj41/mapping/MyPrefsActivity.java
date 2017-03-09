package com.example.a0lambj41.mapping;

/**
 * Created by 0lambj41 on 09/03/2017.
 */
import android.os.Bundle;
import android.preference.PreferenceActivity;

public class MyPrefsActivity extends PreferenceActivity
{
    public void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}


