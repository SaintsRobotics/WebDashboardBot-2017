package com.saintsrobotics.webinterfacebot;

import org.simpleHTTPServer.SimpleHTTPServer;

public class SimpleHTTPServerTest {

	public static void main(String[] args) {
		new SimpleHTTPServer(8080, SimpleHTTPServer.DEFAULT_FILE).start();

	}

}
