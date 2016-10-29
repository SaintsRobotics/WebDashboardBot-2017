package com.saintsrobotics.webinterfacebot.motors;

public class MotorsPractice extends Motors {

    public Motor LEFT()  { return getMotor( 0, true ); }
    public Motor RIGHT()  { return getMotor( 1, true ); }
}
