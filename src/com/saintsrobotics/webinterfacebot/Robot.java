
package com.saintsrobotics.webinterfacebot;

import java.io.File;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.simpleHTTPServer.SimpleHTTPServer;

import com.saintsrobotics.webinterfacebot.util.Task;
import com.saintsrobotics.webinterfacebot.util.TaskRunner;

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
    public void robotInit() {
    	server = new SimpleHTTPServer(8080, new File("./home/lvuser/html/index.html"));
    	server.start();
    	try {
			webInterface = new WebInterface();
			webInterface.start();
			System.out.println("socket up");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Task[] tasks = {new Task(){
        	public void run(){
        		System.out.println("It's working!");
        		yield(()->{return true;});
        		System.out.println("Never say die");
        		yield(()->{return false;});
        		System.out.println("No man shall see this line. But I am gnome ann.");
        	}
        }
    	};
        runner = new TaskRunner(tasks);
    }
    
    public void autonomousInit() {
    }

    public void autonomousPeriodic() {
    	
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
