package com.appliance.wanderful;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailFragment extends Fragment {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	 public void onActivityCreated(Bundle savedInstanceState) {
	  super.onActivityCreated(savedInstanceState);

	 }
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.activity_detail_layout, container,
				false);
		return v;
	}
	

	

	public void setText(String item) {

		TextView name = (TextView) getActivity().findViewById(R.id.name);
		TextView time = (TextView) getActivity().findViewById(R.id.time);
		name.setText(item);
		time.setText(item);
	}

}
