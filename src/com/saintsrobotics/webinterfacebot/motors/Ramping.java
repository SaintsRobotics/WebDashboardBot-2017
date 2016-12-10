package com.saintsrobotics.webinterfacebot.motors;
import com.saintsrobotics.util.task.Task;
import com.saintsrobotics.util.task.waiters.WaitForFrame;

public class Ramping extends Task {
	
	protected void run() {
		Motor motor = Motors.get(0, false);
		double currentSpeed = 0;
		double goalSpeed = 0;
		double rate = 0.1;
		while (currentSpeed < goalSpeed) {
			currentSpeed += rate;
			motor.set(currentSpeed);
			yield (new WaitForFrame());
		}
		
	}
	
}
