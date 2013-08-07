package com.appliance.wanderful;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class SearchEvent extends BaseActivity {
	Button enterBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_event);
		
		JSONGetClient client = new JSONGetClient(this, jsonGets);
		String ourl = "http://54.218.117.137/scoutservices/eventlist.php?user=scoutreader&pass=readscout&command=events";
		client.execute(ourl);
		
		// gets all buttons and sets them to nav click listeners
		//enterBtn = (Button) findViewById(R.id.enter_btn);
		//enterBtn.setOnClickListener(new enterClickListeners());
	}
	public class enterClickListeners implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			// Gets the button Id and sends to new activity

			//if (enterBtn.getId() == v.getId())
				//currentEventID = 1;
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_event, menu);
		return true;
	}
	JSONClientListener jsonGets = new JSONClientListener() {

		@Override
		public void onRemoteCallComplete(JSONArray jsonArrayFromNet)
				throws JSONException {
			saveEventInfo(jsonArrayFromNet);
			startActivity(new Intent(SearchEvent.this, SearchPage.class));

			//init();
		}
	};
}