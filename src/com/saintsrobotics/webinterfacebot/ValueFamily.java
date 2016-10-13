package com.saintsrobotics.webinterfacebot;


import java.util.Iterator;

import org.java_websocket.WebSocket;
import org.json.JSONObject;

public class ValueFamily {
	private final String name;
	private final JSONObject json;
	private final WebSocket conn;
	public ValueFamily(String name, JSONObject json, WebSocket conn){
		this.name = name;
		this.json = json;
		this.conn = conn;
	}
	public void set(String key, Object value) throws NotWritableFieldException{
		JSONObject val = json.getJSONObject(name).getJSONObject(key);
		if(val.optBoolean("server-writeable")){
			JSONObject message = new JSONObject();
			JSONObject delta = new JSONObject();
			delta.put(key, value);
			message.put("type", "delta");
			message.put(name, delta);
			conn.send(message.toString());
		}else{
			throw new NotWritableFieldException();
		}
	}
	public void set(JSONObject value) throws NotWritableFieldException{
		JSONObject fam = json.getJSONObject(name);
		Iterator<String> keys = value.keys();
		JSONObject message = new JSONObject();
		JSONObject delta = new JSONObject();
		while(keys.hasNext()){
			String key = keys.next();
			if(fam.has(key) && fam.getJSONObject(key).optBoolean("server-writeable")){
				delta.put(key, value);
			}
		}
		message.put("type", "delta");
		message.put(name, delta);
		conn.send(message.toString());
	}
	public Object get(String key){
		return json.getJSONObject(name).get(key);
	}
}
