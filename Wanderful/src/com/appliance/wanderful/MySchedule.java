package com.appliance.wanderful;

import android.os.Bundle;
import android.view.Menu;

public class MySchedule extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_schedule);
		
		// gets all buttons and sets them to nav click listeners
		createNav(MySchedule.this,this.findViewById(R.layout.activity_hash_feed));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_schedule, menu);
		return true;
	}
}
