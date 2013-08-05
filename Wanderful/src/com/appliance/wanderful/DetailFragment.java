package com.appliance.wanderful;


import com.appliance.wanderful.ScheduleContent.ScheduleItem;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class DetailFragment extends Fragment {
	public static final String ARG_ITEM_ID = "item_id";
	String bigImgUrl ="http://sagegatzke.com/scout/imagesbig/";
	/**
	 * The dummy content this fragment is presenting.
	 */
	private Performance mItem;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes)
	 **/
	public DetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_ITEM_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = Schedule.performances.get(Integer.parseInt((String) getArguments().get(ARG_ITEM_ID))-1);
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.detailfragment_layout, container,
				false);
		if (mItem != null) {
			new DownloadImageTask(getActivity(),((ImageView) v.findViewById(R.id.performerimagebig)), mItem.getPerformanceID()).execute(bigImgUrl+mItem.getPerformanceImage());
			((TextView) v.findViewById(R.id.time)).setText(mItem
					.getPerformanceTime());
			((TextView) v.findViewById(R.id.name)).setText(mItem
					.getPerformanceArtistName());
			((TextView) v.findViewById(R.id.genre)).setText(mItem
					.getPerformanceType());
			((TextView) v.findViewById(R.id.location)).setText(mItem
					.getPerformanceStage());
			((TextView) v.findViewById(R.id.description)).setText(mItem
					.getPerformanceDescription());
			if(mItem.isPerformanceAttending() == true) 
				{
				((ToggleButton) v.findViewById(R.id.addbutton)).setChecked(true);
					
				}
			((Button) v.findViewById(R.id.addbutton)).setOnClickListener(new OnClickListener() {

	            @Override
	            public void onClick(View v) {
	            	//Toast.makeText(context,rowItem.getPerformanceTime()+"time"+rowItem.getPerformanceArtistName()+"artist"+rowItem.getPerformanceStage()+"stage"+rowItem.getPerformanceID(), Toast.LENGTH_LONG).show();
	            	DBHelper db = new DBHelper(getActivity());
	            	/***sets the performance to attending in the base class.***/
	 			   	//BaseActivity.performances.get(Integer.parseInt(rowItem.getId())).setPerformanceAttending(true);
	            	
	            	if(mItem.isPerformanceAttending() == true)
	            	{
	                	Toast.makeText(getActivity(),mItem.getPerformanceArtistName()+" has been deleted from your bookmarks!", Toast.LENGTH_SHORT).show();
	            		db.deleteEventt(new ScheduleItem(mItem.getPerformanceID() + ""));
	                	
	            	}else{
	                	Toast.makeText(getActivity(),mItem.getPerformanceArtistName()+" has been added to your bookmarks!", Toast.LENGTH_SHORT).show();
	            long id = db.insertShow(new ScheduleItem(mItem.getPerformanceID() + ""));
	            //Log.d("MyTag", mItem.getPerformanceID() + "test");
	            db.close();
	            	}  
	            }
	        });
		}
		return v;
	}

}
