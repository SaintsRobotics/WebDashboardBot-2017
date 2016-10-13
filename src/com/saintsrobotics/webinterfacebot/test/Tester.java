package com.saintsrobotics.webinterfacebot.test;

import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.function.BooleanSupplier;
import java.util.function.IntSupplier;

import org.json.JSONObject;

import com.saintsrobotics.webinterfacebot.WebDashboard;


public class Tester{
	public static void main(String[] args){
		try {
			WebDashboard dash = new WebDashboard();
			dash.start();
			Scanner scn = new Scanner(System.in);
			while(true){
				log(dash.values.toString());
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