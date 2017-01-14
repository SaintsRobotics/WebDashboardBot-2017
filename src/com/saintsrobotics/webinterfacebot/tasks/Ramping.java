package com.saintsrobotics.webinterfacebot.tasks;
import com.saintsrobotics.util.task.Task;
import com.saintsrobotics.util.task.waiters.WaitForFrame;
import com.saintsrobotics.webinterfacebot.motors.Motor;
import com.saintsrobotics.webinterfacebot.motors.Motors;

public class Ramping extends Task {
	
	protected void run() {
		while(true) {
			for (Motor motor : Motors.getMotors()) {
				double current = motor.getCurrent();
				double goal = motor.getGoal();
				double rate = motor.getRate();
				 
				if (current < goal) {
					if ((current + rate) > goal) {
						motor.set(goal);
					} else {
						motor.set(current + rate);
					}
				} else if (current > goal) {
					if ((current - rate) < goal) {
						motor.set(goal);
					} else {
						motor.set(current - rate);
					}
				}
			}
			yield (new WaitForFrame());
		}
	}
}