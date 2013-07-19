package com.appliance.wanderful;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class MainSchedule extends BaseActivity implements TabListener {
	SectionsPagerAdapter mSectionsPagerAdapter;

	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_schedule);

		// gets all buttons and sets them to nav click listeners
		createNav(MainSchedule.this,
				this.findViewById(R.layout.activity_main_schedule));
		
		// sends a get request for festival information
		
		
		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// days

		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding

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

	public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
		final int NUM_TAB = 3;

		public SectionsPagerAdapter(android.support.v4.app.FragmentManager fm) {
			super(fm);
		}
		/** public void onBackPressed() {
		        if (mSectionsPagerAdapter.getCurrentItem() == 0) {
		            super.onBackPressed();
		        } else {
		        	mSectionsPagerAdapter.setCurrentItem(mSectionsPagerAdapter.getCurrentItem() - 1);
		        }
		    }
		    **/
		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.

			if (position == 0) {

				ListFragment listone = DummyListFragment.newInstance(position);
				return listone;
			} else if (position == 1) {

				ListFragment listtwo = DummyListFragment.newInstance(position);
				return listtwo;
			} else {

				ListFragment listthree = DummyListFragment
						.newInstance(position);
				return listthree;
			}
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return NUM_TAB;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.Friday).toUpperCase(l);
			case 1:
				return getString(R.string.Saturday).toUpperCase(l);
			case 2:
				return getString(R.string.Sunday).toUpperCase(l);
			}
			return null;
		}
		 @Override
		    public void destroyItem(ViewGroup container, int position, Object object) {
		        super.destroyItem(container, position, object);
		    }

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		mViewPager.setCurrentItem(tab.getPosition());

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	public static class DummyListFragment extends ListFragment {
		View v;

		/**
		 * static DummyListFragment newInstance(int num) { DummyListFragment f =
		 * new DummyListFragment();
		 * 
		 * // Supply num input as an argument. Bundle args = new Bundle();
		 * args.putInt("num", num); f.setArguments(args);
		 * 
		 * return f; }
		 **/

		static DummyListFragment newInstance(int num) {
			DummyListFragment list = new DummyListFragment();

			Bundle args = new Bundle();
			args.putInt("num", num);

			list.setArguments(args);

			return list;
		}

		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);

		}

		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			v = inflater.inflate(R.layout.activity_listview_layout, container,
					false);
			/**
			 * View name = v.findViewById(R.id.eventname); View time =
			 * v.findViewById(R.id.eventtime); View addButton =
			 * v.findViewById(R.id.addbutton); ((TextView)
			 * name).setText("Fragment #" + mNum);
			 **/

			return v;
		}

		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);

			List<EventItem> eventItems = new ArrayList<EventItem>();

			String name = null;
			String time = null;

			// populate the day/time of the list with static data that I
			// created
			// in string value folder
			for (int i = 0; i < getResources().getStringArray(R.array.names).length; i++) {

				name = (getResources().getStringArray(R.array.names)[i]);
				time = (getResources().getStringArray(R.array.time)[i]);
				// create a temp list to hold the info and then add to our
				// ultimate list that has all the events of the expand list
				EventItem tempList = new EventItem(name, time);
				eventItems.add(tempList);
			}
			ListAdapter adapter = new ListAdapter(getActivity(),
					R.layout.activity_listview_layout, eventItems);
			setListAdapter(adapter);

		}

		public void onListItemClick(ListView l, View v, int position, long id) {
			Log.i("FragmentList", "Item clicked: " + id);
		}
	}

	
}
