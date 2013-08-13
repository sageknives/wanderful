package com.appliance.wanderful;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;


public class MainSchedule extends Schedule{


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_schedule);
		checkCacheRedirect(MainSchedule.this);
		DBHelper db = new DBHelper(MainSchedule.this);
		db.insertCurrentEvent(events.get(currentEventID-1));
		db.close();
		createNav(MainSchedule.this,
				this.findViewById(R.layout.activity_main_schedule));
		getActionBar().setTitle(events.get(currentEventID-1).getEventName());
		if(!isNetworkAvailable())
		{
			restartActivity(MainSchedule.class);
		}else{

			sortBy = getIntent().getIntExtra("sortby", 0);
			sortBy = getIntent().getIntExtra("sortby", 0);
			//int EventID= getIntent().getExtras() .getInt("eventId");
			
			// checks to see if base activity has a list of performances and if it
			// is the performances for this event
			if (performances.size() == 0) {
				requestPerformanceInfo(currentEventID);
				Log.d("TAG", "else event name "+ (currentEventID-1) +": " + events.get(currentEventID-1).getEventName());
	
			} else if (performances.get(0).getEventID() != currentEventID) {
				currentMapImage = null;
				requestPerformanceInfo(currentEventID);
	
			} else {
				init();
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	    return super.onCreateOptionsMenu(menu);
	}


	
}
