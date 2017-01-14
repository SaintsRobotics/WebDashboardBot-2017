package src.com.saintsrobotics.webinterfacebot.motors;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

public class Motors {
    private static Motor[] motors = new Motor[10];

    /**
     * Alias for @link #getTalon().
     * @see #getTalon()
     * @see MotorSet
     * */
    public static Motor get(int pin, boolean inverted) {
       return getTalon(pin, inverted);
    }
    /**
     * This method attempts to return the speedcontroller corresponding with the pin number.
     * This will lazily construct motors, meaning that motors are only constructed when they are first gotten.
     * This method defaults to Jaguars when constructing new motors.
     * 
     * @param   pin         pwm pin number of motor to be got
     * @param   inverted    If this motors should be inverted
     * @return  The motor requested.
    */
    public static Motor getJaguar(int pin, boolean inverted) {
        if (motors[pin] == null) {
            motors[pin] = new Motor(new Jaguar(pin), inverted);
        }
        return motors[pin];
    }
    /**
     * This method attempts to return the speedcontroller corresponding with the pin number.
     * This will lazily construct motors, meaning that motors (and the associated actual speedcontroller) are only constructed when they are first gotten.
     * This method defaults to Talons when constructing new motors.
     * 
     * @param   pin         pwm pin number of motor to be got
     * @param   inverted    If this motors should be inverted
     * @return  The motor requested.
     * 
    */
    public static Motor getTalon(int pin, boolean inverted){
        if (motors[pin] == null) {
            motors[pin] = new Motor(new Talon(pin), inverted);
        }
        return motors[pin];
    }
    
    public static void stopAll(){
        for(Motor m : motors) m.stop();
    }
    
    public static Motor[] getMotors() {
    	return motors;
    }
}
