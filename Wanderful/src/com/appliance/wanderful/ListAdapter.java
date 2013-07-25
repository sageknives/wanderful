package com.appliance.wanderful;

import java.util.List;

import com.appliance.wanderful.DummyContent.DummyItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ListAdapter extends ArrayAdapter<DummyItem> {
   
	Context context;
   public long id;
   List<DummyItem> iTEMS;
    public ListAdapter(Context context,  int resourceId, List<DummyItem> iTEMS) {
        super(context,resourceId,iTEMS);
        this.iTEMS=iTEMS;
        this.context = context;
        
    }
    
 
    /**
     * Populate new items in the list.
     */
    @Override public View getView(int position, View view, ViewGroup parent) {
    	 
    	 ViewHolder viewHolder = null;// to reference the child views for later actions
    	 
    	// DummyItem rowItem= (DummyItem)getItem(position);
    	 final DummyItem rowItem= (iTEMS).get(position);
    	 
    	 LayoutInflater inflater = 
    		     (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	 
        if (view == null) {
            view =  inflater.inflate(R.layout.activity_list_row, null);
            
                    
         // cache view fields into the holder
            viewHolder = new ViewHolder();
            
            viewHolder.performanceArtistName= (TextView) view.findViewById(R.id.artistname);
            viewHolder.performanceTime=(TextView) view.findViewById(R.id.peformancetime);
           // viewHolder.performanceStage=(TextView) view.findViewById(R.id.performancestage);
            
            viewHolder.addButton=(Button)view.findViewById(R.id.addbutton);
           
            // associate the holder with the view for later lookup
            view.setTag(viewHolder);
            
        } else 
        	// view already exists, get the holder instance from the view
            viewHolder = (ViewHolder)view.getTag();

        viewHolder.performanceArtistName.setText(rowItem.getContent());
        viewHolder.performanceTime.setText(rowItem.getTime());
        //viewHolder.performanceStage.setText(rowItem.getPerformanceStage());
       // viewHolder.eventID.setText(rowItem.getEventID());
        //viewHolder.performanceID.setText(rowItem.getPerformanceID());
        viewHolder.addButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
            	Toast.makeText(context,rowItem.getContent()+rowItem.getTime(), Toast.LENGTH_SHORT).show();
            	//DBHelper db = new DBHelper(context);
 			   
            	
            	
            	//id = db.insertShow(new EventItem(rowItem.getItemName(),rowItem.getItemTime()));
                
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

