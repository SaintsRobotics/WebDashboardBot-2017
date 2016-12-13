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
		double leftValue = oi.getDrive(Axis.LX) + oi.getDrive(Axis.RY);
		Motor left1 = Motors.get(0, false);
		left1.set(leftValue);
		Motor left2 = Motors.get(0, false);
		left2.set(leftValue);
		Motor left3 = Motors.get(0, false);
		left3.set(leftValue);
		double rightValue = oi.getDrive(Axis.RX) + oi.getDrive(Axis.LY);
		Motor right1 = Motors.get(1, false);
		right1.set(rightValue);
		Motor right2 = Motors.get(1, false);
		right2.set(rightValue);
		Motor right3 = Motors.get(1, false);
		right3.set(rightValue);
	}

}
