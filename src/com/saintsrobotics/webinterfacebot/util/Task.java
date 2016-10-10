package com.saintsrobotics.webinterfacebot.util;

import com.zoominfo.util.yieldreturn.Generator;

import java.util.Iterator;
import java.util.function.BooleanSupplier;;
public abstract class Task extends Generator<BooleanSupplier>{
	public BooleanSupplier waiter;
	public Iterator<BooleanSupplier> iterator;
}