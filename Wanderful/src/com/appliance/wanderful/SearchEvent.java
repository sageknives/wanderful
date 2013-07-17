package com.appliance.wanderful;

import android.os.Bundle;
import android.view.Menu;

public class SearchEvent extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_event);

		// gets all buttons and sets them to nav click listeners
		createNav(SearchEvent.this,this.findViewById(R.layout.activity_hash_feed));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_event, menu);
		return true;
	}

}
