
package org.usfirst.frc.team3694.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	public static Joystick driveStick = new Joystick(PortMap.driveStick);
	public static Joystick manStick = new Joystick(PortMap.manStick);
	
	public static JoystickButton strafe = new JoystickButton(driveStick, 1);
	public static JoystickButton forward = new JoystickButton(manStick, 6);
	public static JoystickButton backward = new JoystickButton(manStick, 4);
	
}
