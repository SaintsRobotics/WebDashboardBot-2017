package com.saintsrobotics.webinterfacebot;

import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;

import org.json.*;

public class WebInterface extends WebSocketServer {
	
	protected JSONArray table = new JSONArray();
	protected JSONObject values = new JSONObject();
	
	
	
	public WebInterface() throws UnknownHostException {
		super(new InetSocketAddress(1899));

		// TODO Auto-generated constructor stub
	}
	public void putField(String name, String data, boolean input){
		JSONObject obj = new JSONObject()
							.put("name", name)	
							.put("data", data)
							.put("type", input?"input":"output");
		table.put(obj);
		for(WebSocket s : connections()){
			s.send(new JSONObject().put(name,obj).toString());
		}
		values.put(name, data);
	}
	public String getField(String field){
		return values.getString(field);
	}
	public void debug(String message){
		JSONObject debug = new JSONObject()
								.put("type", "debug")
								.put("data", message);
		for(WebSocket s : connections()){
			s.send(debug.toString());
		}
	}
	@Override
	public void onOpen(WebSocket conn, ClientHandshake handshake) {
		System.out.println("Connected to " + conn.getRemoteSocketAddress().toString());
		conn.send(table.toString());
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		System.out.println("closed " + conn.getRemoteSocketAddress().toString() + " with code " + code + " because " + reason + "; closed by " + (remote ? "remote":"local"));
	}

	@Override
	public void onMessage(WebSocket conn, String message) {
		System.out.println("recv: " + message + " from " + conn.getRemoteSocketAddress().toString());
		JSONObject json = new JSONObject(message);
		values.put(json.getString("name"), json.get("value"));
	}
	

	@Override
	public void onError(WebSocket conn, Exception ex) {
		System.out.println("ERROR: " + ex.getMessage());
		ex.printStackTrace();
		// TODO Auto-generated method stub
		
	}

}
