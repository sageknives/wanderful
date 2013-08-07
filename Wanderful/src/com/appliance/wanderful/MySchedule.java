package com.appliance.wanderful;

import android.app.ActionBar.TabListener;
import android.os.Bundle;
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
		checkCacheRedirect(this);
		getActionBar().setTitle("BookMarks");

		createNav(MySchedule.this,
				this.findViewById(R.layout.activity_my_schedule));

		// checks to see if base activity has a list of performances and if it
		// is the performances for this event.
		sortBy = 3;
		init();
		if(emptylist)
		{
			((LinearLayout)findViewById(R.id.no_result)).setVisibility(0);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	    return super.onCreateOptionsMenu(menu);
	}


	
}


