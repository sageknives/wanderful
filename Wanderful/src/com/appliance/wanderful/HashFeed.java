package com.appliance.wanderful;

import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class HashFeed extends BaseActivity {

	// initializes nav buttons
	Button homeBtn;
	Button mainScheduleBtn;
	Button myScheduleBtn;
	Button hashFeedBtn;
	Button mapBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hash_feed);
		// gets all buttons and sets them to nav click listeners
		homeBtn = (Button) findViewById(R.id.home_btn);
		homeBtn.setOnClickListener(new navClickListeners());
		mainScheduleBtn = (Button) findViewById(R.id.main_schedule_btn);
		mainScheduleBtn.setOnClickListener(new navClickListeners());
		myScheduleBtn = (Button) findViewById(R.id.my_schedule_btn);
		myScheduleBtn.setOnClickListener(new navClickListeners());
		hashFeedBtn = (Button) findViewById(R.id.hash_feed_btn);
		hashFeedBtn.setOnClickListener(new navClickListeners());
		mapBtn = (Button) findViewById(R.id.map_btn);
		mapBtn.setOnClickListener(new navClickListeners());
	}

	private class navClickListeners implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			// Gets the button Id and sends to new activity
			if (homeBtn.getId() == v.getId())
				startActivity(new Intent(HashFeed.this, SearchEvent.class));
			if (mainScheduleBtn.getId() == v.getId())
				startActivity(new Intent(HashFeed.this, MainSchedule.class));
			if (myScheduleBtn.getId() == v.getId())
				startActivity(new Intent(HashFeed.this, MySchedule.class));
			if (hashFeedBtn.getId() == v.getId())
				startActivity(new Intent(HashFeed.this, HashFeed.class));
			if (mapBtn.getId() == v.getId())
				startActivity(new Intent(HashFeed.this, Map.class));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hash_feed, menu);
		return true;
	}

}
