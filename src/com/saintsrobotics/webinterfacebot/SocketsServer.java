package com.saintsrobotics.webinterfacebot;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import org.json.*;


public class WebDashboard extends WebSocketServer {
	
	public JSONObject values;
	
	
	
	public WebDashboard() throws UnknownHostException {
		super(new InetSocketAddress(1899));
		// TODO Auto-generated constructor stub

		try {
			values = new JSONObject(new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir"),"/html/define.json"))));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		System.out.println("Connected to " + conn.getRemoteSocketAddress().toString());
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		System.out.println("closed " + conn.getRemoteSocketAddress().toString() + " with code " + code + " because " + reason + "; closed by " + (remote ? "remote":"local"));
	}

	@Override
	public void onMessage(WebSocket conn, String message) {
		System.out.println("recv: " + message + " from " + conn.getRemoteSocketAddress().toString());
		JSONObject json = new JSONObject(message);
		if(json.getString("type").equals("confirm")){
			Iterator<String> keys =json.keys();
			while(keys.hasNext()){
				String changeKey = keys.next();
				if(changeKey.equals("type")){
					changeKey = keys.next();
				}
				JSONObject changeValues = values.getJSONObject(changeKey);
				Iterator<String> things = json.getJSONObject(changeKey).keys();
				while(things.hasNext()){
					String key = things.next();
					if(!changeValues.has(key) || !changeValues.getJSONObject(key).getBoolean("server-writable")){
						conn.send("{'type':'error','message':'I never told you to do that'}");
						return;
					}
				}
				things = json.getJSONObject(changeKey).keys();
				while(things.hasNext()){
					String key = things.next();
					changeValues.getJSONObject(key).put("value", json.getJSONObject(changeKey).get(key));
				}
			}
		}else if(json.getString("type").equals("delta")){
			Iterator<String> keys=json.keys();
			while(keys.hasNext()){
				String changeKey = keys.next();
				if(changeKey.equals("type")){
					changeKey = keys.next();
				}
				JSONObject changeValues = values.getJSONObject(changeKey);
				Iterator<String> things = json.getJSONObject(changeKey).keys();
				while(things.hasNext()){
					String key = things.next();
					if(!changeValues.has(key) || changeValues.getJSONObject(key).getBoolean("server-writable")){
						conn.send("{'type':'error','message':'You shouldn\\'nt be able to write to that'}");
						return;
					}
				}
				things = json.getJSONObject(changeKey).keys();
				
			}
			json.put("type", "confirm");
			conn.send(json.toString());
			while(keys.hasNext()){
				String changeKey = keys.next();
				if(changeKey.equals("type")){
					changeKey = keys.next();
				}
				JSONObject changeValues = values.getJSONObject(changeKey);
				Iterator<String> things = json.getJSONObject(changeKey).keys();
				while(things.hasNext()){
					String key = things.next();
					changeValues.getJSONObject(key).put("value", json.getJSONObject(changeKey).get(key));
				}
			}
			
		}else if(json.getString("type").equals("error")){
			//welp
		}
	}
	

	@Override
	public void onError(WebSocket conn, Exception ex) {
		System.out.println("ERROR: " + ex.getMessage());
		ex.printStackTrace();
		// TODO Auto-generated method stub
	}
	
}
