package com.appliance.wanderful;

import java.util.ArrayList;
import java.util.List;



import android.content.Context;
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


public class SearchAdapter extends ArrayAdapter<Event> implements
		Filterable {

	Context context;
	public long id;
	private scheduleFilter filter;
	private List<Event> newiTEMS;
	public List<Event> iTEMS;
	String logoImgUrl ="http://sagegatzke.com/scout/imagesbig/";
	public SearchAdapter(Context context, int resourceId, List<Event> iTEMS) {
		super(context, resourceId, iTEMS);
		
		this.context = context;
		
		
	this.iTEMS = new ArrayList<Event>();
	this.iTEMS.addAll(iTEMS);
	this.newiTEMS = new ArrayList<Event>();
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
	static class ViewHolder {
		
		int eventID;
		TextView eventName;
		TextView eventDate;
		TextView eventAddress;
		ImageView eventLogo;

		
	}

	/**
	 * Populate new items in the list.
	 */
	@Override
	public View getView(int position, View view, ViewGroup parent) {

		ViewHolder viewHolder = null;// to reference the child views for later
										// actions

		// DummyItem rowItem= (DummyItem)getItem(position);
		final Event rowItem = (iTEMS).get(position);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if (view == null) {
			view = inflater.inflate(R.layout.activity_searchpage_list_row, null);

			// cache view fields into the holder
			viewHolder = new ViewHolder();

			viewHolder.eventName = (TextView) view
					.findViewById(R.id.eventName);
			viewHolder.eventDate = (TextView) view
					.findViewById(R.id.eventDate);
			viewHolder.eventAddress = (TextView) view
					.findViewById(R.id.eventAddress);
			viewHolder.eventLogo=(ImageView)view.findViewById(R.id.eventLogo);
		

			// associate the holder with the view for later lookup
			view.setTag(viewHolder);

		} else
			// view already exists, get the holder instance from the view
			viewHolder = (ViewHolder) view.getTag();

		viewHolder.eventName.setText(rowItem.getEventName());
		viewHolder.eventDate.setText(rowItem.getEventStartDate());
		 viewHolder.eventAddress.setText(rowItem.getLocationAddress());
		 viewHolder.eventID=rowItem.getEventID();
		 
		 if(rowItem.getEventLogo() == null){
	        	new DownloadImageTask(context,viewHolder.eventLogo,rowItem.getEventID()).execute(logoImgUrl+rowItem.getEventLogo());
	        }else{
	        	viewHolder.eventLogo.setImageBitmap(rowItem.getImage());
	        }
		
		return view;
	}

	private class scheduleFilter extends Filter {

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
		      //List<Performance> FilteredArrList = new ArrayList<Performance>();

		  
			
			if(constraint != null && constraint.toString().length() > 0)
		    {
		    ArrayList<Event> filteredItems = new ArrayList<Event>();
		 
		    for(int i = 0, l = newiTEMS.size(); i < l; i++)
		    {
		    	Event events = newiTEMS.get(i);
		     if(events.toString().toLowerCase().contains(constraint))
		      filteredItems.add(events);
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
			iTEMS = (List<Event>)results.values;// has the filtered values
			 if (results.count > 0) {
	                notifyDataSetChanged();
	            } else {
	                notifyDataSetInvalidated();
	            }

			
		}
	}
	

}
