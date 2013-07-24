package com.appliance.wanderful;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;

public class Map extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		if(currentMapImage == null)
		{
			new DownloadImageTask(this, (ScaleImageView) findViewById(R.id.map_image))
	        .execute(mapUrlLocation + events.get(currentEventID).getEventMap());
		}else{
			((ScaleImageView) findViewById(R.id.map_image)).setImageBitmap(currentMapImage);
		}
		// gets all buttons and sets them to nav click listeners
		createNav(Map.this,this.findViewById(R.layout.activity_hash_feed));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}
}
