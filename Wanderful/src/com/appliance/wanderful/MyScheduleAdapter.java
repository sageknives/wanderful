
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

public class MyScheduleAdapter extends ArrayAdapter<ScheduleItem> {
   
	Context context;
	List<ScheduleItem> items;
    public MyScheduleAdapter(Context context,  int resourceId, List<ScheduleItem> items) {
        super(context,resourceId,items);
        this.items=items;
        this.context = context;
        
    }
 
    /**
     * Populate new items in the list.
     */
    @Override public View getView(int position, View view, ViewGroup parent) {
    
    	 ViewHolder viewHolder = null;// to reference the child views for later actions
    	 final ScheduleItem rowItem = (items).get(position);
    	 LayoutInflater inflater = 
    		     (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	 
        if (view == null) {
            view =  inflater.inflate(R.layout.activity_myschedule_row, null);
            
  // cache view fields into the holder
            viewHolder = new ViewHolder();
            
            viewHolder.performanceArtistName=(TextView) view.findViewById(R.id.resultname);
           viewHolder.performanceTime=(TextView) view.findViewById(R.id.resulttime);
           viewHolder.performanceStage=(TextView) view.findViewById(R.id.performancestage);
           viewHolder.deleteButton =(Button)view.findViewById(R.id.deletebutton);
          
            // associate the holder with the view for later lookup
            view.setTag(viewHolder);
            
        } else 
        	// view already exists, get the holder instance from the view
            viewHolder = (ViewHolder)view.getTag();

        viewHolder.performanceArtistName.setText(rowItem.getContent());
       viewHolder.performanceTime.setText(rowItem.getTime());
     viewHolder.performanceStage.setText(rowItem.getStage());
       // viewHolder.performanceID=rowItem.getId();
       viewHolder.deleteButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
            	Toast.makeText(context,rowItem.getContent()+rowItem.getTime()+rowItem.getStage()+rowItem.getPerformanceId(), Toast.LENGTH_SHORT).show();
            	DBHelper db = new DBHelper(context);
            	db.deleteEventt(new ScheduleItem(rowItem.getPerformanceId()));
            	Schedule.performances.get(Integer.parseInt(rowItem.getPerformanceId())).setPerformanceAttending(false);
            }
        });
  
        return view;
    } 
   
    static class ViewHolder {
    	String performanceID;
  		TextView performanceArtistName;
  		TextView performanceTime;
  		TextView performanceStage;
		  Button deleteButton;
		}
}

