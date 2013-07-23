package com.appliance.wanderful;

public class Event {
	
	private int EventID;
	private String EventName;
	private String EventStartDate;
	private String EventLength;
	private String EventLastUpdate;
	private String EventLogo;
	private String EventLocation;
	private String EventMap;

	public Event(int EventID,String EventName, String EventStartDate,String EventLength,
			String EventLastUpdate,String EventLogo,String EventLocation,String EventMap) {
		this.EventID = EventID;
		this.EventName = EventName;
		this.EventStartDate = EventStartDate;
		this.EventLength = EventLength;
		this.EventLastUpdate = EventLastUpdate;
		this.EventLogo = EventLogo;
		this.EventLocation = EventLocation;
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
	public String getEventLocation() {
		return EventLocation;
	}
	public String getEventMap() {
		return EventMap;
	}

}