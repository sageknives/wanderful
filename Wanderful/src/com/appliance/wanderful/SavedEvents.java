package com.appliance.wanderful;

public class SavedEvents {
	private String eventName;
	private String eventId;
	private int numOfPerformance;
	
	public SavedEvents(int numOfPerformance, String eventName, String eventId)
	 {
		this.eventName=eventName;
		this.numOfPerformance=numOfPerformance;
		this.eventId=eventId;
	 }

	
	public int getNumOfPerformance() {
		return numOfPerformance;
	}

	public void setNumOfPerformance(int numOfPerformance) {
		this.numOfPerformance = numOfPerformance;
	}


	public String getEventName() {
		return eventName;
	}


	public void setEventName(String eventName) {
		this.eventName = eventName;
	}


	public String getEventId() {
		return eventId;
	}


	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

}
