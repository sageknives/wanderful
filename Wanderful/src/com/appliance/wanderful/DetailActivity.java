package com.appliance.wanderful;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.ImageView;

import com.appliance.wanderful.R;


public class DetailActivity extends BaseActivity {
	 Activity activity = this;
 
	@Override
	  protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.acitivity_mainschedule_detail);
		checkCacheRedirect(this);

	// Show the Up button in the action bar.
			getActionBar().setDisplayHomeAsUpEnabled(true);

			
			
			if (savedInstanceState == null) {
				// Create the detail fragment and add it to the activity
				// using a fragment transaction.
				Bundle arguments = new Bundle();
				arguments.putString(DetailFragment.ARG_ITEM_ID, getIntent()
						.getStringExtra(DetailFragment.ARG_ITEM_ID));
				DetailFragment fragment = new DetailFragment();
				fragment.setArguments(arguments);
				

				getSupportFragmentManager().beginTransaction()
						.add(R.id.performance_detail_container, fragment).commit();
			}
			// gets all buttons and sets them to nav click listeners
			createNav(this, findViewById(R.layout.acitivity_mainschedule_detail));	
			ImageView logo = (ImageView) findViewById(android.R.id.home);
			replaceBitmap(logo, R.drawable.ic_launcher);
/**
	  
	  Bundle extras = getIntent().getExtras();
	  if (extras != null) {
	   String t = extras.getString("time");
	   String n = extras.getString("name");
	   TextView time = (TextView) findViewById(R.id.time);
	   TextView name = (TextView) findViewById(R.id.name);
	   time.setText(t);
	   name.setText(n);
	   **/
	  }
	 
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpTo(this, new Intent(this,
					MainSchedule.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	

	 
}