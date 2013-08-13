package com.appliance.wanderful;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;


public class SettingsActivity extends Schedule {
	ListView savedEventListView;
	SettingsAdapter adapter;
	Activity previousActivity;
	private List<SavedEvents> eventList;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		checkCacheRedirect(SettingsActivity.this);
		previousActivity = curActivity;
		curActivity = this;
		if(!isNetworkAvailable())
		{
			restartActivity(SettingsActivity.class);
		}else{
			savedEventListView = (ListView)findViewById(R.id.savedlistview);
			populateList();
			
			adapter= new SettingsAdapter(this, R.layout.activity_setting_list_row, eventList);
			adapter.notifyDataSetChanged();
			savedEventListView.setAdapter(adapter);
			
			savedEventListView.setOnItemClickListener(new OnItemClickListener() {
		
				@Override
				public void onItemClick(AdapterView<?> parent, View v,
						int position, long id) {
		
					String ids = eventList.get(position).getEventId();
					//Toast.makeText(SettingsActivity.this, ids + "", Toast.LENGTH_LONG)
							//.show();
		
					// get eventId from adapter and set the currentEventId in
					// baseActivity
					//performances.clear();
					currentEventID = Integer.parseInt(eventList.get(position).getEventId());
					// Start an Intent to MainSchedule
					Intent intent = new Intent(SettingsActivity.this, MainSchedule.class);
					intent.putExtra("eventId", currentEventID);
		
					startActivity(intent);
		
				}
			});
		}
	
	}
	private void populateList() {
		eventList = new ArrayList<SavedEvents>();
		DBHelper db = new DBHelper(this);
		eventList=db.getSavedEvents();
		
	}
	
	@Override
	public void onBackPressed() {
		
		//override the back button so the listview can be refreshed
	   Intent setIntent = new Intent(SettingsActivity.this,previousActivity.getClass());
	   startActivity(setIntent);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings_menu, menu);
		return true;
	}
}
