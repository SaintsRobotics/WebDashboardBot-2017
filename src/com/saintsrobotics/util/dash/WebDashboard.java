package com.saintsrobotics.util.dash;

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

import com.saintsrobotics.webinterfacebot.Robot;


public class WebDashboard extends WebSocketServer {
	
	public JSONObject values;
	private WebSocket defaultSocket;
	
	
	public WebDashboard() throws UnknownHostException {
		super(new InetSocketAddress(1899));
		// TODO Auto-generated constructor stub

		try {
			values = new JSONObject(new String(Files.readAllBytes(Paths.get("home/lvuser/html/define.json"))));
		} catch (JSONException e) {
			Robot.log("JSONException in WebDashboard Boot!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Robot.log("IOException in WebDashboard Boot!");
			Robot.log(e.getMessage());
		}
	}
	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		Robot.log("Connected to " + conn.getRemoteSocketAddress().toString());
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		Robot.log("closed " + conn.getRemoteSocketAddress().toString() + " with code " + code + " because " + reason + "; closed by " + (remote ? "remote":"local"));
	}

	@Override
	public void onMessage(WebSocket conn, String message) {
		this.defaultSocket = conn;
		JSONObject json = new JSONObject(message);
		if(json.getString("type").equals("error")){
			return;
			//welp
			//TODO: actually fix this
		}
		boolean confirm = json.getString("type").equals("confirm");
		Iterator<String> keys =json.keys();
		while(keys.hasNext()){
			String changeKey = keys.next();
			if(changeKey.equals("type")){
				if(!keys.hasNext()) break;
				changeKey = keys.next();
			}
			JSONObject changeValues = values.getJSONObject(confirm?"client":"server").getJSONObject(changeKey);
			Iterator<String> things = json.getJSONObject(changeKey).keys();
			while(things.hasNext()){
				String key = things.next();
				if(!changeValues.has(key)){
					defaultSocket.send("{'type':'error','message':'I never told you to do that'}");
					return;
				}
			}
			things = json.getJSONObject(changeKey).keys();
			while(things.hasNext()){
				String key = things.next();
				System.out.println(confirm?"client":"server" + " " + changeKey + " " + key);
				values.getJSONObject(confirm?"client":"server").getJSONObject(changeKey).put(key, json.getJSONObject(changeKey).get(key));
			}
		}
	}
	public void send(String message){
		if(this.defaultSocket == null)
			this.defaultSocket = this.connections().iterator().next();
		this.defaultSocket.send(message);
	}
	public ValueFamily family(String s){
		return new ValueFamily(s,this);
	}

	@Override
	public void onError(WebSocket conn, Exception ex) {
		Robot.log("ERROR: " + ex.getMessage());
	}
	
}
