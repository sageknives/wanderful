package com.appliance.wanderful;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ListAdapter extends ArrayAdapter<EventItem> {
   
	Context context;
   public long id;
    public ListAdapter(Context context,  int resourceId, List<EventItem> items) {
        super(context,resourceId,items);
        
        this.context = context;
        
    }
    
 
    /**
     * Populate new items in the list.
     */
    @Override public View getView(int position, View view, ViewGroup parent) {
    	 
    	 ViewHolder viewHolder = null;// to reference the child views for later actions
    	 final EventItem rowItem = getItem(position);
    	 LayoutInflater inflater = 
    		     (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    	 
        if (view == null) {
            view =  inflater.inflate(R.layout.activity_list_row, null);
            
            TextView eventname= (TextView) view.findViewById(R.id.eventname);
            TextView eventtime=(TextView) view.findViewById(R.id.eventtime);
            Button addButton =(Button)view.findViewById(R.id.addbutton);
            
         // cache view fields into the holder
            viewHolder = new ViewHolder();
            viewHolder.name=eventname;
            viewHolder.time=eventtime;
        viewHolder.addButton=addButton;
            
            // associate the holder with the view for later lookup
            view.setTag(viewHolder);
            
        } else 
        	// view already exists, get the holder instance from the view
            viewHolder = (ViewHolder)view.getTag();

        viewHolder.name.setText(rowItem.getItemName());
        viewHolder.time.setText(rowItem.getItemTime());
        viewHolder.addButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
            	Toast.makeText(context,rowItem.getItemName()+rowItem.getItemTime(), Toast.LENGTH_SHORT).show();
            	DBHelper db = new DBHelper(context);
 			   
            	
            	
            	id = db.insertShow(new EventItem(rowItem.getItemName(),rowItem.getItemTime()));
                
            }
        });
      
        return view;
    } 
   
    static class ViewHolder {
		  TextView name;
		  TextView time;
		  Button addButton;
		}
}

