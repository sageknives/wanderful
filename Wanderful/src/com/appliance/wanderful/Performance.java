package com.appliance.wanderful;

public class Performance {

	private int eventID;
	private int performanceID;
	private String performanceArtistName;
	private String performanceTime;
	private String performanceStage;
	private String performanceDay;
	private String performanceDescription;
	private String performanceImage;
	private String performanceMedia;
	private boolean performanceAttending = false;

	public Performance(int eventID,int performanceID,String performanceArtistName, String performanceTime,String performanceStage,
			String performanceDay,String performanceDescription,String performanceImage,String performanceMedia) {
		this.eventID = eventID;
		this.performanceID = performanceID;
		this.performanceArtistName = performanceArtistName;
		this.performanceTime = performanceTime;
		this.performanceStage = performanceStage;
		this.performanceDay = performanceDay;
		this.performanceDescription = performanceDescription;
		this.performanceImage = performanceImage;
		this.performanceMedia = performanceMedia;
	}

	public int getPerformanceID() {
		return performanceID;
	}
	public String getPerformanceArtistName() {
		return performanceArtistName;
	}
	public String getPerformanceTime() {
		return performanceTime;
	}
	public String getPerformanceStage() {
		return performanceStage;
	}
	public String getPerformanceDay() {
		return performanceDay;
	}
	public String getPerformanceDescription() {
		return performanceDescription;
	}
	public String getPerformanceImage() {
		return performanceImage;
	}
	public String getPerformanceMedia() {
		return performanceMedia;
	}

	public int getEventID() {
		return eventID;
	}

	public boolean isPerformanceAttending() {
		return performanceAttending;
	}

	public void setPerformanceAttending(boolean performanceAttending) {
		this.performanceAttending = performanceAttending;
	}


}