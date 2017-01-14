package com.saintsrobotics.webinterfacebot.tasks;

import com.saintsrobotics.util.task.Task;
import com.saintsrobotics.util.task.waiters.WaitForFrame;
import com.saintsrobotics.webinterfacebot.OI;
import com.saintsrobotics.webinterfacebot.OI.Axis;
import com.saintsrobotics.webinterfacebot.Robot;
import com.saintsrobotics.webinterfacebot.motors.Motors;

public class ArcadeDriveTask extends Task {
	@Override
	protected void run() {
		OI oi = Robot.instance.oi;
		
		while(true) {
			double leftValue = oi.getDrive(Axis.LX) + oi.getDrive(Axis.LY);
			double rightValue = oi.getDrive(Axis.LX) - oi.getDrive(Axis.LY);
			double coefficient = 0.75 + 0.25*(oi.getDrive(Axis.RT)) - 0.25*(oi.getDrive(Axis.LT));
			leftValue *= coefficient;
			rightValue *= coefficient;
			Motors.get(1, false).setGoal(leftValue);
			Motors.get(2, false).setGoal(leftValue);
			Motors.get(3, false).setGoal(leftValue);
			Motors.get(4, false).setGoal(rightValue);
			Motors.get(5, false).setGoal(rightValue);
			Motors.get(6, false).setGoal(rightValue);
			yield(new WaitForFrame());
		}
	}
}
