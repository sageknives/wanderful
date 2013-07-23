package com.appliance.wanderful;

import java.util.ArrayList;
 

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MyScheduleListFragment extends ListFragment {
	

	public MyScheduleListFragment() {

	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		savedInstanceState = this.getArguments();

		View v = inflater.inflate(R.layout.activity_resultlist_layout, container,
				false);
		// add = (Button) v.findViewById(R.id.addbutton);

		return v;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		ArrayList<EventItem> resultsinfo;;
		DBHelper db = new DBHelper(getActivity());
		   
		 
		    resultsinfo=db.getResults();
		    db.close();
		    MyScheduleAdapter adapter = new MyScheduleAdapter(getActivity(),
				R.layout.activity_myschedule_row, resultsinfo);
		setListAdapter(adapter);

	

	}

	public void onListItemClick(ListView l, View v, int position, long id) {

		String time = ((TextView) v.findViewById(R.id.resulttime)).getText()
				.toString();
		String name = ((TextView) v.findViewById(R.id.resultname)).getText()
				.toString();
		Toast.makeText(getActivity(), name+time, Toast.LENGTH_SHORT).show();

		/**
		 * add.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { Toast.makeText(getActivity(),
		 *           "balala", Toast.LENGTH_SHORT).show(); } });
		 **/
		DetailFragment fragment = (DetailFragment) getFragmentManager()
				.findFragmentById(R.id.detailFragments);

		if (fragment != null && fragment.isInLayout()) {
			fragment.setText(time);
			fragment.setText(name);

			/**FragmentManager fragmentManager = getActivity().getSupportFragmentManager();  
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.result_list_fragment, fragment).commit();
**/
		} else {
			Intent intent = new Intent(getActivity().getApplicationContext(),
					DetailActivity.class);
			intent.putExtra("time", time);
			intent.putExtra("name", name);
			startActivity(intent);

		}

	}

}