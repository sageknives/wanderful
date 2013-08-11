package com.appliance.wanderful;

import android.graphics.Bitmap;

public class Event {

	private int EventID;
	private int EventKey;
	private String EventName;
	private String EventStartDate;
	private String EventLength;
	private String StartDay;
	private String EventLastUpdate;
	private String EventImage;
	private String LocationName;
	private String LocationAddress;
	private String LocationCity;
	private String LocationState;
	private String LocationZip;
	private String EventMap;
	private Bitmap image = null;
	
	public Event(int EventID,int EventKey,String EventName, String EventStartDate,String EventLength,String StartDay,
			String EventLastUpdate,String EventImage,String locationName,String LocationAddress,String LocationCity,String LocationState,String LocationZip,String EventMap) {
		this.EventID = EventID;
		this.EventKey = EventKey;
		this.EventName = EventName;
		this.EventStartDate = EventStartDate;
		this.EventLength = EventLength;
		this.StartDay = StartDay;
		this.EventLastUpdate = EventLastUpdate;
		this.EventImage = EventImage;
		this.LocationName = locationName;
		this.LocationAddress = LocationAddress;
		this.LocationCity = LocationCity;
		this.LocationState = LocationState;
		this.LocationZip = LocationZip;
		this.EventMap = EventMap;
	}

	public int getEventID() {
		return EventID;
	}
	public int getEventKey() {
		return EventKey;
	}
	public String getEventName() {
		return EventName;
	}
	public String getEventStartDate() {
		return EventStartDate;
	}
	public String getEventLength() {
		return EventLength;
	}
	public String getStartDay() {
		return StartDay;
	}
	public String getEventLastUpdate() {
		return EventLastUpdate;
	}
	public String getEventLogo() {
		return EventImage;
	}
	public String getLocationName() {
		return LocationName;
	}
	public String getLocationAddress() {
		return LocationAddress;
	}
	public String getLocationCity() {
		return LocationCity;
	}
	public String getLocationState() {
		return LocationState;
	}
	public String getLocationZip() {
		return LocationZip;
	}
	public String getEventMap() {
		return EventMap;
	}

	@Override
	public String toString() {
		return "Event [EventID=" + EventID + ", EventName=" + EventName
				+ ", EventStartDate=" + EventStartDate + ", EventLength="
				+ EventLength + ", StartDay=" + StartDay + ", EventLastUpdate="
				+ EventLastUpdate + ", EventImage=" + EventImage
				+ ", LocationName=" + LocationName + ", LocationAddress="
				+ LocationAddress + ", LocationCity=" + LocationCity
				+ ", LocationState=" + LocationState + ", LocationZip="
				+ LocationZip + ", EventMap=" + EventMap + "]";
	}

	public String getAll() {
		return "Event [EventID=" + EventID + ", EventKey=" + EventKey
				+ ", EventName=" + EventName
				+ ", EventStartDate=" + EventStartDate + ", EventLength="
				+ EventLength + ", StartDay=" + StartDay + ", EventLastUpdate="
				+ EventLastUpdate + ", EventImage=" + EventImage
				+ ", LocationName=" + LocationName + ", LocationAddress="
				+ LocationAddress + ", LocationCity=" + LocationCity
				+ ", LocationState=" + LocationState + ", LocationZip="
				+ LocationZip + ", EventMap=" + EventMap + "]";
	}
	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}

}