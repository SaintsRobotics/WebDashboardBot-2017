package src.com.saintsrobotics.webinterfacebot.tasks;

import src.com.saintsrobotics.util.task.Task;

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
