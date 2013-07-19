package com.appliance.wanderful;

import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class HashFeed extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hash_feed);
		
		// gets all buttons and sets them to nav click listeners
		createNav(HashFeed.this,this.findViewById(R.layout.activity_hash_feed));
		
		//sends a get request for festival information
		JSONGetClient client = new JSONGetClient(this, jsonGet);
		String ourl = "http://54.218.117.137/scoutservices/jsonobjectcommand.php?user=scoutreader&pass=readscout";
		client.execute(ourl);
	}

	JSONClientListener jsonGet = new JSONClientListener() {

		@Override
		public void onRemoteCallComplete(JSONObject jsonObjectFromNet)throws JSONException 
		{	
			//changes the text view 1 to the first band on the first day
            TextView txtView1 = (TextView)findViewById(R.id.text1);
            txtView1.setText(jsonObjectFromNet.getJSONArray("Day1").getJSONObject(0).getString("SubEventName"));
            TextView txtView2 = (TextView)findViewById(R.id.text2);
            txtView2.setText(jsonObjectFromNet.getJSONArray("Day1").getJSONObject(0).getString("SubEventStartTime"));
        }
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hash_feed, menu);
		return true;
	}
}
