package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.SparkPIDController;

// This Subsystem will act as a Shooter Subsystem with Volatge Compensation

public class ShooterSubsystem extends SubsystemBase {
  //Constants for motor and PID
  private static final int kShooterMotorCanId = 3;
  private static final double kP = 0.0;
  private static final double kI = 0.0;
  private static final double kD = 0.0;
  private static final double kFF = 0.00176;
  private static final double kMaxOutput = 1.0;
  private static final double kMinOutput = -1.0;
  private double targetVelocityRPM = 3000.0;
  private static final double tolerance = 50.0;

  //Motor, Encoder, and PID Controller
  private static CANSparkMax ShooterMotor;
  private final RelativeEncoder ShooterEncoder;
  private static SparkPIDController pidController;

  
  public ShooterSubsystem() {
    //Initialize Motor
    ShooterMotor = new CANSparkMax(kShooterMotorCanId, MotorType.kBrushless);
    ShooterMotor.restoreFactoryDefaults();

    //Initalize Encoder and PID
    ShooterEncoder = ShooterMotor.getEncoder();
    pidController = ShooterMotor.getPIDController();

    //Configure PID 
    pidController.setP(kP);
    pidController.setI(kI);
    pidController.setD(kD);
    pidController.setFF(kFF);
    pidController.setOutputRange(kMinOutput, kMaxOutput);

    //Log Initialization
    System.out.println("ShooterSubsytem initialized with ShooterMotor ID: " + kShooterMotorCanId);
  }

  public static void setTargetVelocity(double targetRPM) {
    //Set the Target Velocity for the Motor
    pidController.setReference(targetRPM, CANSparkMax.ControlType.kVelocity);

    //Log the Target Velocity
    System.out.println("Setting target velocity: " + targetRPM + " RPM");
  }

  

  
  public static void stopShooterMotor() {
    ShooterMotor.stopMotor();
    System.out.println("Shooter Motor Stopped");
  }

  @Override
  public void periodic(){
  // Log Current Velocity Output
  double currentVelocity = ShooterEncoder.getVelocity();
  double ShooterMotorOutput = ShooterMotor.getAppliedOutput();

  System.out.println("Current Velocity: " + currentVelocity + " RPM");
  System.out.println("Motor Output: " + ShooterMotorOutput);

  if (Math.abs(targetVelocityRPM - ShooterEncoder.getVelocity()) < tolerance) {
    pidController.setReference(0, CANSparkMax.ControlType.kDutyCycle); // Hold the current speed
    }
  }
}