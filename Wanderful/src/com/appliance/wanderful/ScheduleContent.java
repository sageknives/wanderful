package com.appliance.wanderful;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ScheduleContent extends Schedule {

	/**
	 * An array of sample (dummy) items.
	 */
	public static List<ScheduleItem> ITEMS = new ArrayList<ScheduleItem>();
	/**
	 * A map of sample (dummy) items, by ID.
	 */
	public static Map<String, ScheduleItem> ITEM_MAP = new HashMap<String, ScheduleItem>();

	static {
		for (int i = 0; i < performances.size(); i++) {
			addItem(new ScheduleItem(currentEventID-1+ "", performances.get(i).getPerformanceKey() + "", performances.get(i).getPerformanceID() + "",performances.get(i).getEventName() +""));
					
			System.out.println("in dummy: "
					+ performances.get(i).getPerformanceArtistName()
					+ performances.get(i).getPerformanceTime());
		}
		// Add 3 sample items.
		// addItem(new DummyItem("1", "Item 1"));
		// addItem(new DummyItem("2", "Item 2"));
		// addItem(new DummyItem("3", "Item 3"));
	}

	private static void addItem(ScheduleItem item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.id, item);
	}

	/**
	 * A dummy item representing a piece of content.
	 */
	public static class ScheduleItem {
		public String id;
		public String performanceKey;
		public String eventID;
		public String eventName;
		
		public String performanceID;
		public String stage;

		public String content;
		public String time;

		public ScheduleItem( String eventID,String performanceKey,String performanceID,String eventName) {
			this.eventID = eventID;
			this.performanceKey = performanceKey;
			this.performanceID = performanceID;
			this.eventName = eventName;
			//this.time = time;
			//this.stage = stage;
		}

		public String getTime() {
			System.out.print("dummy getTime: " + time);
			return time;
		}

		public String getContent() {
			return content;
		}

		public String getPerformanceKey() {
			return performanceKey;
		}
		public String getEventId() {
			return eventID;
		}
		public String getPerformanceId() {
			return performanceID;
		}
		public void setPerformanceId(String performanceID) {
			this.performanceID = performanceID;
		}

		@Override
		public String toString() {
			return getContent();
		}

		public String getStage() {
			return stage;
		}

		public void setStage(String stage) {
			this.stage = stage;
		}
		public String getEventName() {
			return eventName;
		}

		public void setEventName(String eventName) {
			this.eventName = eventName;
		}

	}
}