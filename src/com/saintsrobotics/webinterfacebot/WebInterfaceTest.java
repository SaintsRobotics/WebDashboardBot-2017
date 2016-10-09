package com.saintsrobotics.webinterfacebot;

import java.net.UnknownHostException;

public class WebInterfaceTest {
	public static void main(String[] args){
		try {
			WebInterface wi = new WebInterface();
			wi.start();
			wi.putField("hi", "bye", false);
			wi.putField("hey", "rei", true);
			while(true){
				if(wi.getField("hey").equals("bleh")){
					System.out.println("sucsess");
					break;
				}
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
