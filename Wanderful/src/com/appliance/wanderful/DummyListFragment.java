package com.appliance.wanderful;

import java.util.ArrayList;
import java.util.List;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public   class DummyListFragment extends ListFragment {
	View v;
	public static final String ARG_SECTION_NUMBER = "section_number";
	public Bundle setValues;
	public DummyListFragment()
	{
		
	}
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		savedInstanceState = this.getArguments();
		
		v = inflater.inflate(R.layout.activity_listview_layout, container,
				false);
		

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
		
		String time = ((TextView) v.findViewById(R.id.eventtime)).getText().toString();
		String name = ((TextView) v.findViewById(R.id.eventname)).getText().toString();
	
		/**
		  DetailFragment fragment = (DetailFragment) getFragmentManager()
		    .findFragmentById(R.id.details_Fragment);
		  if (fragment != null && fragment.isInLayout()) {
		   fragment.setText(time);
		   fragment.setText(name);
		  } else {
		   Intent intent = new Intent(getActivity().getApplicationContext(),
		     DetailActivity.class);
		   intent.putExtra("time", time);
		   intent.putExtra("name", name);
		   startActivity(intent);
**/
		  }

		 
	
	
	
}