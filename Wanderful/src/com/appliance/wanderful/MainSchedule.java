package com.appliance.wanderful;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
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
		createNav(MainSchedule.this,this.findViewById(R.layout.activity_main_schedule));
		if(isNetworkAvailable())
		{
			JSONGetClient oclient = new JSONGetClient(this, ol);
			String ourl = "http://54.218.117.137/scoutservices/jsonobjectcommand.php?user=scoutreader&pass=readscout";
			oclient.execute(ourl);
			System.out.print("network is available!");
		}
		
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_schedule, menu);
		return true;
	}
	
	

	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		int numTabs;
		JSONObject jsonObject;
		public SectionsPagerAdapter(android.support.v4.app.FragmentManager fm, JSONObject jsonObject) {
			super(fm);
			this.numTabs = jsonObject.length() + 1;
			this.jsonObject = jsonObject;
		}


		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			

			for(int i = 0; i < jsonObject.length();i ++)
			{
				if (position == i) {
					ListFragment list = DummyListFragment.newInstance(position,jsonObject);
					return  list;
				}
			}
			ListFragment list = DummyListFragment.newInstance(position,jsonObject);
			return  list;
			/*} else if (position == 1) {

				ListFragment listtwo = DummyListFragment.newInstance(position);
				return  listtwo;
			} else {

				ListFragment listthree = DummyListFragment.newInstance(position);
				return  listthree;
			}*/
		}
			


		@Override
		public int getCount() {
			// Show 3 total pages.
			return numTabs;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			for(int i = 0; i < numTabs -1;i++)
			{
				if(position == i) return (getResources().getStringArray(R.array.day_names)[i]).toUpperCase(l);
				//if(position == numTabs) 
			}
			return  "Search".toUpperCase(l);
				
			/*switch (position) {
			case 0:
				return getString(R.string.Friday).toUpperCase(l);
			case 1:
				return getString(R.string.Saturday).toUpperCase(l);
			case 2:
				return getString(R.string.Sunday).toUpperCase(l);
			default:
				return getString(R.string.search).toUpperCase(l);
			}*/
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

	
	@SuppressLint("ValidFragment")
	public static  class DummyListFragment extends ListFragment {
		View v;
		JSONObject jsonObject;
		int num = 0;
		@SuppressLint("ValidFragment")
		public DummyListFragment(JSONObject jsonObject,int num) {
			// TODO Auto-generated constructor stub
			this.jsonObject = jsonObject;
			this.num = num;
			System.out.print(num);
		}

		/**
		 * static DummyListFragment newInstance(int num) { DummyListFragment
		 * f = new DummyListFragment();
		 * 
		 * // Supply num input as an argument. Bundle args = new Bundle();
		 * args.putInt("num", num); f.setArguments(args);
		 * 
		 * return f; }
		 **/
		
		@SuppressLint("ValidFragment")
		static DummyListFragment newInstance(int num,JSONObject jsonObject) {
			DummyListFragment list = new DummyListFragment(jsonObject,num);
			
	        Bundle args = new Bundle();
	        args.putInt("num", num);
	        
	        
	        list.setArguments(args);

	        return list;
	    }

		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);

		}

		public View onCreateView(LayoutInflater inflater,
				ViewGroup container, Bundle savedInstanceState) {
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
			
			String name = null;
			String time = null;
			ListAdapter adapter = null;
			// populate the day/time of the list with static data that I
			// created
			// in string value folder
			
            	// create an array list of eventItem so that we can put as many
    			// shows/events occurring
            	ArrayList<EventItem> eventItems = new ArrayList<EventItem>();
            	try 
            	{
		        	for (int j = 0; j < jsonObject.getJSONArray("Day" + (num+1)).length(); j++) 
		        	{
						name = jsonObject.getJSONArray("Day" + (num+1)).getJSONObject(j).getString("SubEventName");
		            	//System.out.println("name test " + i + ":" + name);
		            	time = jsonObject.getJSONArray("Day" + (num+1)).getJSONObject(j).getString("SubEventStartTime");
		            	//System.out.println("Time test " + i + ":" + time);
		            	EventItem tempList = (EventItem) new EventItem(name, time);
						eventItems.add(tempList);
		        	}
            	} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	 adapter = new ListAdapter(getActivity(),R.layout.activity_listview_layout,eventItems );
    			
            
			setListAdapter(adapter);

		}

		public void onListItemClick(ListView l, View v, int position,
				long id) {
			Log.i("FragmentList", "Item clicked: " + id);
		}
	}
	JSONClientListener ol = new JSONClientListener() {

		public void onRemoteCallComplete(JSONObject jsonObjectFromNet)throws JSONException 
		{	
			System.out.print(jsonObjectFromNet.getJSONArray("Day1").getJSONObject(0).getString("SubEventName"));
			// Set up the action bar.
			final ActionBar actionBar = getActionBar();
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

			// Create the adapter that will return a fragment for each of the three days
			
			mSectionsPagerAdapter = new SectionsPagerAdapter(
					getSupportFragmentManager(),jsonObjectFromNet);

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
						.setTabListener(MainSchedule.this));
			}
            
        }
	};
}
