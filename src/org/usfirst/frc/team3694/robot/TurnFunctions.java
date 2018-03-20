package org.usfirst.frc.team3694.robot;

public class TurnFunctions {
	//Turns the robot left quite aggressively
	public static void LeftTurn() {
		DriveTrain.frontLeft.set(.25);
		DriveTrain.frontRight.set(.5);
		DriveTrain.rearLeft.set(.3);
		DriveTrain.rearRight.set(.55);
	}

	//Turns the robot right somewhat aggressively
	public static void RightTurn() {
		DriveTrain.frontLeft.set(.5);
		DriveTrain.frontRight.set(.25);
		DriveTrain.rearLeft.set(.55);
		DriveTrain.rearRight.set(.3);
	}
	
	//Twirls the robot in a terrifying manner
	public static void Twirl() {
		DriveTrain.frontLeft.set(1);
		DriveTrain.frontRight.set(-1);
		DriveTrain.rearLeft.set(1);
		DriveTrain.rearRight.set(-1);
	}

}
