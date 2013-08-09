package com.appliance.wanderful;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
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
	static Activity curActivity;
	
	public static ArrayList<Event> events= new ArrayList<Event>();
	public static String mapUrlLocation = "http://sagegatzke.com/scout/maps/";
	public static int currentEventID;
	public static Bitmap currentMapImage;
	public String[] dayNames = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
	public static ArrayList<String> stageNames = new ArrayList<String>();
	public String[] searchNames = {"Search"};
	String logoImgUrl ="http://sagegatzke.com/scout/imageslogo/";

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
				startActivity(new Intent(curActivity, SearchPage.class));
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
	
	public void saveEventInfo(JSONArray jsonArray)
	{
		events = null;
        events = new ArrayList<Event>();
			try 
        	{
	        	for (int i = 0; i < jsonArray.length(); i++) 
	        	{
	        		int EventKey = Integer.parseInt(jsonArray.getJSONObject(i).getString("EventKey"));
					String name = jsonArray.getJSONObject(i).getString("EventName");
					String startDate = jsonArray.getJSONObject(i).getString("EventStartDate");
	            	String length = jsonArray.getJSONObject(i).getString("EventDuration");
	            	String startDay = jsonArray.getJSONObject(i).getString("DayNamesKey");
	            	String lastUpdated = jsonArray.getJSONObject(i).getString("DateUpdated");
	            	String logo = jsonArray.getJSONObject(i).getString("EventLogo");
	            	String locationName = jsonArray.getJSONObject(i).getString("LocationName");
	            	String LocationAddress = jsonArray.getJSONObject(i).getString("LocationAddress");
	            	String LocationCity = jsonArray.getJSONObject(i).getString("LocationCity");
	            	String LocationState = jsonArray.getJSONObject(i).getString("LocationState");
	            	String LocationZip = jsonArray.getJSONObject(i).getString("LocationZip");
	            	String map = jsonArray.getJSONObject(i).getString("EventMap");
					events.add(new Event(EventKey,name,startDate,length,startDay,lastUpdated,logo,locationName,LocationAddress,LocationCity,LocationState,LocationZip,map));
	        	}
        	} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
	}
	public List<Event> GetEventList()
	{
		return events;
	}
	
	public void checkCacheRedirect(Activity pastActivity)
	{
		if(events.size() == 0)
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
	        case R.id.home:
	            startActivity(new Intent(curActivity, SearchEvent.class).putExtra("sortby", 0));
	            return true;
	        case R.id.settings:
	            startActivity(new Intent(curActivity, SettingsActivity.class).putExtra("sortby", 0));
	            return true;
	        default:
	        	return false;
	    }
	}			
}
