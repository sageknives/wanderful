package com.appliance.wanderful;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;


public class SettingsActivity extends BaseActivity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		checkCacheRedirect(this);

	ListView savedEvent = (ListView)findViewById(R.id.savedlistview);
	}
}
