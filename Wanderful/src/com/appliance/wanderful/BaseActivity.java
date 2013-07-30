package com.appliance.wanderful;

import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
	
	public static ArrayList<Event> events= new ArrayList<Event>();
	public static String mapUrlLocation = "http://sagegatzke.com/scout/maps/";
	public static int currentEventID;
	public static Bitmap currentMapImage;
	public String[] dayNames = {"Friday","Saturday","Sunday"};
	public String[] stageNames = {"Main Stage","Vera Stage","ChaCha Stage"};
	public String[] searchNames = {"Search"};
	
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
	
	class navClickListeners implements View.OnClickListener {
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
	            	String map = jsonObject.getJSONArray("Day" + (i)).getJSONObject(j).getString("MapLink");
					events.add(new Event(eventID,name,startDate,length,lastUpdated,logo,location,map));
	        	}
        	} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	
	public void checkCacheRedirect(Activity pastActivity)
	{
		if(events == null)
		{
			startActivity(new Intent(pastActivity, SearchEvent.class));
		}
	}
	

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.search_menu, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.sort_day:
	            startActivity(new Intent(curActivity, MainSchedule.class).putExtra("sortby", 0));
	            return true;
	        case R.id.sort_stage:
	            startActivity(new Intent(curActivity, MainSchedule.class).putExtra("sortby", 1));
	            return true;
	        case R.id.sort_search:
	            startActivity(new Intent(curActivity, MainSchedule.class).putExtra("sortby", 2));
	            return true;
	        default:
	        	return false;
	    }
	    
	}
	
	
			
}
