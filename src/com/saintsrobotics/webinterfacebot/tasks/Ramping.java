package com.saintsrobotics.webinterfacebot.tasks;
import com.saintsrobotics.util.task.Task;
import com.saintsrobotics.util.task.waiters.WaitForFrame;
import com.saintsrobotics.webinterfacebot.motors.Motor;
import com.saintsrobotics.webinterfacebot.motors.Motors;

public class Ramping extends Task {
	
	protected void run() {
		for (Motor motor : Motors.getMotors()) {
			while (motor.getActual() < motor.get()) {
				motor.set(motor.getActual() + motor.getRate());
				yield (new WaitForFrame());
			}
		}
	}
}
