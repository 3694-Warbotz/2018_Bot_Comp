package org.usfirst.frc.team3694.robot;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

public class DriveTrain {
	public static Victor frontLeft = new Victor(PortMap.frontLeft);
	public static Victor frontRight = new Victor(PortMap.frontRight);
	public static Victor rearLeft = new Victor(PortMap.rearLeft);
	public static Victor rearRight = new Victor(PortMap.rearRight);
	
	public static SpeedControllerGroup left = new SpeedControllerGroup(frontLeft, rearLeft);
	public static SpeedControllerGroup right = new SpeedControllerGroup(frontRight, rearRight);
	public static DifferentialDrive drivebot = new DifferentialDrive(left, right);
	
	public static void drive() {
		drivebot.arcadeDrive(Math.pow(OI.driveStick.getY(), 3), Math.pow(-OI.driveStick.getX(), 3));
		while (OI.strafe.get() == true) {
			if (OI.driveStick.getX() > 0) {
				DriveTrain.frontRight.set(OI.driveStick.getX());
				DriveTrain.rearLeft.set(OI.driveStick.getX());
				DriveTrain.frontLeft.set(OI.driveStick.getX() * -1);
				DriveTrain.rearRight.set(OI.driveStick.getX() * -1);
			}
			else if (OI.driveStick.getX() < 0) {
				DriveTrain.frontRight.set(OI.driveStick.getX() * -1);
				DriveTrain.rearLeft.set(OI.driveStick.getX() * -1);
				DriveTrain.frontLeft.set(OI.driveStick.getX());
				DriveTrain.rearRight.set(OI.driveStick.getX());
			}
		}
	}
	}
