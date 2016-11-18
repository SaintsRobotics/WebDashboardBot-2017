package com.saintsrobotics.webinterfacebot.motors;

import edu.wpi.first.wpilibj.SpeedController;

public class Motor {

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

    public double get() {
        return motor.get();
    }
    

    public void set(double speed) {
        if(!Motors.locks[pin]) System.out.println("Motor " + pin + " isn't locked. Check your code.");
        motor.set(speed);
    }
    /**
     * Immediately stops the motor
     * */
    public void stop(){
        
    }
}
