package com.appliance.wanderful;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;


public class MainSchedule extends BaseActivity implements TabListener {
	SectionsPagerAdapter mSectionsPagerAdapter;

	ViewPager mViewPager;
	static ArrayList<DummyListFragment> tabs;
	static ArrayList<String> titles;
	static FragmentManager fragManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_schedule);
		// gets all buttons and sets them to nav click listeners
		createNav(MainSchedule.this, this.findViewById(R.layout.activity_main_schedule));
		//checks to see if base activity has a list of performances and if it is the performances for this event
		if(performances.size() == 0)
		{
			requestWebInfo();
		}
		else if(performances.get(1).getEventID() != currentEventID)
		{
			currentMapImage = null;
			requestWebInfo();
		}
		else{
			init();
		}	
	}

	private void init() {
		

		// sends a get request for festival information

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		tabs = new ArrayList<DummyListFragment>();
		tabs.add(new DummyListFragment());
		tabs.add(new DummyListFragment());
		tabs.add(new DummyListFragment());
		titles = new ArrayList<String>();
		String title = null;
		for (int i = 0; i < getResources().getStringArray(R.array.titles).length; i++) {

			title = (getResources().getStringArray(R.array.titles)[i]);
			titles.add(i, title);
		}

		fragManager = getSupportFragmentManager();
		mSectionsPagerAdapter = new SectionsPagerAdapter(fragManager, tabs,
				titles);
		/**DummyListFragment frag = new DummyListFragment();
		android.support.v4.app.FragmentTransaction fragmentTransaction = fragManager.beginTransaction ();
		// work here to change Activity fragments (add, remove, etc.).  Example here of adding.
		fragmentTransaction.add (R.id.container, frag);
		fragmentTransaction.commit ();
		**/// Set up the ViewPager with the sections adapter.
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_schedule, menu);
		return true;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

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
	public void requestWebInfo()
	{
		JSONGetClient client = new JSONGetClient(this, jsonGet);
		String ourl = "http://54.218.117.137/scoutservices/jsonobjectcommand.php?user=scoutreader&pass=readscout";
		client.execute(ourl);
	}
	JSONClientListener jsonGet = new JSONClientListener() {

		@Override
		public void onRemoteCallComplete(JSONObject jsonObjectFromNet)throws JSONException 
		{	
			int eventID=1;
			savePerformancesInfo(jsonObjectFromNet,eventID);
			saveEventInfo(jsonObjectFromNet);
			init();
        }
	};
}
