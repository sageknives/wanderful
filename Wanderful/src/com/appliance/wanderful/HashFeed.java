package com.appliance.wanderful;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

//extends schedule for now but won't when we get actual content
public class HashFeed extends Schedule {
	Button eventbtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hash_feed);
		checkCacheRedirect(HashFeed.this);

		// gets all buttons and sets them to nav click listeners
		createNav(HashFeed.this, this.findViewById(R.layout.activity_hash_feed));

		// changes the text view 1 to the first band on the first day
		TextView header = (TextView) findViewById(R.id.header);
		header.setText(events.get(0).getEventName());
		TextView txtView1 = (TextView) findViewById(R.id.text1);
		txtView1.setText(performances.get(0).getPerformanceArtistName());
		TextView txtView2 = (TextView) findViewById(R.id.text2);
		txtView2.setText(performances.get(0).getPerformanceTime());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	    return super.onCreateOptionsMenu(menu);
	}
}
