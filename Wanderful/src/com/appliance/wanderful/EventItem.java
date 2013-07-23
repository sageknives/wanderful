package com.appliance.wanderful;

public class EventItem {
	
	private String itemName;
	private String itemTime;
	long id;
	


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public EventItem(String itemName, String itemTime) {
		this.setItemName(itemName);
		this.setItemTime(itemTime);

	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	 
	
	public String getItemTime() {
		return itemTime;
	}

	public void setItemTime(String itemTime) {
		this.itemTime = itemTime;
	}

}
