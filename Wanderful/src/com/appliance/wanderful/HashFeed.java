package com.appliance.wanderful;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HashFeed extends BaseActivity {
	Button eventbtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hash_feed);

		// gets all buttons and sets them to nav click listeners
		createNav(HashFeed.this, this.findViewById(R.layout.activity_hash_feed));

		// changes the text view 1 to the first band on the first day
		TextView header = (TextView) findViewById(R.id.header);
		header.setText(events.get(0).getEventName());
		TextView txtView1 = (TextView) findViewById(R.id.text1);
		txtView1.setText(performances.get(0).getPerformanceArtistName());
		TextView txtView2 = (TextView) findViewById(R.id.text2);
		txtView2.setText(performances.get(0).getPerformanceTime());
		eventbtn = (Button) findViewById(R.id.event_btn);
		eventbtn.setOnClickListener(new eventClickListeners());
	}
	private class eventClickListeners implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			// Gets the button Id and sends to new activity
			if (eventbtn.getId() == v.getId())
				startActivity(new Intent(curActivity, EventListActivity.class));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hash_feed, menu);
		return true;
	}
}
