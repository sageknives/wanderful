package com.appliance.wanderful;

import org.json.JSONArray;
import org.json.JSONException;

public interface JSONClientListener {
	public void onRemoteCallComplete(JSONArray jsonObjectFromNet) throws JSONException;
}