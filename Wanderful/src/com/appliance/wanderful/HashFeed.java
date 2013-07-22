package com.appliance.wanderful;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HashFeed extends BaseActivity {
	Button eventbtn;

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
	public void saveEventInfo(JSONObject jsonObject)
	{
		for(int i = 1; i<jsonObject.length() + 1;i++)
		{
			try 
        	{
	        	for (int j = 0; j < 1; j++) 
	        	{
	        		int eventID = 0;
					String name = jsonObject.getJSONArray("Day" + (i)).getJSONObject(j).getString("EventName");
					String startDate = jsonObject.getJSONArray("Day" + (i)).getJSONObject(j).getString("EventStartDate");
	            	String length = jsonObject.getJSONArray("Day" + (i)).getJSONObject(j).getString("EventNumDays");
	            	String lastUpdated = "2013-10-23";
	            	String logo = "logo";
	            	String location = jsonObject.getJSONArray("Day" + (i)).getJSONObject(j).getString("EventLocation");
	            	String map = "map";
					events.add(new Event(eventID,name,startDate,length,lastUpdated,logo,location,map));
	        	}
        	} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public void savePerformancesInfo(JSONObject jsonObject,int eventID)
	{
		for(int i = 1; i<jsonObject.length() + 1;i++)
		{
			try 
        	{
	        	for (int j = 0; j < jsonObject.getJSONArray("Day" + (i)).length(); j++) 
	        	{
					String name = jsonObject.getJSONArray("Day" + (i)).getJSONObject(j).getString("SubEventName");
	            	String time = jsonObject.getJSONArray("Day" + (i)).getJSONObject(j).getString("SubEventStartTime");
	            	String stage = jsonObject.getJSONArray("Day" + (i)).getJSONObject(j).getString("SubEventLocation");
	            	String day = jsonObject.getJSONArray("Day" + (i)).getJSONObject(j).getString("SubEventDay");
	            	String description = jsonObject.getJSONArray("Day" + (i)).getJSONObject(j).getString("SubEventDescription");
	            	String image = "image";
	            	String media = "media";
					performances.add(new Performance(eventID,j,name,time,stage,day,description,image,media));
	        	}
        	} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	JSONClientListener jsonGet = new JSONClientListener() {

		@Override
		public void onRemoteCallComplete(JSONObject jsonObjectFromNet)throws JSONException 
		{	
			int eventID=1;
			savePerformancesInfo(jsonObjectFromNet,eventID);
			saveEventInfo(jsonObjectFromNet);
			//changes the text view 1 to the first band on the first day
			TextView header = (TextView)findViewById(R.id.header);
			header.setText(events.get(0).getEventName());
            TextView txtView1 = (TextView)findViewById(R.id.text1);
            txtView1.setText(performances.get(0).getPerformanceArtistName());
            TextView txtView2 = (TextView)findViewById(R.id.text2);
            txtView2.setText(performances.get(0).getPerformanceTime());
    		eventbtn = (Button) findViewById(R.id.event_btn);
    		eventbtn.setOnClickListener(new eventClickListeners());
        }
	};
	private class eventClickListeners implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			// Gets the button Id and sends to new activity
			if (eventbtn.getId() == v.getId())
				startActivity(new Intent(curActivity, EventListActivity.class));
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hash_feed, menu);
		return true;
	}
}
