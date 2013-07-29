package com.appliance.wanderful;

import java.util.List;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

@SuppressLint("ValidFragment")
public class DummyListFragment extends ListFragment {
	ListFragmentItemClickListener itemListener;
	private List<Performance> performanceList;
	private static final String STATE_ACTIVATED_POSITION = "activated_position";
	private int mActivatedPosition = ListView.INVALID_POSITION;
	private ListFragmentItemClickListener mCallbacks = sDummyCallbacks;

	public DummyListFragment(List<Performance> performanceList) {
		this.performanceList = performanceList;

	}

	public interface ListFragmentItemClickListener {
		/**
		 * This method will be invoked when an item in the ListFragment is
		 * clicked
		 */
		public void onItemSelected(String id);;
	}

	private static ListFragmentItemClickListener sDummyCallbacks = new ListFragmentItemClickListener() {
		@Override
		public void onItemSelected(String id) {

		}

	};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ListAdapter(getActivity(),
				R.layout.activity_listview_layout, performanceList));

	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Restore the previously serialized activated item position.
		if (savedInstanceState != null
				&& savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
			setActivatedPosition(savedInstanceState
					.getInt(STATE_ACTIVATED_POSITION));
		}

		View v = inflater.inflate(R.layout.activity_listview_layout, container,
				false);
		// add = (Button) v.findViewById(R.id.addbutton);

		return v;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// This makes sure that the container activity has implemented
		// the callback interface. If not, it throws an exception

		if (!(activity instanceof ListFragmentItemClickListener)) {
			throw new IllegalStateException(
					"Activity must implement fragment's callbacks.");
		}

		mCallbacks = (ListFragmentItemClickListener) activity;
	}

	/*public void onActivityCreated(Bundle savedInstanceState) {
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

	}*/

	public void setActivateOnItemClick(boolean activateOnItemClick) {
		// When setting CHOICE_MODE_SINGLE, ListView will automatically
		// give items the 'activated' state when touched.
		getListView().setChoiceMode(
				activateOnItemClick ? ListView.CHOICE_MODE_SINGLE
						: ListView.CHOICE_MODE_NONE);
	}

	private void setActivatedPosition(int position) {
		if (position == ListView.INVALID_POSITION) {
			getListView().setItemChecked(mActivatedPosition, false);
		} else {
			getListView().setItemChecked(position, true);
		}

		mActivatedPosition = position;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (mActivatedPosition != ListView.INVALID_POSITION) {
			// Serialize and persist the activated item position.
			outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
		}
	}
	@Override
	public void onDetach() {
		super.onDetach();

		// Reset the active callbacks interface to the dummy implementation.
		mCallbacks = sDummyCallbacks;
	}
	public void onListItemClick(ListView l, View v, int position, long id) {

		mCallbacks.onItemSelected(performanceList.get(position).getPerformanceID() + "");

		/**
		 * String time = ((TextView) v.findViewById(R.id.eventtime)).getText()
		 * .toString(); String name = ((TextView)
		 * v.findViewById(R.id.eventname)).getText() .toString();
		 * Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
		 * 
		 * /** add.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { Toast.makeText(getActivity(),
		 *           "balala", Toast.LENGTH_SHORT).show(); } });
		 * 
		 *           DetailFragment fragment = (DetailFragment)
		 *           getFragmentManager()
		 *           .findFragmentById(R.id.detail_fragment);
		 * 
		 *           if (fragment != null && fragment.isInLayout()) {
		 *           fragment.setText(time); fragment.setText(name);
		 * 
		 *           FragmentTransaction transaction = getChildFragmentManager()
		 *           .beginTransaction(); transaction.addToBackStack(null);
		 *           transaction.replace(R.id.detail_fragment,
		 *           fragment).commit();
		 * 
		 *           } else { Intent intent = new
		 *           Intent(getActivity().getApplicationContext(),
		 *           DetailActivity.class); intent.putExtra("time", time);
		 *           intent.putExtra("name", name); startActivity(intent);
		 * 
		 *           }
		 **/
	}

}