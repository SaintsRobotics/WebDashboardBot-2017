package com.saintsrobotics.webinterfacebot.util;

import java.util.function.BooleanSupplier;

public class WaitForSeconds implements BooleanSupplier {
	long finalTimeMillis;
	public WaitForSeconds(double sec){
		finalTimeMillis = (long)(sec*1000) + System.currentTimeMillis();
	}
	
	@Override
	public boolean getAsBoolean() {
		return finalTimeMillis < System.currentTimeMillis();
	}

}
