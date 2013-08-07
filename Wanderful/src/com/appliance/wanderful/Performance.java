package com.appliance.wanderful;

import android.graphics.Bitmap;

public class Performance {

	private int eventID;
	private String eventName;
	private int performanceID;
	private int performanceKey;
	private String performanceArtistName;
	private String performanceTime;
	private String performanceStage;
	private String performanceDay;
	private String performanceDescription;
	private String performanceType;
	private String performanceImage;
	private String performanceMedia;
	private boolean performanceAttending = false;
	private Bitmap image = null;

	public Performance(int eventID,String eventName, int performanceID,int performanceKey,String performanceArtistName, String performanceTime,String performanceStage,
			String performanceDay,String performanceDescription,String performanceType,String performanceImage,String performanceMedia) {
		this.eventID = eventID;
		this.performanceID = performanceID;
		this.setEventName(eventName);
		this.performanceKey = performanceKey;
		this.performanceArtistName = performanceArtistName;
		this.performanceTime = performanceTime;
		this.performanceStage = performanceStage;
		this.performanceDay = performanceDay;
		this.performanceDescription = performanceDescription;
		this.performanceType = performanceType;
		this.performanceImage = performanceImage;
		this.performanceMedia = performanceMedia;
	}

	public int getPerformanceID() {
		return performanceID;
	}
	public int getPerformanceKey() {
		return performanceKey;
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
	public String getPerformanceType() {
		return performanceType;
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
	public Bitmap getImage() {
		return this.image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Performance [eventID=" + eventID + ", performanceID="
				+ performanceID + ", performanceArtistName="
				+ performanceArtistName + ", performanceTime="
				+ performanceTime + ", performanceStage=" + performanceStage
				+ ", performanceDay=" + performanceDay
				+ ", performanceDescription=" + performanceDescription
				+ ", performanceImage=" + performanceImage
				+ ", performanceMedia=" + performanceMedia
				+ ", performanceAttending=" + performanceAttending + "]";
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}


}