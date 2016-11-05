package com.saintsrobotics.webinterfacebot.motors;

import edu.wpi.first.wpilibj.SpeedController;

public class Motor {

    private SpeedController motor;
    private int pin;
    /**
     * Wraps a motor object
     * @param motor a speedcontroller object that represents the motor
     * @param pin the number of the pin
     * @param inverted 
     * */
    public Motor(SpeedController motor, int pin, boolean inverted) {
        this.motor = motor;
        this.pin = pin;
        motor.setInverted(inverted);
    }

    public double get() {
        return motor.get();
    }
    
    public int getPin(){
        return this.pin;
    }

    public void set(double speed) {
        motor.set(speed);
    }
    /**
     * Immediately stops the motor
     * */
    public void stop(){
        
    }
}

