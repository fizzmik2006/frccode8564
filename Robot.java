// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;


/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  
  private DifferentialDrive m_robot;
  private Joystick m_leftStick;
  private Joystick m_rightStick;
  private static final int clawarmID = 7;
  private static final int middlearmID = 6;
  private static final int frontarmID = 5;
  private static final int leftFrontDeviceID = 4;
  private static final int leftBackDeviceID = 3; 
  private static final int rightFrontDeviceID = 2;
  private static final int rightBackDeviceID = 1;

  CANSparkMax m_frontLeftMotor;
  CANSparkMax m_backLeftMotor;

  CANSparkMax m_frontRightMotor;
  CANSparkMax m_backRightMotor;

  CANSparkMax m_frontarm;
  CANSparkMax m_middlearm;
  CANSparkMax m_clawarm;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    m_frontLeftMotor = new CANSparkMax(leftFrontDeviceID, MotorType.kBrushless);
    m_backLeftMotor = new CANSparkMax(leftBackDeviceID, MotorType.kBrushless);
    MotorControllerGroup m_left = new MotorControllerGroup(m_frontLeftMotor, m_backLeftMotor);

    m_frontRightMotor = new CANSparkMax(rightFrontDeviceID, MotorType.kBrushless);
    m_backRightMotor = new CANSparkMax(rightBackDeviceID, MotorType.kBrushless);
    MotorControllerGroup m_right = new MotorControllerGroup(m_frontRightMotor, m_backRightMotor);

    m_frontarm = new CANSparkMax(frontarmID, MotorType.kBrushless);
    m_middlearm = new CANSparkMax(middlearmID, MotorType.kBrushless);
    m_clawarm = new CANSparkMax(clawarmID, MotorType.kBrushless);
    MotorControllerGroup m_arm = new MotorControllerGroup(m_clawarm, m_middlearm, m_frontarm);
   


    m_robot = new DifferentialDrive(m_left, m_right);


    m_leftStick = new Joystick(0);  
    m_rightStick = new Joystick(1);}
  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:                        
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    SmartDashboard.putNumber("forward", m_rightStick.getX());
    m_robot.arcadeDrive(+m_rightStick.getY(), +m_rightStick.getX());
    SmartDashboard.putNumber("turn", m_rightStick.getY());   
  }
  /** This function is called o nce when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override










  
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
