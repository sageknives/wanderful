package com.appliance.wanderful;

import java.util.ArrayList;

import com.appliance.wanderful.DummyContent.DummyItem;



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
	public static final String KEY_SHOWTIME = "time";
	public static final String KEY_SHOWNAME = "name";
	public static final String KEY_SHOWSTAGE = "stage";
	public static final String KEY_SHOWID = "show id";
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
	                + KEY_ROWID + " INTEGER PRIMARY KEY," + KEY_SHOWNAME + " TEXT,"+KEY_SHOWSTAGE + " TEXT"+KEY_SHOWTIME + " TEXT"+")";
	        db.execSQL(CREATE_CONTACTS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}


	public  long insertShow(DummyItem dummyItem) {
	
		ContentValues initialValues = new ContentValues();
		
		initialValues.put(KEY_SHOWNAME, dummyItem.getContent());
		initialValues.put(KEY_SHOWSTAGE, dummyItem.getStage());
		initialValues.put(KEY_SHOWTIME, "9:00");
		
		//initialValues.put(KEY_SHOWID, dummyItem.getId());
		 // Inserting Row
      
        long eventId=getWritableDatabase().insert(DATABASE_TABLE, null, initialValues);
        
        return eventId;
	}
	
	
 
    // Deleting single event
    public void deleteEventt(DummyItem dummyItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, KEY_ROWID + " = ?",
                new String[] { String.valueOf(dummyItem.getContent()) });
        db.close();
    }
        
	  public  ArrayList<DummyItem> getResults() {
		  
		  ArrayList<DummyItem> showinfo = new ArrayList<DummyItem>();
		  
		 
		    String selectQuery = "SELECT  * FROM " + DATABASE_TABLE;
		    SQLiteDatabase db = this.getWritableDatabase();
		    
		    Cursor cursor = db.rawQuery(selectQuery, null);
		 
		    // looping through all rows and adding to list
		    if (cursor.moveToFirst()) {
		        do {
		        	DummyItem results = new DummyItem(cursor.getString(1),cursor.getString(2),cursor.getString(3));
		        
		            // Adding to list
		        	showinfo.add(results);
		        } while (cursor.moveToNext());
		    }
		 
		    // return  list
			Log.d("MyTag", showinfo.toString());	
			return showinfo;
	  }
}
