package com.appliance.wanderful;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SearchEvent extends BaseActivity {
	Button enterBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_event);

		// gets all buttons and sets them to nav click listeners
		enterBtn = (Button) findViewById(R.id.enter_btn);
		enterBtn.setOnClickListener(new enterClickListeners());
	}
	public class enterClickListeners implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			// Gets the button Id and sends to new activity

			if (enterBtn.getId() == v.getId())
				currentEventID = 1;
				startActivity(new Intent(SearchEvent.this, MainSchedule.class));
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_event, menu);
		return true;
	}

}