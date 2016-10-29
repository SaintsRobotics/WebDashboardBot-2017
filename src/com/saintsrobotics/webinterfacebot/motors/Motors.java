package com.saintsrobotics.webinterfacebot.motors;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;

public abstract class Motors {

    public abstract Motor LEFT();
    public abstract Motor RIGHT();
    Motor[] motors = new Motor[2];

    public Motor getMotor(int pin, boolean inverted) {
        if (motors[pin] == null) {
            motors[pin] = new Motor(new Talon(pin), inverted);
        }
        return motors[pin];
    }

    public class Motor {

        private SpeedController motor;

        public Motor(SpeedController motor, boolean inverted) {
            this.motor = motor;
            motor.setInverted(inverted);
        }

        public double get() {
            return motor.get();
        }

        public void set(double speed) {
            motor.set(speed);
        }
    }
}
