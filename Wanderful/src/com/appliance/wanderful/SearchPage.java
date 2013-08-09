package com.appliance.wanderful;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class SearchPage extends Schedule {
	EditText filterText;
	
	ListView view;
	private List<Event> eventList = new ArrayList<Event>();
	private static final String TAG = "schedule";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_page);
		curActivity = this;
		checkCacheRedirect(this);
		view = (ListView) findViewById(R.id.searchlistview);
		eventList = GetEventList();
		final SearchAdapter adapter = new SearchAdapter(this, R.layout.activity_list_row, eventList);

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

				String ids = Integer.toString(eventList.get(position)
						.getEventID());
				//Toast.makeText(SearchPage.this, ids + "", Toast.LENGTH_LONG)
						//.show();

				// get eventId from adapter and set the currentEventId in
				// baseActivity
				performances.clear();
				currentEventID = eventList.get(position).getEventID();
				// Start an Intent to MainSchedule
				Intent intent = new Intent(SearchPage.this, MainSchedule.class);
				intent.putExtra("eventId", currentEventID);

				startActivity(intent);

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings_menu, menu);
		return true;
	}
	    
	
}

