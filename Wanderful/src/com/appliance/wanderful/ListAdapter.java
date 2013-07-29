package com.appliance.wanderful;

import java.util.List;

import com.appliance.wanderful.ScheduleContent.ScheduleItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ListAdapter extends ArrayAdapter<Performance> {
   
	Context context;
   public long id;
   List<Performance> iTEMS;
    public ListAdapter(Context context,  int resourceId, List<Performance> performanceList) {
        super(context,resourceId,performanceList);
        this.iTEMS=performanceList;
        this.context = context;
        
    }
    
 
    /**
     * Populate new items in the list.
     */
    @Override public View getView(int position, View view, ViewGroup parent) {
    	 
    	 ViewHolder viewHolder = null;// to reference the child views for later actions
    	 
    	// DummyItem rowItem= (DummyItem)getItem(position);
    	 final Performance rowItem= (iTEMS).get(position);
    	 
    	 LayoutInflater inflater = 
    		     (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	 
        if (view == null) {
            view =  inflater.inflate(R.layout.activity_list_row, null);
            
                    
         // cache view fields into the holder
            viewHolder = new ViewHolder();
            
            viewHolder.performanceArtistName= (TextView) view.findViewById(R.id.artistname);
            viewHolder.performanceTime=(TextView) view.findViewById(R.id.peformancetime);
            viewHolder.performanceStage=(TextView) view.findViewById(R.id.performancestage);
            
            viewHolder.addButton=(Button)view.findViewById(R.id.addbutton);
           
            // associate the holder with the view for later lookup
            view.setTag(viewHolder);
            
        } else 
        	// view already exists, get the holder instance from the view
            viewHolder = (ViewHolder)view.getTag();

        viewHolder.performanceArtistName.setText(rowItem.getPerformanceArtistName());
        viewHolder.performanceTime.setText(rowItem.getPerformanceTime());
        viewHolder.performanceStage.setText(rowItem.getPerformanceStage());
       // viewHolder.eventID.setText(rowItem.getEventID());
        viewHolder.performanceID=rowItem.getPerformanceID() + "";
        if(rowItem.isPerformanceAttending() == true) viewHolder.addButton.setText("Remove");
        viewHolder.addButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
            	Toast.makeText(context,rowItem.getPerformanceTime()+"time"+rowItem.getPerformanceArtistName()+"artist"+rowItem.getPerformanceStage()+"stage"+rowItem.getPerformanceID(), Toast.LENGTH_LONG).show();
            	DBHelper db = new DBHelper(context);
            	/***sets the performance to attending in the base class.***/
 			   	//BaseActivity.performances.get(Integer.parseInt(rowItem.getId())).setPerformanceAttending(true);
            	
            	if(rowItem.isPerformanceAttending() == true)
            	{
            		db.deleteEventt(new ScheduleItem(rowItem.getPerformanceArtistName(),rowItem.getPerformanceStage(),rowItem.getPerformanceTime(),rowItem.getPerformanceID() + ""));
            		Schedule.performances.get(rowItem.getPerformanceID()).setPerformanceAttending(false);
                	
            	}else{
            id = db.insertShow(new ScheduleItem(rowItem.getPerformanceArtistName(),rowItem.getPerformanceStage(),rowItem.getPerformanceTime(),rowItem.getPerformanceID() + ""));
            db.close();
            	}  
            }
        });
      
        return view;
    } 
   
    static class ViewHolder {
	//testing DummyItem item class
    	 String eventID;
    	 String performanceID;
  		TextView performanceArtistName;
  		TextView performanceTime;
  		TextView performanceStage;
  	
    	
    	
    	
		 Button addButton;
		}
}

