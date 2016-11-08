package com.saintsrobotics.webinterfacebot.util;

import com.zoominfo.util.yieldreturn.Generator;

import com.saintsrobotics.webinterfacebot.motors.MotorSet;
import com.saintsrobotics.webinterfacebot.motors.MotorLockedException;

import java.util.Iterator;
import java.util.function.BooleanSupplier;
public abstract class Task extends Generator<BooleanSupplier>{
	public BooleanSupplier waiter = new BooleanSupplier(){
		public boolean getAsBoolean(){
			return true;
		}
	};
	public Iterator<BooleanSupplier> iterator;
}