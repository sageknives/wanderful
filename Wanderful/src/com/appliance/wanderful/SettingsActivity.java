package com.appliance.wanderful;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class SettingsActivity extends BaseActivity {
	ListView savedEventListView;
	SettingsAdapter adapter;
	private List<SavedEvents> eventList;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		checkCacheRedirect(this);

	savedEventListView = (ListView)findViewById(R.id.savedlistview);
	populateList();
	adapter= new SettingsAdapter(this, R.layout.activity_setting_list_row, eventList);
	savedEventListView.setAdapter(adapter);
	
	savedEventListView.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View v,
				int position, long id) {

			String ids = eventList.get(position).getEventId();
			Toast.makeText(SettingsActivity.this, ids + "", Toast.LENGTH_LONG)
					.show();

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
	private void populateList() {
		eventList = new ArrayList<SavedEvents>();
		DBHelper db = new DBHelper(this);
		eventList=db.getSavedEvents();
		
	}
}
