package com.appliance.wanderful;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

//extends schedule for now but won't when we get actual content
public class HashFeed extends BaseActivity {
	private String HashSearch = events.get(currentEventID -1).getEventName().replaceAll("\\s","%20");
	String URL = "https://api.twitter.com/1.1/search/tweets.json?q=" + HashSearch;
	final String APIKEY = "Gq79Zt8TMmlloVxW29bYw";
	final String APISECRET = "HBZ1cjL2RapaHaYrgLE1FGxpWLI9pYIPmT0vfFmo";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hash_feed);
		checkCacheRedirect(HashFeed.this);

		// gets all buttons and sets them to nav click listeners
		createNav(HashFeed.this, this.findViewById(R.layout.activity_hash_feed));
		new GetBearerTokenTask().execute();

		}

		
		
		protected class GetBearerTokenTask extends AsyncTask<Void, Void, String> {
			ProgressDialog progressDialog;

	        @Override
			protected String doInBackground(Void... params) {
				
				try {
					DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
					HttpPost httppost = new HttpPost("https://api.twitter.com/oauth2/token");
					
					String apiString = APIKEY + ":" + APISECRET;
					String authorization = "Basic " + Base64.encodeToString(apiString.getBytes(), Base64.NO_WRAP);
			
					httppost.setHeader("Authorization", authorization);
					httppost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
					httppost.setEntity(new StringEntity("grant_type=client_credentials"));
			
					InputStream inputStream = null;
					HttpResponse response = httpclient.execute(httppost);
					HttpEntity entity = response.getEntity();
			
					inputStream = entity.getContent();
					BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
					StringBuilder sb = new StringBuilder();
			
					String line = null;
					while ((line = reader.readLine()) != null)
					{
					    sb.append(line + "\n");
					}
					
					return sb.toString();
				}catch (Exception e){
					Log.e("GetBearerTokenTask", "Error:" + e.getMessage());
					return null;
				}
			}
	        @Override
	    	public void onPreExecute() {
	    		progressDialog = new ProgressDialog(HashFeed.this);
	    		progressDialog.setMessage("Connecting to twitter..");
	    		progressDialog.setCancelable(false);
	    		progressDialog.setIndeterminate(true);
	    		progressDialog.show();
	    	}
			@Override
			protected void onPostExecute(String jsonText){
				try {
					JSONObject root = new JSONObject(jsonText);
					String bearer_token = root.getString("access_token");			
					new GetFeedTask().execute(bearer_token, URL);
					//TextView txt = (TextView)findViewById(R.id.txt_bearer_token);
					//txt.setText(bearer_token);
					
					
					
				}catch (Exception e){
					Log.e("GetBearerTokenTask", "Error:" + e.getMessage());
				}
				progressDialog.dismiss();
			}
	    }



		
		
		protected class GetFeedTask extends AsyncTask<String, Void, String> {
			ProgressDialog progressDialog;

		    @Override
			protected String doInBackground(String... params) {
				
				try {
					DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
					HttpGet httpget = new HttpGet(params[1]);
					httpget.setHeader("Authorization", "Bearer " + params[0]);
					httpget.setHeader("Content-type", "application/json");

					InputStream inputStream = null;
					HttpResponse response = httpclient.execute(httpget);
					HttpEntity entity = response.getEntity();

					inputStream = entity.getContent();
					BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
					StringBuilder sb = new StringBuilder();

					String line = null;
					while ((line = reader.readLine()) != null)
					{
						sb.append(line + "\n");
					}
					return sb.toString();
				}catch (Exception e){
					Log.e("GetFeedTask", "Error:" + e.getMessage());
					return null;
				}
			}
		    @Override
	    	public void onPreExecute() {
	    		progressDialog = new ProgressDialog(HashFeed.this);
	    		progressDialog.setMessage("Getting Tweets..");
	    		progressDialog.setCancelable(false);
	    		progressDialog.setIndeterminate(true);
	    		progressDialog.show();
	    	}
			@Override
			protected void onPostExecute(String jsonText){
				StringBuilder tweetResultBuilder = new StringBuilder();
				JSONObject mJsonObject = null;
				Log.d("twitter","in twitter");
				
				try {
					//txt.setText(jsonText);
					Log.d("twitter","in twitter set text");
					mJsonObject = new JSONObject(jsonText);
					Log.d("twitter","in twitter set json text to object");
					JSONArray tweetArray = mJsonObject.getJSONArray("statuses");
					Log.d("twitter","in twitter set json statuses, size=" + tweetArray.length());

					for (int t=0; t<tweetArray.length(); t++) {
						Log.d("twitter","in twitter loop :" + t);
						JSONObject tweetObject = tweetArray.getJSONObject(t);	
						LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
							    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
						layoutParams.setMargins(0, 10, 0, 0);
						LinearLayout layout = new LinearLayout(HashFeed.this);
						layout.setOrientation(LinearLayout.HORIZONTAL);
						layout.setLayoutParams(layoutParams);
						layout.setBackgroundColor(Color.WHITE);
						layout.setPadding(5, 0, 0, 0);
						ImageView imageView = new ImageView(HashFeed.this);
						imageView.setLayoutParams(new LinearLayout.LayoutParams(
							    100, 100));
						
						imageView.setPadding(5, 5, 5, 5);
						LinearLayout innerLayout = new LinearLayout(HashFeed.this);
						innerLayout.setOrientation(LinearLayout.VERTICAL);
						innerLayout.setPadding(5, 5, 5, 5);
						innerLayout.setLayoutParams(new LinearLayout.LayoutParams(
							    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
						TextView userName = new TextView(HashFeed.this);
						userName.setText(tweetObject.getJSONObject("user").getString("screen_name"));
						TextView userTweet = new TextView(HashFeed.this);
						userTweet.setText(tweetObject.get("text")+"");
						
						LinearLayout layoutshadow = new LinearLayout(HashFeed.this);
						layoutshadow.setOrientation(LinearLayout.VERTICAL);
						layoutshadow.setLayoutParams(new LinearLayout.LayoutParams(
							    LinearLayout.LayoutParams.FILL_PARENT, 10));
						layoutshadow.setBackgroundColor(Color.GRAY);
						innerLayout.addView(userName);
						innerLayout.addView(userTweet);
						layout.addView(imageView);
						layout.addView(innerLayout);
						
						//tweetResultBuilder.append(tweetObject.getJSONObject("user").getString("profile_image_url")+": ");
						new DownloadImageTask(HashFeed.this, imageView,2,2).execute(tweetObject.getJSONObject("user").getString("profile_image_url"));
						//tweetResultBuilder.append(tweetObject.getJSONObject("user").getString("screen_name")+": ");
						//tweetResultBuilder.append(tweetObject.get("text")+"\n\n");
						((LinearLayout)findViewById(R.id.hash_feed)).addView(layout);
						((LinearLayout)findViewById(R.id.hash_feed)).addView(layoutshadow);

					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(tweetResultBuilder.length()>0) Log.d("list","no list");
					//txt.setText(tweetResultBuilder.toString());
				//else txt.setText("Sorry - no tweets found for your search!");
				progressDialog.dismiss();
			}
		}
		// changes the text view 1 to the first band on the first day
		/*TextView header = (TextView) findViewById(R.id.header);
		header.setText(events.get(0).getEventName());
		TextView txtView1 = (TextView) findViewById(R.id.text1);
		txtView1.setText(performances.get(0).getPerformanceArtistName());
		TextView txtView2 = (TextView) findViewById(R.id.text2);
		txtView2.setText(performances.get(0).getPerformanceTime());
		*/
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	    return super.onCreateOptionsMenu(menu);
	}

}
