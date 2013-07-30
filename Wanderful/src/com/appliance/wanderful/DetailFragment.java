package com.appliance.wanderful;


import com.appliance.wanderful.ScheduleContent.ScheduleItem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
			mItem = Schedule.performances.get(Integer.parseInt((String) getArguments().get(ARG_ITEM_ID)));
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
			new DownloadImageTask(getActivity(),((ImageView) v.findViewById(R.id.performerimagebig))).execute(bigImgUrl+mItem.getPerformanceImage());
			((TextView) v.findViewById(R.id.time)).setText(mItem
					.getPerformanceTime());
			((TextView) v.findViewById(R.id.name)).setText(mItem
					.getPerformanceArtistName());
			//((TextView) v.findViewById(R.id.genre)).setText(mItem
					//.getPerformanceGenre());
			((TextView) v.findViewById(R.id.location)).setText(mItem
					.getPerformanceStage());
			((TextView) v.findViewById(R.id.description)).setText(mItem
					.getPerformanceDescription());
			if(mItem.isPerformanceAttending() == true) 
				{
					((Button) v.findViewById(R.id.addbutton)).setText("Remove");
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
	            		db.deleteEventt(new ScheduleItem(mItem.getPerformanceID() + ""));
	            		Schedule.performances.get(mItem.getPerformanceID()).setPerformanceAttending(false);
	                	
	            	}else{
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
