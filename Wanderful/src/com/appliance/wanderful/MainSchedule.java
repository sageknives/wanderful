package com.appliance.wanderful;

import android.os.Bundle;
import android.view.Menu;

public class MainSchedule extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_schedule);

		// gets all buttons and sets them to nav click listeners
		createNav(MainSchedule.this,this.findViewById(R.layout.activity_hash_feed));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_schedule, menu);
		return true;
	}
}
