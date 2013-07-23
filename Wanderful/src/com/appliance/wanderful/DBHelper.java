package com.appliance.wanderful;

import java.util.ArrayList;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	// Contacts Table Columns names
	public static final String KEY_ROWID = "id";
	public static final String KEY_SHOWTIME = "name";
	public static final String KEY_SHOWNAME = "email";
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
	                + KEY_ROWID + " INTEGER PRIMARY KEY," + KEY_SHOWTIME + " TEXT,"
	                + KEY_SHOWNAME + " TEXT" + ")";
	        db.execSQL(CREATE_CONTACTS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}


	public  long insertShow(EventItem eventItem) {
	
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_SHOWNAME, eventItem.getItemName());
		initialValues.put(KEY_SHOWTIME, eventItem.getItemTime());
		 // Inserting Row
      
        long eventId=getWritableDatabase().insert(DATABASE_TABLE, null, initialValues);
        
        return eventId;
	}
	
	
 
    // Deleting single event
    public void deleteEventt(EventItem eventitemt) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, KEY_ROWID + " = ?",
                new String[] { String.valueOf(eventitemt.getId()) });
        db.close();
    }
        
	  public  ArrayList<EventItem> getResults() {
		  
		  ArrayList<EventItem> showinfo = new ArrayList<EventItem>();
		  
		 
		    String selectQuery = "SELECT  * FROM " + DATABASE_TABLE;
		    SQLiteDatabase db = this.getWritableDatabase();
		    
		    Cursor cursor = db.rawQuery(selectQuery, null);
		 
		    // looping through all rows and adding to list
		    if (cursor.moveToFirst()) {
		        do {
		        	EventItem results = new EventItem(cursor.getString(1),cursor.getString(2));
		        
		            // Adding to list
		        	showinfo.add(results);
		        } while (cursor.moveToNext());
		    }
		 
		    // return  list
			Log.d("MyTag", showinfo.toString());	
			return showinfo;
	  }
}
