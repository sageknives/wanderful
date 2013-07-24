package com.appliance.wanderful;

import java.io.InputStream;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class BaseActivity extends FragmentActivity{

	public static final String SETTINGS_PREFS = "SETTINGS PREFS";
	
	// initializes nav buttons
	ImageButton homeBtn;
	ImageButton mainScheduleBtn;
	ImageButton myScheduleBtn;
	ImageButton hashFeedBtn;
	ImageButton mapBtn;
	Activity curActivity;
	
	public static ArrayList<Performance> performances= new ArrayList<Performance>();
	public static ArrayList<Event> events= new ArrayList<Event>();
	public static String mapUrlLocation = "http://sagegatzke.com/scout/maps/";
	public static int currentEventID;
	public static Bitmap currentMapImage;

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
	public void savePerformancesInfo(JSONObject jsonObject,int eventID)
	{
		/*sets performances to null before adding new list of performances 
		 * for current event. This way only one set of performances is in memory
		 * at a time.
		 */
		performances = null;
		performances= new ArrayList<Performance>();
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
	public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> 
	{
	    ImageView mapImage;
	    ProgressDialog progressDialog;
	    Context currentContext;
	
	    public DownloadImageTask(Context context, ImageView bmImage) {
	    	this.mapImage = bmImage;
	    	this.currentContext = context;
	    }
	
	    protected Bitmap doInBackground(String... urls) {
	    	String urldisplay = urls[0];
	    	Bitmap mIcon11 = null;
	    	try {
	    		InputStream in = new java.net.URL(urldisplay).openStream();
	    		mIcon11 = BitmapFactory.decodeStream(in);
	    	} catch (Exception e) {
	    		Log.e("Error", e.getMessage());
	    		e.printStackTrace();
	    	}
	    	return mIcon11;
	    }
		@Override
		public void onPreExecute() {
			progressDialog = new ProgressDialog(currentContext);
			progressDialog.setMessage("Loading..");
			progressDialog.setCancelable(false);
			progressDialog.setIndeterminate(true);
			progressDialog.show();
		}
	    protected void onPostExecute(Bitmap result) {
	    	mapImage.setImageBitmap(result);
	    	progressDialog.dismiss();
	    	currentMapImage = result;
		}
	}
}
