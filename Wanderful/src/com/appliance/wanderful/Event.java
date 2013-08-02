package com.appliance.wanderful;

public class Event {

	private int EventID;
	private String EventName;
	private String EventStartDate;
	private String EventLength;
	private String EventLastUpdate;
	private String EventLogo;
	private String LocationName;
	private String LocationAddress;
	private String LocationCity;
	private String LocationState;
	private String LocationZip;
	private String EventMap;

	public Event(int EventID,String EventName, String EventStartDate,String EventLength,
			String EventLastUpdate,String EventLogo,String locationName,String LocationAddress,String LocationCity,String LocationState,String LocationZip,String EventMap) {
		this.EventID = EventID;
		this.EventName = EventName;
		this.EventStartDate = EventStartDate;
		this.EventLength = EventLength;
		this.EventLastUpdate = EventLastUpdate;
		this.EventLogo = EventLogo;
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
	public String getEventName() {
		return EventName;
	}
	public String getEventStartDate() {
		return EventStartDate;
	}
	public String getEventLength() {
		return EventLength;
	}
	public String getEventLastUpdate() {
		return EventLastUpdate;
	}
	public String getEventLogo() {
		return EventLogo;
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

}