package com.saintsrobotics.webinterfacebot.util;

import com.zoominfo.util.yieldreturn.Generator;

import java.util.Iterator;
import java.util.function.BooleanSupplier;
public abstract class Task extends Generator<BooleanSupplier>{
	public BooleanSupplier waiter = new BooleanSupplier(){
		public boolean getAsBoolean(){
			return true;
		}
	};
	public Iterator<BooleanSupplier> iterator;
	protected MotorSet motors;
	/**
	 * Pass a motorset to the Task and enable it
	 * @param motors A @link MotorSet that will be locked and accessable through @link #motors()
	 * */
	protected void require(MotorSet motors){
        motors = motors;
        motors.enable();
    }
    /**
     * Disable the @link MotorSet previously required
     * */
    public void unrequire(){
    	if(motors == null) return;
    	motors.disable();
    	motors = null;
    }
}