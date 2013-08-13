package com.appliance.wanderful;

import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

public class SearchPage extends Schedule {
	EditText filterText;

	ListView view;
	private List<Event> eventList = new ArrayList<Event>();
	private static final String TAG = "schedule";

	// int launchingEvent = getIntent().getExtras() .getInt("SearchPage");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_page);

		curActivity = this;
		if(!isNetworkAvailable())
		{
			restartActivity(SearchPage.class);
		}else{
			
		
		if (events.size() <= 1)
			startActivity(new Intent(SearchPage.this, SearchEvent.class));
		// checkCacheRedirect(this);

		/**
		 * this code is not working full I am trying to figure out a way to
		 * bypass the searchpage listview and launch the schedule class if
		 * (geteventID != null) { currentEventID =
		 * geteventID.getInt("SearchPage"); Intent intents = new
		 * Intent(SearchPage.this, MainSchedule.class);
		 * Toast.makeText(SearchPage.this, currentEventID + "",
		 * Toast.LENGTH_LONG) .show();
		 * 
		 * startActivity(intents); }
		 **/

		//ImageView logo = (ImageView) findViewById(android.R.id.home);
		//replaceBitmap(logo, R.drawable.ic_launcher);
		view = (ListView) findViewById(R.id.searchlistview);
		eventList = GetEventList();
		final SearchAdapter adapter = new SearchAdapter(this,
				R.layout.activity_list_row, eventList);

		view.setAdapter(adapter);

		view.setTextFilterEnabled(true);

		filterText = (EditText) findViewById(R.id.searchfiltertext);
		filterText.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence s, int arg1, int arg2,
					int arg3) {
				adapter.getFilter().filter(s.toString());
				adapter.notifyDataSetChanged();

			}
		});

		view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				// String ids = Integer.toString(eventList.get(position)
				// .getEventID());
				// Toast.makeText(SearchPage.this, ids + "", Toast.LENGTH_LONG)
				// .show();

				// get eventId from adapter and set the currentEventId in
				// baseActivity
				performances.clear();
				currentMapImage = null;
				currentEventID = eventList.get(position).getEventKey();

				// Start an Intent to MainSchedule
				Intent intent = new Intent(SearchPage.this, MainSchedule.class);
				intent.putExtra("eventId", currentEventID);

				startActivity(intent);

			}
		});
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings_menu, menu);
		return true;
	}

}
