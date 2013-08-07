package com.appliance.wanderful;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;


public class SettingsActivity extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
	ListView savedEvent = (ListView)findViewById(R.id.savedlistview);
	}
}
