package com.appliance.wanderful;

import android.app.ActionBar.TabListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;



public class MySchedule extends Schedule implements TabListener, DummyListFragment.ListFragmentItemClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_schedule);
		checkCacheRedirect(MySchedule.this);
		getActionBar().setTitle("BookMarks");

		createNav(MySchedule.this,
				this.findViewById(R.layout.activity_my_schedule));

		// checks to see if base activity has a list of performances and if it
		// is the performances for this event.
		sortBy = 3;
		if (performances.size() == 0) {
			requestPerformanceInfo(currentEventID);
			Log.d("TAG", "else event name "+ (currentEventID-1) +": " + events.get(currentEventID-1).getEventName());
			if(emptylist)((LinearLayout)findViewById(R.id.no_result)).setVisibility(0);

		} else if (performances.get(0).getEventID() != currentEventID) {
			currentMapImage = null;
			requestPerformanceInfo(currentEventID);
			if(emptylist)((LinearLayout)findViewById(R.id.no_result)).setVisibility(0);
		} else {
			init();
			if(emptylist)((LinearLayout)findViewById(R.id.no_result)).setVisibility(0);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	    return super.onCreateOptionsMenu(menu);
	}


	
}


