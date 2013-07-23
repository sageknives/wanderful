package com.appliance.wanderful;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

import com.appliance.wanderful.R;


public class DetailActivity extends BaseActivity {
	 @Override
	 protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);

	  // Need to check if Activity has been switched to landscape mode
	  // If yes, finished and go back to the start Activity
	  if (getResources().getConfiguration().orientation == 
	    Configuration.ORIENTATION_LANDSCAPE) {
	   finish();
	   return;
	  }

	  setContentView(R.layout.activity_detail_layout);
	  Bundle extras = getIntent().getExtras();
	  if (extras != null) {
	   String t = extras.getString("time");
	   String n = extras.getString("name");
	   TextView time = (TextView) findViewById(R.id.time);
	   TextView name = (TextView) findViewById(R.id.name);
	   time.setText(t);
	   name.setText(n);
	  }
	 }
}