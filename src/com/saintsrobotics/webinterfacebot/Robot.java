package com.saintsrobotics.webinterfacebot;

import com.saintsrobotics.util.dash.WebDashboard;
import com.saintsrobotics.util.task.Task;
import com.saintsrobotics.util.task.TaskRunner;
import com.saintsrobotics.webinterfacebot.motors.MotorsWebDashboard;
import com.saintsrobotics.webinterfacebot.tasks.DriveTask;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;

import java.net.UnknownHostException;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static Robot instance;
	public  WebDashboard webDashboard;
//	private SimpleHTTPServer server;
	private TaskRunner runner;
	public OI oi;
	public MotorsWebDashboard motors;
    public void robotInit() {
    	/*server = new SimpleHTTPServer(8080, new File("./home/lvuser/html"));
    	server.start();*/
    	try {
			webDashboard = new WebDashboard();
			webDashboard.start();
			Robot.log("socket up");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	motors = new MotorsWebDashboard(webDashboard);
    	oi = new OI();
    	instance = this;
    }
    
    public void autonomousInit() {
    }

    public void autonomousPeriodic() {
    	
    }
    @Override
    public void teleopInit(){
    	motors.refresh();
    	runner = new TaskRunner( new Task[]{
                new DriveTask()
    	});
    }
    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
    	runner.run();
    }
    
    @Override
    public void disabledInit(){
    	if(runner != null){
    		runner.disable();
    		runner = null;
    	}
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    public static void log(String s){
    	DriverStation.reportError(s + "\n", false);
    }
}
