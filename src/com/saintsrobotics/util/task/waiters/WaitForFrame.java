package com.saintsrobotics.util.task.waiters;
import java.util.function.BooleanSupplier;


public class WaitForFrame implements BooleanSupplier {
	@Override
	public boolean getAsBoolean() {
		return true;
	}
}
