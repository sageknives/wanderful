package com.appliance.wanderful;

import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> 
{
    ImageView myImage;
    //ProgressDialog progressDialog;
    Context currentContext;
    int performanceID;
    
    public DownloadImageTask(Context context, ImageView bmImage, int performanceID) {
    	this.myImage = bmImage;
    	this.currentContext = context;
    	this.performanceID = performanceID;
    }

    protected Bitmap doInBackground(String... urls) {
    	String urldisplay = urls[0];
    	Bitmap mIcon11 = null;
    	try {
    		InputStream in = new java.net.URL(urldisplay).openStream();
    		mIcon11 = BitmapFactory.decodeStream(in);
    		in.close();
    	} catch (Exception e) {
    		Log.e("Error", e.getMessage());
    		e.printStackTrace();
    	}
    	return mIcon11;
    }
	@Override
	public void onPreExecute() {
		/*progressDialog = new ProgressDialog(currentContext);
		progressDialog.setMessage("Loading..");
		progressDialog.setCancelable(false);
		progressDialog.setIndeterminate(true);
		progressDialog.show();*/
	}
    protected void onPostExecute(Bitmap result) {
    	Schedule.performances.get(performanceID -1).setImage(result);

    	myImage.setImageBitmap(result);
    	//progressDialog.dismiss();
	}
}
