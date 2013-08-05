package com.appliance.wanderful;

import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ListView;


public class SearchPage extends Schedule{
	EditText filterText;
	SearchAdapter adapter;
	ListView view;
	private static final String TAG = "schedule";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_page);
		createNav(SearchPage.this,
				this.findViewById(R.layout.activity_search_page));
		
		
		/***
		 view = (ListView)findViewById(R.id.listview);
		 
		 adapter = new SearchAdapter(this, R.layout.activity_list_row, FilteredPerformancesBySearch(3));
			
			view.setAdapter(adapter);
			
			view.setTextFilterEnabled(true);
			
			filterText = (EditText)findViewById(R.id.searchfiltertext);
		    filterText.addTextChangedListener(new TextWatcher(){

				@Override
				public void afterTextChanged(Editable s) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void beforeTextChanged(CharSequence s, int arg1,
						int arg2, int arg3) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onTextChanged(CharSequence s, int arg1, int arg2,
						int arg3) {
					adapter.getFilter().filter(s.toString());
					adapter.notifyDataSetChanged();
					
				}
		    	
		    });***/
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	    return super.onCreateOptionsMenu(menu);
	}
}
