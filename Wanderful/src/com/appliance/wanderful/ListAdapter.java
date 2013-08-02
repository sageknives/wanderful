package com.appliance.wanderful;

import java.util.ArrayList;
import java.util.List;

import com.appliance.wanderful.ScheduleContent.ScheduleItem;


import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class ListAdapter extends ArrayAdapter<Performance> implements
Filterable {
   String smallImgUrl ="http://sagegatzke.com/scout/imagesbig/";
	Context context;
   public long id;
	private scheduleFilter filter;
   List<Performance> iTEMS;
   private List<Performance> newiTEMS;
    public ListAdapter(Context context,  int resourceId, List<Performance> iTEMS)  {
        super(context,resourceId,iTEMS);
        
        this.context = context;
        this.iTEMS = new ArrayList<Performance>();
    	this.iTEMS.addAll(iTEMS);
    	this.newiTEMS = new ArrayList<Performance>();
    	this.newiTEMS.addAll(iTEMS);
        
    }
    @Override
	public Filter getFilter() {
		if (filter == null) {
			filter = new scheduleFilter();
		}
		return filter;
    }
    @Override
    public int getCount() {
        return iTEMS.size();
    }
    
    @Override
    public long getItemId(int position) {
        return 0;
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
            viewHolder.performanceImage= (ImageView) view.findViewById(R.id.performerimagesm);
            viewHolder.performanceArtistName= (TextView) view.findViewById(R.id.artistname);
            viewHolder.addButton=(ToggleButton)view.findViewById(R.id.addbutton);
            viewHolder.performanceTime=(TextView) view.findViewById(R.id.peformancetime);
            viewHolder.performanceStage=(TextView) view.findViewById(R.id.performancestage);
            
            
           
            // associate the holder with the view for later lookup
            view.setTag(viewHolder);
            
        } else 
        	// view already exists, get the holder instance from the view
            viewHolder = (ViewHolder)view.getTag();

        viewHolder.performanceArtistName.setText(rowItem.getPerformanceArtistName());
        
        //if(rowItem.isPerformanceAttending() == true)  	viewHolder.addButton.setText("Remove");
      
       
        viewHolder.addButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
            	DBHelper db = new DBHelper(context);
            	/***sets the performance to attending in the base class.***/
 			   	//BaseActivity.performances.get(Integer.parseInt(rowItem.getId())).setPerformanceAttending(true);
            	
            	if(rowItem.isPerformanceAttending() == true)
            	{
            		
                	Toast.makeText(context,rowItem.getPerformanceArtistName()+" has been deleted from your bookmarks!", Toast.LENGTH_LONG).show();
            		db.deleteEventt(new ScheduleItem(rowItem.getPerformanceID() + ""));
            		Schedule.performances.get(rowItem.getPerformanceID()).setPerformanceAttending(false);
                	
            	}else{
                	Toast.makeText(context,rowItem.getPerformanceArtistName()+" has been added to your bookmarks!", Toast.LENGTH_LONG).show();
            id = db.insertShow(new ScheduleItem(rowItem.getPerformanceID() + ""));
            Log.d("MyTag", rowItem.getPerformanceID() + "test");
            db.close();
            	}  
            }
        });
        viewHolder.performanceTime.setText(rowItem.getPerformanceTime());
        viewHolder.performanceStage.setText(rowItem.getPerformanceStage());
       // viewHolder.eventID.setText(rowItem.getEventID());
        viewHolder.performanceID=rowItem.getPerformanceID() + "";
        new DownloadImageTask(this.getContext(), viewHolder.performanceImage).execute(smallImgUrl+rowItem.getPerformanceImage());
      
        return view;
    } 
    
    static class ViewHolder {
    	//testing DummyItem item class
        	 String eventID;
        	 String performanceID;
      		TextView performanceArtistName;
      		TextView performanceTime;
      		TextView performanceStage;
      		ImageView performanceImage;
        	 ToggleButton addButton;
    		}
	private class scheduleFilter extends Filter {

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
		      //List<Performance> FilteredArrList = new ArrayList<Performance>();

		  
			
			if(constraint != null && constraint.toString().length() > 0)
		    {
		    ArrayList<Performance> filteredItems = new ArrayList<Performance>();
		 
		    for(int i = 0, l = newiTEMS.size(); i < l; i++)
		    {
		    	Performance peformance = newiTEMS.get(i);
		     if(peformance.toString().toLowerCase().contains(constraint))
		      filteredItems.add(peformance);
		    }
		    results.count = filteredItems.size();
		    results.values = filteredItems;
		    }
		    else
		    {
		     synchronized(this)
		     {
		    	 results.values = newiTEMS;
		    	 results.count = newiTEMS.size();
		     }
		    }
		    return results;
		   
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {
			iTEMS = (List<Performance>)results.values;// has the filtered values
			 if (results.count > 0) {
	                notifyDataSetChanged();
	            } else {
	                notifyDataSetInvalidated();
	            }

			
		}
	}
}

