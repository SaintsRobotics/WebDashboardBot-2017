package com.saintsrobotics.webinterfacebot;

import com.saintsrobotics.util.task.Task;
import com.saintsrobotics.util.task.waiters.WaitForSeconds;
import com.saintsrobotics.webinterfacebot.motors.Motor;
import com.saintsrobotics.webinterfacebot.motors.Motors;

public class SetMotorSpeed extends Task {

	@Override
	protected void run() {
		Motor mot = Motors.get(1,false);
		
		while(true){
			double maxdif = 0.25;
			mot.set(Robot.instance.oi.getDrive(OI.Axis.LX) * maxdif);
			yield(()->{					
				return true;
			});
		}
	}
}
