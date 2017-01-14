package com.saintsrobotics.webinterfacebot.tasks;
import com.saintsrobotics.util.task.Task;
import com.saintsrobotics.util.task.waiters.WaitForFrame;
import com.saintsrobotics.util.task.Time;
import com.saintsrobotics.webinterfacebot.motors.Motor;
import com.saintsrobotics.webinterfacebot.motors.Motors;

public class Ramping extends Task {
	
	protected void run() {
		Time time = new Time();
		while(true) {
			for (Motor motor : Motors.getMotors()) {
				if(motor == null) continue;
				double current = motor.getCurrent();
				double goal = motor.getGoal();
				double rate = motor.getRate() * time.deltaSeconds();
				 
				if (current < goal) {
					if ((current + rate) > goal) {
						motor.setActual(goal);
					} else {
						motor.setActual(current + rate);
					}
				} else if (current > goal) {
					if ((current - rate) < goal) {
						motor.setActual(goal);
					} else {
						motor.setActual(current - rate);
					}
				}
			}
			time.update();
			yield (new WaitForFrame());
		}
	}
}