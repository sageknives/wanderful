package com.appliance.wanderful;

import java.io.InputStream;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;

public class Map extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		
		if(currentMapImage == null)
		{
			new DownloadMapImageTask(this, (ScaleImageView) findViewById(R.id.map_image))
	        .execute(mapUrlLocation + events.get(currentEventID).getEventMap());
		}else{
			displayMap();
		}
		// gets all buttons and sets them to nav click listeners
		createNav(Map.this,this.findViewById(R.layout.activity_hash_feed));
	}

	public void displayMap()
	{
		((ImageView) findViewById(R.id.map_image)).setImageBitmap(currentMapImage);

	}
	
	public class DownloadMapImageTask extends AsyncTask<String, Void, Bitmap> 
	{
	    ScaleImageView mapImage;
	    ProgressDialog progressDialog;
	    Context currentContext;

	    public DownloadMapImageTask(Context context, ScaleImageView bmImage) {
	    	this.mapImage = bmImage;
	    	this.currentContext = context;
	    }

	    protected Bitmap doInBackground(String... urls) {
	    	String urldisplay = urls[0];
	    	Bitmap mIcon11 = null;
	    	try {
	    		InputStream in = new java.net.URL(urldisplay).openStream();
	    		mIcon11 = BitmapFactory.decodeStream(in);
	    	} catch (Exception e) {
	    		Log.e("Error", e.getMessage());
	    		e.printStackTrace();
	    	}
	    	return mIcon11;
	    }
		@Override
		public void onPreExecute() {
			progressDialog = new ProgressDialog(currentContext);
			progressDialog.setMessage("Loading..");
			progressDialog.setCancelable(false);
			progressDialog.setIndeterminate(true);
			progressDialog.show();
		}
	    protected void onPostExecute(Bitmap result) {
	    	mapImage.setImageBitmap(result);
	    	progressDialog.dismiss();
	    	currentMapImage = result;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	    return super.onCreateOptionsMenu(menu);
	}
}
