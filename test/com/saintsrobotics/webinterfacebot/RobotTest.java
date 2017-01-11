package com.saintsrobotics.webinterfacebot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Field;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest({NetworkTable.class, DriverStation.class})
@SuppressStaticInitializationFor("edu.wpi.first.wpilibj.DriverStation")
public class RobotTest {
    
    private Robot robot;
    private Talon leftDrive = mock(Talon.class);
    private Talon rightDrive = mock(Talon.class);
    private Joystick driveStick = mock(Joystick.class);
    private Joystick operatorStick = mock(Joystick.class);
    
    @Before
    public void setupRobot() throws Exception {
        PowerMockito.mockStatic(NetworkTable.class);
        ITable mockSubTable = mock(ITable.class);
        NetworkTable mockNetworkTable = mock(NetworkTable.class);
        when(mockNetworkTable.getSubTable("~STATUS~")).thenReturn(mockSubTable);
        PowerMockito.when(NetworkTable.class, "getTable", "LiveWindow").thenReturn(mockNetworkTable);
        
        DriverStation ds = mock(DriverStation.class);
        when(ds.isEnabled()).thenReturn(true);
        
        Field dsInstance = DriverStation.class.getDeclaredField("instance");
        dsInstance.setAccessible(true);
        dsInstance.set(null, ds);
        
        PowerMockito.whenNew(Talon.class).withArguments(0).thenReturn(leftDrive);
        PowerMockito.whenNew(Talon.class).withArguments(1).thenReturn(rightDrive);
        PowerMockito.whenNew(Joystick.class).withArguments(0).thenReturn(driveStick);
        PowerMockito.whenNew(Joystick.class).withArguments(1).thenReturn(operatorStick);
        
        when(driveStick.getRawAxis(anyInt())).thenReturn(0.5);
        
        robot = new Robot();
    }
    
    @Test
    public void testDrive() {
        robot.robotInit();
        robot.teleopInit();
        for (int i = 0; i < 100; i++) {
            robot.teleopPeriodic();
        }
        verify(leftDrive, times(100)).set(anyDouble());
        verify(rightDrive, times(100)).set(anyDouble());
        
        verify(leftDrive, atLeastOnce()).set(1.0);
        verify(rightDrive, atLeastOnce()).set(0.0);
    }
}
