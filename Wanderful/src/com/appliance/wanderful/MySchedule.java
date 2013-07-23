package com.appliance.wanderful;



import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;

public class MySchedule extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_schedule);

		

		if (findViewById(R.id.result_list_fragment) != null) {
			MyScheduleListFragment list = new MyScheduleListFragment();
			getSupportFragmentManager().beginTransaction()
					.add(R.id.result_list_fragment, list).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_schedule, menu);
		return true;
	}

}
