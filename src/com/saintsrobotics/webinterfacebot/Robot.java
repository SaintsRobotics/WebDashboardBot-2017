
package com.saintsrobotics.webinterfacebot;

import java.io.File;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.simpleHTTPServer.SimpleHTTPServer;

import com.saintsrobotics.webinterfacebot.motors.Motors;
import com.saintsrobotics.webinterfacebot.motors.MotorsPractice;
import com.saintsrobotics.webinterfacebot.util.Task;
import com.saintsrobotics.webinterfacebot.util.TaskRunner;
import com.saintsrobotics.webinterfacebot.util.WaitForSeconds;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public  WebInterface webInterface;
	private SimpleHTTPServer server;
	private TaskRunner runner;
	public Motors motors;
	public OI oi;
    public void robotInit() {
    	/*server = new SimpleHTTPServer(8080, new File("./home/lvuser/html/index.html"));
    	server.start();
    	try {
			webInterface = new WebInterface();
			webInterface.start();
			System.out.println("socket up");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	motors = new MotorsPractice();
    	oi = new OI();
    }
    
    public void autonomousInit() {
    }

    public void autonomousPeriodic() {
    	
    }
    @Override
    public void teleopInit(){
    	runner = new TaskRunner( new Task[]{
                new Task(){
					@Override
					protected void run() {
						motors.LEFT().set(0.7);
						yield(new WaitForSeconds(10));
						motors.LEFT().set(-0.7);
						yield(new WaitForSeconds(10));
						while(true){
							motors.LEFT().set(oi.getDrive(OI.Axis.LY));
							yield(()->{return true;});
						}
					}
                },
                new Task(){
                	@Override
					protected void run() {
							while(true){
							motors.RIGHT().set(oi.getDrive(OI.Axis.LY));
							yield(()->{return true;});
						}
					}
                }
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
    
}
