package com.saintsrobotics.webinterfacebot.util;

import zoominfo.util.yieldreturn.Generator;
import java.util.function.BooleanProvider;
public abstract class Task extends Generator<BooleanProvider>{
	public BooleanProvider waiter;
}