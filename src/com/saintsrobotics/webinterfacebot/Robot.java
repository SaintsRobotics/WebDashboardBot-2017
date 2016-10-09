
package com.saintsrobotics.webinterfacebot;

import java.io.File;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.simpleHTTPServer.SimpleHTTPServer;

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
	public ArrayList<ChangeListener> listeners = new ArrayList<>();
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
