package com.appliance.wanderful;

import java.util.List;

import com.appliance.wanderful.ScheduleContent.ScheduleItem;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsAdapter extends ArrayAdapter<SavedEvents> {

	Context context;

	private List<SavedEvents> eventList;

	private static final String TAG = "schedule";

	public SettingsAdapter(Context context, int resourceId,
			List<SavedEvents> eventList) {
		super(context, resourceId, eventList);

		this.context = context;

		this.eventList = eventList;

	}

	static class ViewHolder {
		TextView eventName;
		TextView numOfPerformances;

		Button deleteButton;

	}

	public View getView(int position, View view, ViewGroup parent) {

		ViewHolder viewHolder = null;// to reference the child views for later
										// actions

		// DummyItem rowItem= (DummyItem)getItem(position);
		final SavedEvents rowItem = (eventList).get(position);

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if (view == null) {
			view = inflater.inflate(R.layout.activity_setting_list_row, null);

			// cache view fields into the holder
			viewHolder = new ViewHolder();

			viewHolder.eventName = (TextView) view
					.findViewById(R.id.savedEventName);
			viewHolder.numOfPerformances = (TextView) view
					.findViewById(R.id.numOfPerformances);
			viewHolder.deleteButton = (Button) view
					.findViewById(R.id.deletebutton);

			// associate the holder with the view for later lookup
			view.setTag(viewHolder);

		} else
			// view already exists, get the holder instance from the view
			viewHolder = (ViewHolder) view.getTag();

		viewHolder.eventName.setText(rowItem.getEventName());
		viewHolder.numOfPerformances.setText(Integer.toString(rowItem
				.getNumOfPerformance()));
		viewHolder.deleteButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						context);

				// set title

				alertDialogBuilder.setTitle("Delete saved festivals");

				// set dialog message
				alertDialogBuilder
						.setMessage(
								"All your saved performances will be deleted. Continue?")
						.setCancelable(false)
						.setPositiveButton("Yes",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										// if this button is clicked, close
										// current activity
										DBHelper db = new DBHelper(context);
										db.deleteEvent(rowItem.getEventId());
									}
								})
						.setNegativeButton("No",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										// if this button is clicked, just close
										// the dialog box and do nothing
										dialog.cancel();
									}
								});

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();

			}

		});

		return view;
	}

}
