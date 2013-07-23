package com.appliance.wanderful;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageButton;
 
public class BaseActivity extends FragmentActivity{

	public static final String SETTINGS_PREFS = "SETTINGS PREFS";
	
	// initializes nav buttons
	ImageButton homeBtn;
	ImageButton mainScheduleBtn;
	ImageButton myScheduleBtn;
	ImageButton hashFeedBtn;
	ImageButton mapBtn;
	Activity curActivity;

	public void createNav(Activity activity, View view)
	{
		this.curActivity = activity;
		homeBtn = (ImageButton) findViewById(R.id.home_btn);
		homeBtn.setOnClickListener(new navClickListeners());
		mainScheduleBtn = (ImageButton) findViewById(R.id.main_schedule_btn);
		mainScheduleBtn.setOnClickListener(new navClickListeners());
		myScheduleBtn = (ImageButton) findViewById(R.id.my_schedule_btn);
		myScheduleBtn.setOnClickListener(new navClickListeners());
		hashFeedBtn = (ImageButton) findViewById(R.id.hash_feed_btn);
		hashFeedBtn.setOnClickListener(new navClickListeners());
		mapBtn = (ImageButton) findViewById(R.id.map_btn);
		mapBtn.setOnClickListener(new navClickListeners());
	}
	private class navClickListeners implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			// Gets the button Id and sends to new activity
			if (homeBtn.getId() == v.getId())
				startActivity(new Intent(curActivity, SearchEvent.class));
			if (mainScheduleBtn.getId() == v.getId())
				startActivity(new Intent(curActivity, MainSchedule.class));
			if (myScheduleBtn.getId() == v.getId())
				startActivity(new Intent(curActivity, MySchedule.class));
			if (hashFeedBtn.getId() == v.getId())
				startActivity(new Intent(curActivity, HashFeed.class));
			if (mapBtn.getId() == v.getId())
				startActivity(new Intent(curActivity, Map.class));
		}
	}
	protected boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
}
