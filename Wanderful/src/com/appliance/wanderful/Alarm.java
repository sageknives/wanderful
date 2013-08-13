package com.appliance.wanderful;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Alarm extends BroadcastReceiver  {

	@Override
	public void onReceive(Context context, Intent intent) {

		 Bundle bundle = intent.getExtras();
	        String artistName = bundle.getString("artist_name");
	        int perKey = bundle.getInt("performance_key");
	      //I am hoping to use the eventId to launch mainschedule
	        int eventId = bundle.getInt("event_id");
	     // Request the notification manager
	        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
	        //icon for the notification
	        int icon = R.drawable.bookmark;
	       
	        Toast.makeText(context, eventId + "", Toast.LENGTH_LONG)
			.show();	
	        CharSequence text = artistName + " "+"is playing now!";
	        
	        // Create a new intent which will be fired if you click on the notification
	        
	        Intent newIntent = new Intent(context,
                    SearchPage.class);
	        newIntent.putExtra("SearchPage",eventId);
	        // The PendingIntent to launch our activity if the user selects this notification
	        PendingIntent pIntent = PendingIntent.getActivity(context,0, newIntent, 0);
	        // Create the notification using builder
	        Builder builder = new Notification.Builder(context);
	        builder.setContentTitle("Wanderful")
	          
	          .setSmallIcon(R.drawable.ic_launcher)
	          .setAutoCancel(true)
	          .addAction(icon, "Show Schedule", pIntent);
	        Notification notification = new Notification.BigTextStyle(builder)
	          .bigText(text).build();
	        
	       
	     // Fire the notification
	        notificationManager.notify(perKey, notification);
		
	}

}
