package com.appliance.wanderful;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.appliance.wanderful.ScheduleContent.ScheduleItem;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;


public class Schedule extends BaseActivity implements TabListener, DummyListFragment.ListFragmentItemClickListener{
	public static ArrayList<Performance> performances= new ArrayList<Performance>();
	public static int sortBy = 0;
	//private static final String TAG = "DBAdapter";
	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;
	static ArrayList<DummyListFragment> tabs;
	static ArrayList<String> titles;
	static FragmentManager fragManager;
	private boolean mTwoPane;
	private static final String TAG = "schedule";
	static boolean search =false;
	boolean emptylist = false;
	
	public void savePerformancesInfo(JSONArray jsonArray,int eventID)
	{
		/*sets performances to null before adding new list of performances 
		 * for current event. This way only one set of performances is in memory
		 * at a time.
		 */
		performances = null;
		performances= new ArrayList<Performance>();
		stageNames = null;
		stageNames= new ArrayList<String>();
		
		try 
    	{
        	for (int i = 0; i < jsonArray.length(); i++) 
        	{
        		int PerformancesKey = Integer.parseInt(jsonArray.getJSONObject(i).getString("PerformancesKey"));
				String name = jsonArray.getJSONObject(i).getString("PerformerName");
            	String time = jsonArray.getJSONObject(i).getString("PerformanceTime");
            	String stage = jsonArray.getJSONObject(i).getString("StageName");
            	//adds stages to string array for tab labeling.
            	if(stageNames.size() == 0) stageNames.add(stage);
				Log.d("MyTag", i + ": in save performance artist name: ");

            	for(int j = 0; j < stageNames.size();j++)
            	{
            		if(stageNames.get(j).toString().equals(stage))break;
            		else if(j+1 == stageNames.size()){
            			stageNames.add(stage);
            			break;
            		}
            	}
            	String day = jsonArray.getJSONObject(i).getString("PerformanceDateNumber");
            	String description = jsonArray.getJSONObject(i).getString("PerformerDescription");
            	String kind = jsonArray.getJSONObject(i).getString("PerformerType");
            	String image = jsonArray.getJSONObject(i).getString("PerformerImage");
            	String media = jsonArray.getJSONObject(i).getString("PerformerMedia");
				performances.add(new Performance(eventID,events.get(currentEventID-1).getEventName(),i+1,PerformancesKey,name,time,stage,day,description,kind,image,media));
        	}
    	} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	public List<Performance> FilteredPerformancesByDay(int tabNum)
	{
		List<Performance> performanceList = new ArrayList<Performance>();
		for(int i = 0;i < performances.size();i++)
		{
			Log.d("MyTag", i + ": in FilteredPerformancesByDay");

			if(performances.get(i).getPerformanceDay().equals((tabNum+1) + ""))
			{
            	performanceList.add(performances.get(i));
			}
		}
		return performanceList;
	}
	
	public List<Performance> FilteredPerformancesByStage(int tabNum)
	{
		List<Performance> performanceList = new ArrayList<Performance>();
		for(int i = 0;i < performances.size();i++)
		{
			Log.d("MyTag", i + ": in FilteredPerformancesByStage");

			if(performances.get(i).getPerformanceStage().equals(stageNames.get(tabNum).toString()))
			{
            	performanceList.add(performances.get(i));
			}
		}
		return performanceList;
	}
	
	public List<Performance> FilteredPerformancesBySearch(int tabNum)
	{
		return performances;
	}
	
	public List<Performance> FilteredPerformancesByAttending()
	{
		List<Performance> performanceList = new ArrayList<Performance>();
		for(int i = 0;i < performances.size();i++)
		{
			Log.d("MyTag", i + ": in FilteredPerformancesByAttending");

			if(performances.get(i).isPerformanceAttending() == true)
			{
            	performanceList.add(performances.get(i));
			}
		}
		if(performanceList.size() == 0) emptylist = true;
		return performanceList;
	}
	
	
	
	public void updateSchedule(ArrayList<ScheduleItem> savedSchedule)
	{
		for(int i = 0;i < savedSchedule.size();i++)
		{
			Log.d("MyTag", "savedschedule" + savedSchedule.get(i).getPerformanceId());
			for(int j = 0;j < performances.size();j++)
			{
				Log.d("MyTag", i + ": in updateSchedule");

				if(performances.get(j).getPerformanceKey() == Integer.parseInt(savedSchedule.get(i).getPerformanceKey()))
				{
					Log.d("MyTag", "in update schedule" + performances.get(j).getPerformanceID());
					performances.get(j).setPerformanceAttending(true);
					break;
				}
			}
		}
	}
	
	protected void init() {
		
		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		tabs = new ArrayList<DummyListFragment>();

		titles = new ArrayList<String>();
		String title = null;
		
		if(sortBy == 1)
		{
			

			for (int i = 0; i < stageNames.size(); i++) {
				Log.d("Tag", i + ": " + String.valueOf(search) + " add. stage");
				tabs.add(new DummyListFragment(FilteredPerformancesByStage(i),false));
				title = stageNames.get(i).toString();
				Log.d("Tag", i + ": " + stageNames.get(i).toString() + " add.");
				titles.add(i, title);
			}
		}else if(sortBy == 2)
		{
			for (int i = 0; i < searchNames.length; i++) {
				tabs.add(new DummyListFragment(FilteredPerformancesBySearch(i),true));
				title = searchNames[i];
				titles.add(i, title);
			}
		}else if(sortBy == 3)
		{
			tabs = new ArrayList<DummyListFragment>();
			titles = new ArrayList<String>();
			tabs.add(new DummyListFragment(FilteredPerformancesByAttending(),false));
			titles.add(0, "MySchedule");
			//removes the tab since its not needed in myschedule
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
			
		}
		else{
			int dayNumber = Integer.parseInt(events.get(currentEventID-1).getStartDay());
			
			for (int i = 0; i < Integer.parseInt(events.get(currentEventID-1).getEventLength()); i++) {
				tabs.add(new DummyListFragment(FilteredPerformancesByDay(i),search));
				
				if(i + dayNumber == dayNames.length)dayNumber = -(i);
				int currentTabDay = i + dayNumber;
				title = dayNames[currentTabDay];
				titles.add(i, title);
				
			}
		}
		
		

		fragManager = getSupportFragmentManager();
		mSectionsPagerAdapter = new SectionsPagerAdapter(fragManager, tabs,
				titles);

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each day of the schedule, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}

	}
	
	@Override
	public void onItemSelected(String id) {
		
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(DetailFragment.ARG_ITEM_ID, id);
			DetailFragment fragment = new DetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.performance_detail_container, fragment).commit();

		} else {
		Intent detailIntent = new Intent(this, DetailActivity.class);
		detailIntent.putExtra(DetailFragment.ARG_ITEM_ID, id);
		startActivity(detailIntent);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	    return super.onCreateOptionsMenu(menu);
	}
	
	public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
		private ArrayList<DummyListFragment> mtabs;
		private ArrayList<String> mtitles;

		public SectionsPagerAdapter(android.support.v4.app.FragmentManager fm,
				ArrayList<DummyListFragment> tabs, ArrayList<String> titles) {
			super(fm);

			mtabs = tabs;
			mtitles = titles;
		}

		@Override
		public Fragment getItem(int position) {
			return mtabs.get(position);

		}

		@Override
		public int getCount() {
			return mtabs.size();
		}

		@Override
		public int getItemPosition(Object object) {
			if (mtabs.contains(object)) {
				return POSITION_UNCHANGED;
			}
			return POSITION_NONE;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Log.v("titlePosition", position + "");
			return mtitles.get(position);
		}

	}

	public void requestPerformanceInfo(int EventID) {
		JSONGetClient client = new JSONGetClient(this, jsonGet);
		String ourl = "http://54.218.117.137/scoutservices/eventlist.php?user=scoutreader&pass=readscout&command=performances&eventID=" + EventID;
		client.execute(ourl);
	}

	JSONClientListener jsonGet = new JSONClientListener() {

		@Override
		public void onRemoteCallComplete(JSONArray jsonArrayFromNet)
				throws JSONException {
			int eventID = 1;
			savePerformancesInfo(jsonArrayFromNet, eventID);
			// gets all buttons and sets them to nav click listeners

			//rests the baseactivity attendence on redownload.
			DBHelper db = new DBHelper(Schedule.this);
			ArrayList<ScheduleItem> savedSchedule = db.getPerformances(currentEventID-1);
			Log.d("MyTag", "get performance from db" + savedSchedule.toString());
			updateSchedule(savedSchedule);
			Log.d("MyTag", "i have updated the schedule" + savedSchedule.toString());

			db.close();
			init();
			Log.d("MyTag", "i have made it throught init" + savedSchedule.toString());

		}
	};
	@Override
	public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
}
