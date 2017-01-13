package com.saintsrobotics.webinterfacebot.tasks;
import com.saintsrobotics.util.task.Task;
import com.saintsrobotics.util.task.waiters.WaitForFrame;
import com.saintsrobotics.webinterfacebot.motors.Motor;
import com.saintsrobotics.webinterfacebot.motors.Motors;

public class Ramping extends Task {
	
	protected void run() {
		while(true) {
			for (Motor motor : Motors.getMotors()) {
				if (motor.getCurrent() < motor.getGoal() && motor.getRate() < motor.getGoal()) {
					motor.set(motor.getCurrent() + motor.getRate());
				} else if (motor.getCurrent() > motor.getGoal() && motor.getRate() > motor.getGoal() && motor.getRate() < motor.getCurrent()) {
					motor.set(motor.getCurrent() - motor.getRate());
				}
			}
			yield (new WaitForFrame());
		}
	}
}