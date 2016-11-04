package com.saintsrobotics.webinterfacebot.util;

import com.zoominfo.util.yieldreturn.Generator;

import java.util.Iterator;
import java.util.function.BooleanSupplier;
public abstract class Task extends Generator<BooleanSupplier>{
	public BooleanSupplier wmaiter = new BooleanSupplier(){
		public boolean getAsBoolean(){
			return true;
		}
	};
	public Iterator<BooleanSupplier> iterator;
	private MotorSet motors;
	/**
	 * Pass a motorset to the Task and enable/disable it
	 * */
	protected void require(MotorSet motors){
        motors = motors;
        motors.enable();
    }
    protected MotorSet motors(){
    	if(this.motors.enabled == true)
    		return this.motors;
    	else
    		return null;
    }
    public void unrequire(){
    	if(motors == null) return;
    	motors.disable();
    	motors = null;
    }
}