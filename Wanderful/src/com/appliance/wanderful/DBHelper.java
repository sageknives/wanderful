package com.appliance.wanderful;

import java.util.ArrayList;

import com.appliance.wanderful.ScheduleContent.ScheduleItem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
	// Contacts Table Columns names
	public static final String KEY_ROWID = "id";
	//public static final String KEY_SHOWTIME = "time";
	public static final String KEY_EVENTNAME = "eventname";
	//public static final String KEY_SHOWSTAGE = "stage";
	public static final String KEY_EVENTID = "eventId";
	public static final String KEY_SHOWID = "performanceId";
	private static final String TAG = "DBAdapter";
	private static final String DATABASE = "DBinfo";

	 // Database Name
	private static final String DATABASE_NAME = "Wanderful";
	//  table name
	private static final String DATABASE_TABLE = "events";
	private static final String DATABASE_TABLE2 = "curevent";
	public static final String KEY_EVENT_KEY = "eventkey";
	public static final String KEY_EVENT_START_DATE = "eventstartdate";
	public static final String KEY_EVENT_LENGTH = "eventlength";
	public static final String KEY_EVENT_DAY = "eventday";
	public static final String KEY_EVENT_LAST_UPDATED = "eventlastupdated";
	public static final String KEY_EVENT_IMAGE = "eventimage";
	public static final String KEY_EVENT_LOCATION_NAME = "eventlocationname";
	public static final String KEY_EVENT_LOCATION_ADDRESS = "eventlocationaddress";
	public static final String KEY_EVENT_LOCATION_CITY = "eventlocationcity";
	public static final String KEY_EVENT_LOCATION_STATE = "eventlocationstate";
	public static final String KEY_EVENT_LOCATION_ZIP = "eventlocationzip";
	public static final String KEY_EVENT_MAP = "eventmap";

	private static final int DATABASE_VERSION = 1;



;

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

		
	}
	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		 String CREATE_CONTACTS_TABLE = "CREATE TABLE " + DATABASE_TABLE + "("
	                + KEY_ROWID + " INTEGER PRIMARY KEY,"+ KEY_SHOWID + " TEXT,"+ KEY_EVENTID + " TEXT,"+KEY_EVENTNAME+" TEXT"+")";
	     String CREATE_CONTACTS_TABLE2 =  "CREATE TABLE " + DATABASE_TABLE2 + "("
			     + KEY_ROWID + " INTEGER PRIMARY KEY,"+ KEY_EVENTID + " TEXT,"+ KEY_EVENT_KEY + " TEXT,"+ KEY_EVENTNAME + " TEXT," + KEY_EVENT_START_DATE+" TEXT," 
				 + KEY_EVENT_LENGTH + " TEXT,"+ KEY_EVENT_DAY + " TEXT," + KEY_EVENT_LAST_UPDATED+" TEXT," + KEY_EVENT_IMAGE + " TEXT,"
			     + KEY_EVENT_LOCATION_NAME + " TEXT," + KEY_EVENT_LOCATION_ADDRESS+" TEXT," + KEY_EVENT_LOCATION_CITY + " TEXT," 
				 + KEY_EVENT_LOCATION_STATE +" TEXT," + KEY_EVENT_LOCATION_ZIP +" TEXT," + KEY_EVENT_MAP +" TEXT" + ")";
	        db.execSQL(CREATE_CONTACTS_TABLE);
	        db.execSQL(CREATE_CONTACTS_TABLE2);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public  long insertCurrentEvent(Event currentEvent) {
		
		ContentValues initialValues = new ContentValues();
		
		initialValues.put(KEY_EVENTID, currentEvent.getEventID());
		initialValues.put(KEY_EVENT_KEY, currentEvent.getEventKey());
		initialValues.put(KEY_EVENTNAME, currentEvent.getEventName());
		initialValues.put(KEY_EVENT_START_DATE, currentEvent.getEventStartDate());
		initialValues.put(KEY_EVENT_LENGTH, currentEvent.getEventLength());
		initialValues.put(KEY_EVENT_DAY, currentEvent.getStartDay());
		initialValues.put(KEY_EVENT_LAST_UPDATED, currentEvent.getEventLastUpdate());
		initialValues.put(KEY_EVENT_IMAGE, currentEvent.getEventLogo());
		initialValues.put(KEY_EVENT_LOCATION_NAME, currentEvent.getLocationName());
		initialValues.put(KEY_EVENT_LOCATION_ADDRESS, currentEvent.getLocationAddress());
		initialValues.put(KEY_EVENT_LOCATION_CITY, currentEvent.getLocationCity());
		initialValues.put(KEY_EVENT_LOCATION_STATE, currentEvent.getLocationState());
		initialValues.put(KEY_EVENT_LOCATION_ZIP, currentEvent.getLocationZip());
		initialValues.put(KEY_EVENT_MAP, currentEvent.getEventMap());

		try{
			long eventId1=getWritableDatabase().delete(DATABASE_TABLE2, KEY_ROWID + " = ?",
	                new String[] { String.valueOf(1) });
		}catch(Exception e){}
		
        long eventId=getWritableDatabase().insert(DATABASE_TABLE2, null, initialValues);
		Log.d("DATABASE","INSERT DBHelper: eventkey="+currentEvent.getEventKey()+",eventName="+currentEvent.getEventName());

        return eventId;
        
	}
	
	public Event getCurrentEvent() {
		
		 String selectQuery = "SELECT * FROM " + DATABASE_TABLE2;
		    SQLiteDatabase db = this.getWritableDatabase();
		    
		    Cursor cursor = db.rawQuery(selectQuery, null);
		    Event results = null;
		    // looping through all rows and adding to list
		    if (cursor.moveToFirst()) {
		        do {
		        	results = new Event(0,Integer.parseInt(cursor.getString(2)),cursor.getString(3),cursor.getString(4),cursor.getString(5)
		        			,cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10)
		        			,cursor.getString(11),cursor.getString(12),cursor.getString(13),cursor.getString(14));
					Log.d("DATABASE",  cursor.getString(0) + " returned performance from event ");	

		        } while (cursor.moveToNext());
		    }
		    db.close();
		return results;
	}

	public  long insertShow(ScheduleItem scheduleItem) {
	
		ContentValues initialValues = new ContentValues();
		
		//initialValues.put(KEY_SHOWNAME, scheduleItem.getContent());
		//initialValues.put(KEY_SHOWSTAGE, scheduleItem.getStage());
		
		initialValues.put(KEY_SHOWID, scheduleItem.getPerformanceKey());
		initialValues.put(KEY_EVENTID, scheduleItem.getEventId());
		initialValues.put(KEY_EVENTNAME, scheduleItem.getEventName());
		//initialValues.put(KEY_SHOWID, dummyItem.getId());
		 // Inserting Row
		
        long eventId=getWritableDatabase().insert(DATABASE_TABLE, null, initialValues);
		Log.d("DATABASE","INSERT DBHelper: eventID="+scheduleItem.getEventId()+",performanceKey="+scheduleItem.getPerformanceKey()+",performanceID="+scheduleItem.getPerformanceId()+",EventName=" +scheduleItem.getEventName());

        Schedule.performances.get(Integer.parseInt(scheduleItem.getPerformanceId())-1).setPerformanceAttending(true);
        Log.d(TAG,  scheduleItem.getPerformanceId());	
        return eventId;
        
	}
	
	
 
    // Deleting single performance
    public void deleteEventt(ScheduleItem scheduleItem) {
        SQLiteDatabase db = this.getWritableDatabase();
		Log.d("DATABASE","DELETE DBHelper: eventID="+scheduleItem.getEventId()+",performanceKey="+scheduleItem.getPerformanceKey()+",performanceID="+scheduleItem.getPerformanceId()+",EventName=" +scheduleItem.getEventName());

        db.delete(DATABASE_TABLE, KEY_SHOWID + " = ?",
                new String[] { String.valueOf(scheduleItem.performanceKey) });
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
	  public  ArrayList<ScheduleItem> getPerformances(int thisEvent) {
		  
		  ArrayList<ScheduleItem> showinfo = new ArrayList<ScheduleItem>();
			Log.d("MyTag", "attempting to get saved bookmarks for event " + thisEvent);	

		 
		    String selectQuery = "SELECT * FROM " + DATABASE_TABLE + " where " + KEY_EVENTID + "=" + thisEvent;
		    SQLiteDatabase db = this.getWritableDatabase();
		    
		    Cursor cursor = db.rawQuery(selectQuery, null);
		 
		    // looping through all rows and adding to list
		    if (cursor.moveToFirst()) {
		        do {
		        	ScheduleItem results = new ScheduleItem(null,cursor.getString(1),null,null);
					Log.d("DATABASE",  cursor.getString(0) + " returned performance from event ");	

		            // Adding to list
		        	showinfo.add(results);
		        } while (cursor.moveToNext());
		    }
		 
		    // return  list
			Log.d(TAG, showinfo.toString());	
			return showinfo;
	  }
	  
	  public ArrayList<SavedEvents> getSavedEvents()
	  {
		  ArrayList<SavedEvents> savedInfo = new ArrayList<SavedEvents>();
		  
		  String selectQuery = "SELECT COUNT("+KEY_ROWID +"),("+KEY_EVENTNAME+"),("+KEY_EVENTID+")"+ " FROM " + DATABASE_TABLE + " group by "+KEY_EVENTID;
		  
		  SQLiteDatabase db = this.getWritableDatabase();
		    
		    Cursor cursor = db.rawQuery(selectQuery, null);
		 
		    // looping through all rows and adding to list
		    if (cursor.moveToFirst()) {
		        do {
		        	SavedEvents results = new SavedEvents(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
		        	
					Log.d("MyTag",cursor.getInt(0) + " returned events from event ");	

		            // Adding to list
					savedInfo.add(results);
		        } while (cursor.moveToNext());
		    }
		 db.close();
		    // return  list
			Log.d(TAG, savedInfo.toString());	
		return savedInfo;
		  
	  }
	  //delete all performances per Event
	  public void deleteEvent(String eventId) {
		  SQLiteDatabase db = this.getWritableDatabase();
	        db.delete(DATABASE_TABLE, KEY_EVENTID + " = ?",
	                new String[] { eventId});
	        db.close();
	        Schedule.updateSchedule(Integer.parseInt(eventId));

	    }
}
