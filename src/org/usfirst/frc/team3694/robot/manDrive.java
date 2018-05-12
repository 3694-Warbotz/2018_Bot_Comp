package org.usfirst.frc.team3694.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;

public class manDrive {
	public static Spark topLeft = new Spark(PortMap.topLeft);
	public static Spark topRight = new Spark(PortMap.topRight);
	public static Talon Elevator = new Talon(PortMap.Elevator);
	
	public static void drive () {
		while (OI.forward.get() == true) {
			topLeft.set(0.75);
			topRight.set(0.75);
		}
		while (OI.backward.get() == true) {
			topLeft.set(-0.75);
			topRight.set(-0.75);
		}
		Elevator.set(OI.manStick.getY() * -1);
	}
}
