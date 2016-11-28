package com.saintsrobotics.util.task;

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
}