package com.appliance.wanderful;

import java.util.ArrayList;
import java.util.List;

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
	
	public void savePerformancesInfo(JSONObject jsonObject,int eventID)
	{
		/*sets performances to null before adding new list of performances 
		 * for current event. This way only one set of performances is in memory
		 * at a time.
		 */
		performances = null;
		performances= new ArrayList<Performance>();
		int performancesNum = 0;
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
	            	String image = jsonObject.getJSONArray("Day" + (i)).getJSONObject(j).getString("SubEventImage");
	            	String media = "media";
					performances.add(new Performance(eventID,performancesNum++,name,time,stage,day,description,image,media));
	        	}
        	} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public List<Performance> FilteredPerformancesByDay(int tabNum)
	{
		List<Performance> performanceList = new ArrayList<Performance>();
		for(int i = 0;i < performances.size();i++)
		{
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
			if(performances.get(i).getPerformanceStage().equals(stageNames[tabNum]))
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
			if(performances.get(i).isPerformanceAttending() == true)
			{
            	performanceList.add(performances.get(i));
			}
		}
		return performanceList;
	}
	
	
	
	public void updateSchedule(ArrayList<ScheduleItem> savedSchedule)
	{
		for(int i = 0;i < savedSchedule.size();i++)
		{
			for(int j = 0;j < performances.size();j++)
			{
				if(performances.get(j).getPerformanceID() == Integer.parseInt(savedSchedule.get(i).getPerformanceId()))
				{
					//Log.d("MyTag", "in update schedule" + performances.get(j).getPerformanceID());
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
			for (int i = 0; i < stageNames.length; i++) {
				tabs.add(new DummyListFragment(FilteredPerformancesByStage(i),false));
				title = stageNames[i];
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
		}
		else{
			for (int i = 0; i < dayNames.length; i++) {
				tabs.add(new DummyListFragment(FilteredPerformancesByDay(i),false));
				title = dayNames[i];
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

	public void requestWebInfo() {
		JSONGetClient client = new JSONGetClient(this, jsonGet);
		String ourl = "http://54.218.117.137/scoutservices/jsonobjectcommand.php?user=scoutreader&pass=readscout";
		client.execute(ourl);
	}

	JSONClientListener jsonGet = new JSONClientListener() {

		@Override
		public void onRemoteCallComplete(JSONObject jsonObjectFromNet)
				throws JSONException {
			int eventID = 1;
			savePerformancesInfo(jsonObjectFromNet, eventID);
			saveEventInfo(jsonObjectFromNet);
			// gets all buttons and sets them to nav click listeners

			//rests the baseactivity attendence on redownload.
			DBHelper db = new DBHelper(Schedule.this);
			ArrayList<ScheduleItem> savedSchedule = db.getPerformances();
;
			updateSchedule(savedSchedule);
			db.close();
			init();
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
