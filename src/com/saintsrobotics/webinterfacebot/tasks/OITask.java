package com.saintsrobotics.webinterfacebot.tasks;
import com.saintsrobotics.util.task.Task;
import com.saintsrobotics.webinterfacebot.OI;
import com.saintsrobotics.webinterfacebot.OI.Axis;
import com.saintsrobotics.webinterfacebot.Robot;
import com.saintsrobotics.webinterfacebot.motors.Motor;
import com.saintsrobotics.webinterfacebot.motors.Motors;

public class OITask extends Task {

	@Override
	protected void run() {
		OI oi = Robot.instance.oi;
		Motor left = Motors.get(0, false);
		left.set(oi.getDrive(Axis.LX) + oi.getDrive(Axis.RY));
		Motor right = Motors.get(1, false);
		right.set(oi.getDrive(Axis.RX) + oi.getDrive(Axis.LY));
	}

}
