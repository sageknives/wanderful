package com.appliance.wanderful;

import org.json.JSONException;
import org.json.JSONObject;

public interface JSONClientListener {
	public void onRemoteCallComplete(JSONObject jsonObjectFromNet) throws JSONException;
}