package com.saintsrobotics.webinterfacebot.motors;

import edu.wpi.first.wpilibj.SpeedController;

public class Motor {

	private double goalSpeed;
	private double rampingRate = 0.1;
    private SpeedController motor;
    
    /**
     * Wraps a motor object
     * @param motor a speedcontroller object that represents the motor
     * @param inverted 
     * */
    public Motor(SpeedController motor, boolean inverted) {
        this.motor = motor;
        motor.setInverted(inverted);
    }

    public double getGoal() {
        return goalSpeed;
    }
    
    public double getCurrent() {
    	return motor.get();
    }

    public void setGoal(double speed) {
    	goalSpeed = speed;
    }
    
    public void setActual(double speed) {
    	motor.set(speed);
    }
    
    public double getRate() {
    	return rampingRate;
    }
    
    /**
     * Immediately stops the motor
     * */
    public void stop(){
        motor.set(0);
    }
}
