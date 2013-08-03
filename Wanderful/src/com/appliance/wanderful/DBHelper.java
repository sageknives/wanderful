package com.appliance.wanderful;

import java.util.ArrayList;

import com.appliance.wanderful.ScheduleContent.ScheduleItem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	// Contacts Table Columns names
	public static final String KEY_ROWID = "id";
	//public static final String KEY_SHOWTIME = "time";
	//public static final String KEY_SHOWNAME = "name";
	//public static final String KEY_SHOWSTAGE = "stage";
	public static final String KEY_SHOWID = "performanceId";
	private static final String TAG = "DBAdapter";
	 // Database Name
	private static final String DATABASE_NAME = "Wanderful";
	//  table name
	private static final String DATABASE_TABLE = "events";
	private static final int DATABASE_VERSION = 1;



;

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

		
	}
	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		 String CREATE_CONTACTS_TABLE = "CREATE TABLE " + DATABASE_TABLE + "("
	                + KEY_ROWID + " INTEGER PRIMARY KEY,"+ KEY_SHOWID + " TEXT"+")";
	        db.execSQL(CREATE_CONTACTS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}


	public  long insertShow(ScheduleItem scheduleItem) {
	
		ContentValues initialValues = new ContentValues();
		
		//initialValues.put(KEY_SHOWNAME, scheduleItem.getContent());
		//initialValues.put(KEY_SHOWSTAGE, scheduleItem.getStage());
		//initialValues.put(KEY_SHOWTIME, scheduleItem.getTime());
		initialValues.put(KEY_SHOWID, scheduleItem.getPerformanceId());
		
		//initialValues.put(KEY_SHOWID, dummyItem.getId());
		 // Inserting Row
      
        long eventId=getWritableDatabase().insert(DATABASE_TABLE, null, initialValues);
        
        Schedule.performances.get(Integer.parseInt(scheduleItem.getPerformanceId())-1).setPerformanceAttending(true);
        Log.d(TAG,  scheduleItem.getPerformanceId());	
        return eventId;
        
	}
	
	
 
    // Deleting single event
    public void deleteEventt(ScheduleItem scheduleItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, KEY_SHOWID + " = ?",
                new String[] { String.valueOf(scheduleItem.performanceID) });
        db.close();
        Schedule.performances.get(Integer.parseInt(scheduleItem.getPerformanceId())-1).setPerformanceAttending(false);

    }
        
	 /* public  ArrayList<ScheduleItem> getResults() {
		  
		  ArrayList<ScheduleItem> showinfo = new ArrayList<ScheduleItem>();
		  
		 
		    String selectQuery = "SELECT  * FROM " + DATABASE_TABLE;
		    SQLiteDatabase db = this.getWritableDatabase();
		    
		    Cursor cursor = db.rawQuery(selectQuery, null);
		 
		    // looping through all rows and adding to list
		    if (cursor.moveToFirst()) {
		        do {
		        	ScheduleItem results = new ScheduleItem(cursor.getString(1));
		        
		            // Adding to list
		        	showinfo.add(results);
		        } while (cursor.moveToNext());
		    }
		    db.close();
		    // return  list
			Log.d("MyTag", showinfo.toString() + "test");	
			return showinfo;
	  }*/
	  public  ArrayList<ScheduleItem> getPerformances() {
		  
		  ArrayList<ScheduleItem> showinfo = new ArrayList<ScheduleItem>();
		  
		 
		    String selectQuery = "SELECT  * FROM " + DATABASE_TABLE;
		    SQLiteDatabase db = this.getWritableDatabase();
		    
		    Cursor cursor = db.rawQuery(selectQuery, null);
		 
		    // looping through all rows and adding to list
		    if (cursor.moveToFirst()) {
		        do {
		        	ScheduleItem results = new ScheduleItem(cursor.getString(1));
		        
		            // Adding to list
		        	showinfo.add(results);
		        } while (cursor.moveToNext());
		    }
		 
		    // return  list
			Log.d(TAG, showinfo.toString());	
			return showinfo;
	  }
}
