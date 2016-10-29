package com.saintsrobotics.webinterfacebot;

import edu.wpi.first.wpilibj.Joystick;

public class OI {

    public enum Axis {
        LX(0),
        LY(1),
        LT(2),
        RT(3),
        RX(4),
        RY(5),
        ELBOW(0),
        ARM(1),
        CONTROL_BOARD_KNOB(2),
        LOGITECH_KNOB(2);

        int rawIndex;

        Axis(int rawIndex) {
            this.rawIndex = rawIndex;
        }
    }

    public enum Button {
        A(1),
        B(2),
        X(3),
        Y(4),
        LB(5),
        RB(6),
        SELECT(7),
        START(8),
        L3(9),
        R3(10);

        int rawIndex;

        Button(int rawIndex) {
            this.rawIndex = rawIndex;
        }
    }

    private Joystick driveStick;

    public OI() {
        driveStick = new Joystick(0);
            }

    public boolean getDrive(Button button) {
        return driveStick.getRawButton(button.rawIndex);
    }

    public double getDrive(Axis axis) {
        double val = driveStick.getRawAxis(axis.rawIndex);
        if (Math.abs(val) < 0.2) val = 0;
        return val;
    }


}
