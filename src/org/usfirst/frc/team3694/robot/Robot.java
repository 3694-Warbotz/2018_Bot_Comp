package org.usfirst.frc.team3694.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	//SmartDashboard stuffs
	private static final String crossLineEncoder = "crossLineEncoder";
	private static final String crossLineTimer = "crossLineTimer";
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();
	
	//Robot control for joySticks
	public DriveTrain drivetrain = new DriveTrain();
	public OI oi = new OI();
	public PortMap portmap = new PortMap();
	public double xAxis = Math.pow(OI.driveStick.getX(), 1);
	public double yAxis = Math.pow(OI.driveStick.getY(), 1);
	public double twistAxis = Math.pow(OI.driveStick.getTwist(), 5/3);
	public static String GameData = DriverStation.getInstance().getGameSpecificMessage();
	
	//this is a gyroscope
	public static AnalogGyro gyro = new AnalogGyro(0);
	
	//Enocoders
	public Encoder frontLeft = new Encoder(0, 1, false, EncodingType.k4X);
	public Encoder rearLeft = new Encoder(2, 3, false, EncodingType.k4X);
	public Encoder frontRight = new Encoder(4, 5, false, EncodingType.k4X);
	public Encoder rearRight = new Encoder(6, 7, false, EncodingType.k4X);
	
	//Distance Doubles
	public double gearRatio = 20;
	public double encodergearratio = 1;
	public double ppr = 360;
	public double fuckedupfactor = 1;
	final double dpp = ((Math.PI * 6) / ppr /encodergearratio / gearRatio / fuckedupfactor);
	
	//Timer
	Timer autoTimer = new Timer();
	
	
	@Override
	public void robotInit() {
		//Auto chooser
		m_chooser.addDefault("crossLineEncoder", crossLineEncoder);
		m_chooser.addObject("crossLineTimer", crossLineTimer);
		SmartDashboard.putData("Auto choices", m_chooser);
		
		//set encoder distances
		frontLeft.setDistancePerPulse(dpp);
		frontRight.setDistancePerPulse(dpp);
		rearLeft.setDistancePerPulse(dpp);
		rearRight.setDistancePerPulse(dpp);
		DriveTrain.rearRight.setInverted(true);
		DriveTrain.frontRight.setInverted(true);
		DriveTrain.frontLeft.setInverted(true);
		DriveTrain.frontLeft.setInverted(true);
	    CameraServer.getInstance().startAutomaticCapture();
	}

	@Override
	public void autonomousInit() {
		m_autoSelected = m_chooser.getSelected();
		// autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + m_autoSelected);
		autoTimer.reset();
		autoTimer.start(); 
	}

	@Override
	public void autonomousPeriodic() {
		switch (m_autoSelected) {
			case crossLineTimer:
				autoTimer.reset();
				autoTimer.start();
				while (autoTimer.get() <= 10) {
					DriveTrain.drivebot.tankDrive(0.5, 0.5);
				}
				break;
			case crossLineEncoder:
			default:
				frontLeft.reset();
				frontRight.reset();
				rearLeft.reset();
				rearRight.reset();
				autoTimer.reset();
				autoTimer.start();
				while (frontLeft.getDistance() <= 85.25 && frontRight.getDistance() <= 85.25 && rearLeft.getDistance() <= 85.25 && rearRight.getDistance() <= 85.25 && autoTimer.get() <= 10.5) {


				}
				while (frontLeft.getDistance() > 85.25) {
					DriveTrain.drivebot.tankDrive(0, 0);
				}
				while (frontRight.getDistance() > 85.25) {
					DriveTrain.drivebot.tankDrive(0, 0);
				}
				while (rearLeft.getDistance() > 85.25) {
					DriveTrain.drivebot.tankDrive(0, 0);
				}
				while (rearRight.getDistance() > 85.25) {
					DriveTrain.drivebot.tankDrive(0, 0);
				}
				break;
		} 
		
		if (autoTimer.get() < 3) {
			DriveTrain.drivebot.tankDrive(0.5, 0.5);
			while(autoTimer.get() >= 5) {
				if (GameData.charAt(0) == 'L') {
					manDrive.topLeft.set(-0.5);
					manDrive.topRight.set(-0.5);
				}
				else {
					manDrive.topLeft.set(0);
					manDrive.topRight.set(0);
					}
				
				
			}
		}
	}


	@Override
	public void teleopPeriodic() {
		DriveTrain.drive();
		manDrive.drive();
	}

	@Override
	public void testPeriodic() {
		
		}
		
	}

