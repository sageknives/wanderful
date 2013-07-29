package com.appliance.wanderful;
import android.os.Bundle;
import com.appliance.wanderful.R;


public class DetailActivity extends BaseActivity {
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.acitivity_mainschedule_detail);
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
	 
	 
	 
}