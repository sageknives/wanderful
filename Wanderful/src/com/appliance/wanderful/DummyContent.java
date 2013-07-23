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
public class DummyContent extends BaseActivity {

	/**
	 * An array of sample (dummy) items.
	 */
	public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();
	/**
	 * A map of sample (dummy) items, by ID.
	 */
	public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

	static {
		for (int i = 0; i < performances.size(); i++) {
			addItem(new DummyItem(i + "", performances.get(i)
					.getPerformanceArtistName(), performances.get(i)
					.getPerformanceTime()));
			System.out.println("in dummy: "
					+ performances.get(i).getPerformanceArtistName()
					+ performances.get(i).getPerformanceTime());
		}
		// Add 3 sample items.
		// addItem(new DummyItem("1", "Item 1"));
		// addItem(new DummyItem("2", "Item 2"));
		// addItem(new DummyItem("3", "Item 3"));
	}

	private static void addItem(DummyItem item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.id, item);
	}

	/**
	 * A dummy item representing a piece of content.
	 */
	public static class DummyItem {
		public String id;
		public String content;
		public String time;

		public DummyItem(String id, String content, String time) {
			this.id = id;
			this.content = content;
			this.time = time;
		}

		public String getTime() {
			System.out.print("dummy getTime: " + time);
			return time;
		}

		public String getContent() {
			return content;
		}

		@Override
		public String toString() {
			return getContent();
		}

	}
}