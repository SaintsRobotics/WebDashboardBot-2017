package com.saintsrobotics.webinterfacebot.motors;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

public class Motors {
    private static Motor[] motors = new Motor[100];
    public static boolean[] locks = new boolean[100];


    /**
     * Alias for @link #getTalon().
     * Please don't call this unless you know what you're doing. Use @link MotorSet instead
     * @see #getTalon()
     * @see MotorSet
     * */
    public static Motor get(int pin, boolean inverted) {
       return getTalon(pin, inverted);
    }
    /**
     * This method attempts to return the speedcontroller corresponding with the pin number, and locks it.
     * This will lazily construct motors, meaning that motors are only constructed when they are first gotten.
     * It will throw a @link MotorLockedException if the motor is already locked.
     * This method defaults to Jaguars when constructing new motors.
     * Please don't call this if you don't know what you're doing, use @link com.saintsrobotics.webinterfacebot.motors.MotorSet instead, which automagically locks and unlocks the motors for you.
     *  @see com.saintsrobotics.webinterfacebot.motors.MotorLockedException
     * 
    */
    public static Motor getJaguar(int pin, boolean inverted) {
        if(locks[pin]) return null;
        if (motors[pin] == null) {
            motors[pin] = new Motor(new Jaguar(pin), pin, inverted);
        }
        return motors[pin];
    }
    /**
     * This method attempts to return the speedcontroller corresponding with the pin number, and locks it.
     * This will lazily construct motors, meaning that motors (and the associated actual speedcontroller) are only constructed when they are first gotten.
     * It will throw a @link com.saintsrobotics.webinterfacebot.MotorLockedException if the motor is already locked.
     * This method defaults to Talons when constructing new motors.
     * Please don't call this if you don't know what you're doing, use @link com.saintsrobotics.webinterfacebot.motors.MotorSet instead, which automagically locks and unlocks the motors for you.
     * 
     * @param   pin         pwm pin number of motor to be got
     * @param   inverted    If this motors should be inverted
     * @return  The motor requested.
     * 
     *  @throws com.saintsrobotics.webinterfacebot.motors.MotorLockedException  if the motor is already locked.
    */
    public static Motor getTalon(int pin, boolean inverted){
        if(locks[pin]) return null;
        if (motors[pin] == null) {
            motors[pin] = new Motor(new Talon(pin), pin, inverted);
        }
        return motors[pin];
    }
    /**
     * Unlocks a motor and sets it to zero (well it does it the other way round but whatever). Makes sure unclaimed motors don't run. 
     * 
     * @param   pin   pwm pin number of motor to unlock
     * */
    public static void unlock(int pin){
        motors[pin].stop();
        locks[pin] = false;
    }
    public static void lock(int pin) throws MotorLockedException{
        if(locks[pin]) throw new MotorLockedException();
        motors[pin].stop();
        locks[pin] = true;
    }
    

}
