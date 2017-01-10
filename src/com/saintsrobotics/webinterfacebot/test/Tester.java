package com.saintsrobotics.webinterfacebot.test;

import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.function.BooleanSupplier;
import java.util.function.IntSupplier;

import org.json.JSONObject;

import com.saintsrobotics.util.dash.*;
import com.saintsrobotics.webinterfacebot.motors.MotorsWebDashboard;


public class Tester{
	public static void main(String[] args){
		try {
			WebDashboard dash = new WebDashboard();
			dash.start();
			Scanner scn = new Scanner(System.in);
			
			MotorsWebDashboard motors = new MotorsWebDashboard(dash);
			while(true){
				motors.refresh();
				motors.get("left");
				motors.get("right");
				scn.nextLine();
			}
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private static void log(String arg){
		System.out.println(arg);
	}
}