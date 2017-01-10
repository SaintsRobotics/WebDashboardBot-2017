package com.saintsrobotics.webinterfacebot.tasks;
import com.saintsrobotics.util.task.Task;
import com.saintsrobotics.util.task.waiters.WaitForFrame;
import com.saintsrobotics.webinterfacebot.OI;
import com.saintsrobotics.webinterfacebot.OI.Axis;
import com.saintsrobotics.webinterfacebot.Robot;
import com.saintsrobotics.webinterfacebot.motors.Motors;

public class DriveTask extends Task {

	@Override
	protected void run() {
		OI oi = Robot.instance.oi;
		
		while(true) {
			double leftValue = oi.getDrive(Axis.LX) + oi.getDrive(Axis.RY);
			double rightValue = oi.getDrive(Axis.RX) + oi.getDrive(Axis.LY);
			Motors.get(1, false).set(leftValue);
			Motors.get(2, false).set(leftValue);
			Motors.get(3, false).set(leftValue);
			Motors.get(4, false).set(rightValue);
			Motors.get(5, false).set(rightValue);
			Motors.get(6, false).set(rightValue);
			yield(new WaitForFrame());
		}
	}

}
